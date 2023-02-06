<template>
  <div class="m-0 p-0 users">
    <div class="m-0 p-0 row" style="height: 40%">
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="0"></user-comp>
      </div>
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="1"></user-comp>
      </div>
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="2"></user-comp>
      </div>
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="3"></user-comp>
      </div>
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="4"></user-comp>
      </div>
    </div>
    <div class="m-0 p-0 row" style="height: 10%"></div>
    <div class="m-0 p-0 row" style="height: 40%">
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="5"></user-comp>
      </div>
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="6"></user-comp>
      </div>
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="7"></user-comp>
      </div>
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="8"></user-comp>
      </div>
      <div class="m-0 p-0" style="width: 20%">
        <user-comp curIdx="9"></user-comp>
      </div>
    </div>
  </div>
</template>

<script>
import UserComp from "@/components/ingame/UserComp.vue";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL =
  // process.env.NODE_ENV === "production"
  //   ? "http://192.168.32.171:5000/"
  //   : "http://localhost:5000/";
  "http://192.168.32.171:5000/";

export default {
  components: { UserComp },
  name: "GameUser",
  el: "#liar",
  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,

      myIdx: -1,
      // Join form
      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
    };
  },
  created() {
    // 세션 구조 복사
    // console.log(this.$store.state.sessions[i].ovSession)
    // this.mySession = Object.assign({}, this.$store.modules.session),
    this.initFrontSession();
  },
  methods: {
    joinSession(myIdx) {
      // --- 1) Get an OpenVidu object ---
      this.$store.state.sessions[myIdx].ovSession.OV = new OpenVidu();

      // --- 2) Init a session ---
      this.$store.state.sessions[myIdx].ovSession.session =
        this.$store.state.sessions[myIdx].ovSession.OV.initSession();

      // console.log(222, this.$store.state.sessions[myIdx].ovSession.session)
      // console.log(this.session)
      //     // --- 3) Specify the actions when events take place in the session ---

      //     // On every new Stream received...

      this.$store.state.sessions[myIdx].ovSession.session.on(
        "streamCreated",
        ({ stream }) => {
          // console.log(555, this.$store.state.sessions[myIdx].ovSession.session)
          const subscriber =
            this.$store.state.sessions[myIdx].ovSession.session.subscribe(
              stream
            );
          for (let i = 0; i < 10; i++) {
            if (!this.$store.state.sessions[i].isJoin) {
              this.$store.state.sessions[i].ovSession.publisher = subscriber;
              // this.subscribers.push(subscriber);
              this.$store.state.sessions[i].isJoin = true;
              console.log(
                "this.$store.state.sessions[i].ovSession.publisher",
                this.$store.state.sessions[i].ovSession.publisher
              );
              break;
            }
          }

          // console.log("subscribers",this.subscribers)
        }
      );

      this.$store.state.sessions[myIdx].ovSession.session.on(
        "streamDestroyed",
        ({ stream }) => {
          // const index = this.subscribers.indexOf(stream.streamManager, 0);
          for (let i = 0; i < 10; i++) {
            console.log(
              // this.$store.state.sessions[i].ovSession.publisher == stream.streamManager
              this.$store.state.sessions[i].ovSession.publisher
            );
          }
          // if (index >= 0) {
          //     this.subscribers.splice(index, 1);
          // }
        }
      );

      this.$store.state.sessions[myIdx].ovSession.session.on(
        "exception",
        ({ exception }) => {
          console.warn(exception);
        }
      );

      this.getToken(this.mySessionId).then((token) => {
        this.$store.state.sessions[myIdx].ovSession.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            let publisher = this.$store.state.sessions[
              myIdx
            ].ovSession.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            // this.mainStreamManager = publisher;
            this.$store.state.sessions[myIdx].ovSession.publisher = publisher;

            this.$store.state.sessions[myIdx].ovSession.session.publish(
              publisher
            );
          })
          .catch((error) => {
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
      // console.log(this.myIdx)
      if (this.$store.state.sessions[this.myIdx].ovSession.session)
        this.$store.state.sessions[this.myIdx].ovSession.session.disconnect();

      // Empty all properties...

      this.$store.state.sessions[this.myIdx].isJoin = false;
      this.$store.state.sessions[this.myIdx].isReady = false;
      this.$store.state.sessions[this.myIdx].ovSession = {
        OV: undefined,
        session: undefined,
        publisher: undefined,
      };
      this.$store.state.sessions[this.myIdx].myIdx = -1;

      // Remove beforeunload listener
      window.removeEventListener("beforeunload", this.leaveSession);
    },

    // updateMainVideoStreamManager(stream) {
    //     if (this.mainStreamManager === stream) return;
    //         this.mainStreamManager = stream;
    // },
    initFrontSession() {
      // let sessions = this.$store.state.sessions
      for (let i = 0; i < 10; i++) {
        if (!this.$store.state.sessions[i].isJoin) {
          this.$store.state.sessions[i].isJoin = true;
          this.$store.state.sessions[i].myIdx = i;
          this.myIdx = i;
          console.log(this.myIdx);
          this.joinSession(i);
          // myIdx에 유저 접속했다고 백에 알리기(x)
          break;
        }
      }
      for (let i = 0; i < 10; i++) {
        console.log("myidx", this.$store.state.sessions[i].myIdx);
      }
    },

    // 세션 통신 부분
    // 세션 통신이 여기서만 이뤄지기 때문에 라우터 튜닝 안할껍니다

    // 백엔드에서 세션 받아오기
    getFrontSessionFromBackSession() {
      let sessions;
      // axios로 세션 받아와서 세션 구조 맞춰주기(x)
      // 프론트랑 백이랑 세션 구조 동일하게 맞추기(x)
      return sessions;
    },

    // 프론트 세션 싱크로
    syncFrontSession() {
      this.$store.state.sessions = this.getFrontSessionFromBackSession();
    },

    // 백엔드로 세션 보내기
    postFrontSessionToBackSession() {
      let session = this.mySession;
      // axios로 세션보내기(x)
    },

    async getToken(mySessionId) {
      const sessionId = await this.createSession(mySessionId);
      return await this.createToken(sessionId);
    },

    async createSession(sessionId) {
      const response = await axios.post(
        APPLICATION_SERVER_URL + "api/sessions",
        { customSessionId: sessionId },
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

<style></style>
