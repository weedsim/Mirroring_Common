<template>
  <div class="rooms">
    <div class="rooms-wrapper">
      <div
        class="room"
        v-for="(room, index) in rooms"
        :key="index"
        v-on:click="joinRoom(room.id)"
      >
        <div class="card">
          <div class="card-image-wrapper">
            <img
              v-if="!room.isPrivate"
              class="card-image"
              src="@/assets/home/mode-one.jpg"
            />
            <img
              v-if="room.isPrivate"
              class="card-image"
              src="@/assets/home/mode-two-room.jpg"
            />
          </div>
          <div style="width: 100%">
            <div class="room-id-title">
              <div>No.{{ room.id }}</div>
              <div>{{ room.title }}</div>
            </div>
            <div class="room-count">
              <div>{{ room.curCount }} / {{ room.maxCount }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";
import axios from "axios";
import router from "@/router";
import VueCookies from "vue-cookies";
import { OpenVidu } from "openvidu-browser";
import { mapState, mapActions } from "vuex";

// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";
// const APPLICATION_SERVER_URL = "http://192.168.91.171:5000/";
const APPLICATION_SERVER_URL = "http://localhost:5000/";
// process.env.NODE_ENV === "production"
//   ? "http://192.168.91.171:5000/"
//   : "http://localhost:5000/";
// "http://localhost:5000/";

export default {
  name: "LobbyMain",
  data() {
    return {
      API_URL: this.$store.state.API_URL,
      rooms: [],
      OV: undefined,
      session: undefined,
      mySessionId: undefined,
      subscribers: [],
      publisher: undefined,
      // nowpage: this.$route.query.page,
    };
  },
  created() {
    this.getRooms(1);
    if (this.openvidu !== undefined && this.openvidu.session !== undefined)
      this.initSession;
  },
  computed: {
    ...mapActions(["initSession"]),
    ...mapState(["openvidu"]),
    // chgpage() {
    //   return this.$route.query.page;
    // },
  },
  watch: {
    $route(to, from) {
      if (to.query !== from.query) {
        console.log("페이지변경!");
        this.getRooms(to.query);
      }
    },
  },
  methods: {
    // 게임방 목록을 받아오는 메서드
    getRooms(pageNumber) {
      console.log(pageNumber.page);
      axios({
        method: "get",
        // url: `${API_URL}/rooms?pageNumber=${페이지네이션 번호}`,
        url: `${this.API_URL}/rooms`,
        params: {
          page: pageNumber.page - 1,
        },
      })
        .then((res) => {
          // 받아온 방정보 8개는 뷰엑스 스토어로 저장할 예정
          console.log(res.data);
          this.rooms = res.data;
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
    },
    joinRoom(roomId) {
      console.log("방입장, 방번호는 : ", roomId);
      axios({
        method: "patch",
        url: `${this.API_URL}/rooms/${roomId}/enter`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
        data: {
          password: this.roompwd,
        },
      })
        .then((res) => {
          console.log(res);
          // 만약 성공을했다면... room/${roomId}로 인게임.vue로 보낸다.
          // 1. state 방진입 isEnter -> true 단, 방진입직후에는 isEnter를 false로 바꿔줘야된다.
          this.roomPwd = null;
          this.$store.dispatch("setIsEnter");
          this.$store.dispatch("setGameInfo", res.data);
          this.$store.dispatch("setMyIdx", res.data);
          console.log("11111");
          this.joinSession(roomId);
          console.log("22222");
        })
        .catch((err) => {
          this.roomPwd = null;

          console.log("실패");
          console.log(err);
          // 테스트용
          // this.$store.dispatch("setIsEnter");
          // router.push({ name: "room", params: { roomId: this.id } });
          // alert("게임방 진입에 실패하셨습니다");
        });
    },
    joinSession(roomId) {
      // --- 1) Get an OpenVidu object ---

      this.OV = new OpenVidu();
      console.log("1번성공");

      // --- 2) Init a session ---
      this.session = this.OV.initSession();
      console.log("2번성공");

      // --- 3) Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        console.log("누군가 들어왔어");
        const subscriber = this.session.subscribe(stream, undefined);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        console.log("누군가 떠났어");
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.log("오류발생");
        console.warn(exception);
      });
      console.log("3번성공");

      // --- 4) Connect to the session with a valid user token ---

      // Get a token from the OpenVidu deployment
      this.getToken(roomId).then((token) => {
        console.log("3.5번성공");
        // First param is the token. Second param can be retrieved by every user on event
        // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
        this.session
          .connect(token, { clientData: "최원준" })
          .then(() => {
            console.log("4번성공");
            // --- 5) Get your own camera stream with the desired properties ---

            // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
            // element: we will manage it on our own) and with the desired properties
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });
            console.log("5번성공");

            // Set the main video in the page to display our webcam and store our Publisher
            this.publisher = publisher;
            // --- 6) Publish your stream ---
            console.log("오픈비두 6완료");

            this.session.publish(this.publisher);
            this.$store.dispatch("setOpenvidu", {
              OV: this.OV,
              session: this.session,
              publisher: this.publisher,
              subscribers: this.subscribers,
            });

            router.push({ name: "room", params: { roomId: roomId } });
            console.log("라우터 푸시 완료");
            // this.$store.state.openvidu.OV = this.OV;
            // this.$store.state.openvidu.session = this.session;
            // this.$store.state.openvidu.publisher = this.publisher;
            // this.$store.state.openvidu.subscribers = this.subscribers;
          })
          .catch((error) => {
            console.log("오픈비두 연결안됨");
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });
    },

    async getToken(mySessionId) {
      const sessionId = await this.createSession(mySessionId);
      return await this.createToken(sessionId);
    },

    async createSession(sessionId) {
      const response = await axios.post(
        APPLICATION_SERVER_URL + "api/sessions",
        { customSessionId: String(sessionId) },
        {
          headers: { "Content-Type": "application/json" },
        }
      );
      return response.data; // The sessionId
    },

    async createToken(sessionId) {
      const response = await axios.post(
        APPLICATION_SERVER_URL + "api/sessions/" + sessionId + "/connections",
        {},
        {
          headers: { "Content-Type": "application/json" },
        }
      );
      return response.data; // The token
    },
  },
};
</script>

<style scoped>
.rooms {
  height: 65vh;
}
.rooms-wrapper {
  display: flex;
  flex-wrap: wrap;
}
.room {
  height: 13vh;
  padding: 10px;
  width: 50%;
}
.room-id-title {
  width: 100%;
  display: flex;
  justify-content: space-between;
  padding-top: 1.4vh;
  padding-right: 1.5vw;
  padding-left: 0.5vw;
  font-size: 20px;
}
.room-count {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
  padding-top: 1.4vh;
  padding-right: 1.5vw;
  padding-left: 0.5vw;
  font-size: 20px;
}
.roomwrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
.card-image-wrapper {
  padding: 2vh;
}
.card {
  display: flex;
  border: 2px solid #5b3700;
  background-color: #949494;
  border-radius: 20px;
  width: 100%;
  height: 100%;
}
.card-image {
  height: 100%;
}
</style>
