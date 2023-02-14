<template>
  <div class="rooms">방 목록</div>
  <div class="refresh-wrapper">
    <router-link :to="{ name: 'lobby', query: { page: 1 } }" class="nav-link">
      <BIconArrowCounterclockwise
        class="btn-refresh"
      ></BIconArrowCounterclockwise
    ></router-link>

    <div class="room-enter-wrapper">
      <div class="text-white lobby-text">방 번호로 입장</div>
      <input class="lobby lobby-input" v-model="roomId" />
      <button class="lobby lobby-back lobby-enter" v-on:click="joinRoom">
        입장
      </button>
    </div>
  </div>
</template>

<script>
import { BIconArrowCounterclockwise } from "bootstrap-icons-vue";
import axios from "axios";
import router from "@/router";
import VueCookies from "vue-cookies";
import { OpenVidu } from "openvidu-browser";

export default {
  name: "LobbyHeader",
  components: { BIconArrowCounterclockwise },
  data() {
    return {
      roomId: null,
    };
  },
  created() {},
  methods: {
    joinRoom() {
      let roomId = this.roomId;
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
        const subscriber = this.session.subscribe(stream, undefined);
        this.subscribers.push(subscriber);
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

      window.addEventListener("beforeunload", this.leaveSession);
    },
  },
};
</script>

<style scoped>
.rooms {
  font-size: 4vh;
  color: rgb(207, 207, 207);
  padding-top: 3vh;
  text-align: center;
  margin-bottom: 1vh;
}
.btn-refresh {
  color: white;
  font-size: 3.5vh;
}
.refresh-wrapper {
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
  align-items: center;
}
.room-enter-wrapper {
  display: flex;
}
.lobby-input {
  width: 4vw;
  margin-left: 1vw;
  margin-right: 0.5vw;
}
.lobby-enter {
  width: 3vw;
}
.lobby-text {
  font-size: 2vh;
}
</style>
