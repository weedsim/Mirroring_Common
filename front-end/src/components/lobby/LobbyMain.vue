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
          <div>
            <div class="room-id-title">
              <div>No.{{ room.id }}</div>
              <div>{{ room.title }}</div>
            </div>
            <div>{{ room.curCount }} / {{ room.maxCount }}</div>
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

// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";
const APPLICATION_SERVER_URL =
  // process.env.NODE_ENV === "production"
  //   ? "http://192.168.91.171:5000/"
  //   : "http://localhost:5000/";
  "http://192.168.91.171:5000/";

export default {
  name: "LobbyMain",
  data() {
    return {
      API_URL: this.$store.state.API_URL,
      rooms: [],
      // nowpage: this.$route.query.page,
    };
  },
  created() {
    this.getRooms(1);
  },
  // computed: {
  //   chgpage() {
  //     return this.$route.query.page;
  //   },
  // },
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
      console.log(roomId);
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
          this.joinSession();
          router.push({ name: "room", params: { roomId: res.data.roomId } });
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
    joinSession() {
      // --- 1) Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- 2) Init a session ---
      this.session = this.OV.initSession();

      // --- 3) Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
        console.log("subscribers", this.subscribers);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- 4) Connect to the session with a valid user token ---

      // Get a token from the OpenVidu deployment
      this.getToken(this.mySessionId).then((token) => {
        // First param is the token. Second param can be retrieved by every user on event
        // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
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

            // Set the main video in the page to display our webcam and store our Publisher
            this.publisher = publisher;
            this.$store.state.publisher = publisher;
            this.$store.state.subscribers = [];
            // --- 6) Publish your stream ---

            this.session.publish(this.publisher);
            this.$store.state.session = this.session;
            console.log("store-session", this.$store.state.session);
            console.log("store-subscribers", this.$store.state.subscribers);
            console.log("store-publisher", this.$store.state.publisher);
            console.log("하이");
            console.log("감자고구마", this.publisher);
            console.log("하이2");
            console.log("오픈비두 6완료");
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

      window.addEventListener("beforeunload", this.leaveSession);
    },
    leaveSession() {
      // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      // Empty all properties...
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.$store.state.myIdx = undefined;
      this.$store.state.publisher = undefined;
      this.$store.state.subscribers = [];
      this.OV = undefined;

      // Remove beforeunload listener
      window.removeEventListener("beforeunload", this.leaveSession);
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
