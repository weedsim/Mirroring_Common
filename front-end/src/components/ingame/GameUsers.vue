<template>
  <div class="users-container">
    <div
      class="user"
      v-for="(item, index) in $store.state.gameinfo.participants"
      :key="index"
    >
      <div class="speaking-blur" :class="{ 'speaking-blur': active }">
        <!-- <div class="p-3 speaking-blur"> -->
        <div class="user-screen speaking" :class="{ speaking: active }">
          <!-- $store.state.sessions[index].isJoin -->
          <video v-if="false" alt="" autoplay></video>
          <img src="@/assets/ingame/headphone.png" v-else alt="" />
          <div class="screen"></div>
          <div
            class="ready"
            style="background-color: rgba(0, 135, 70, 81%)"
            v-if="this.$store.state.gameinfo.participants[index].isReady"
          >
            <!-- this.$store.state.sessions[index].isReady -->
            준비완료
          </div>

          <div
            class="unready"
            style="background-color: rgba(255, 176, 57, 81%)"
            v-else
          >
            대기중
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import UserDisplay from "@/components/ingame/UserDisplay.vue";
import { OpenVidu } from "openvidu-browser";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL =
  process.env.NODE_ENV === "production"
    ? "http://localhost:5000/"
    : "http://192.168.91.171:5000/";
// "http://192.168.91.171:5000/";

export default {
  components: {
    UserDisplay,
  },
  name: "GameUser",
  el: "#liar",
  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,

      myIdx: this.$store.state.myIdx,
      // Join form
      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
    };
  },
  created() {
    console.log("여기야!!", this.$store.state.gameinfo.participants);
    // 세션 구조 복사
    // console.log(this.$store.state.sessions[i].ovSession)
    // this.mySession = Object.assign({}, this.$store.modules.session),
    // this.initFrontSession();
  },
  computed: {},
  methods: {
    //   joinSession(myIdx) {
    //     // --- 1) Get an OpenVidu object ---
    //     this.$store.state.sessions[myIdx].ovSession.OV = new OpenVidu();
    //     // --- 2) Init a session ---
    //     this.$store.state.sessions[myIdx].ovSession.session =
    //       this.$store.state.sessions[myIdx].ovSession.OV.initSession();
    //     // console.log(222, this.$store.state.sessions[myIdx].ovSession.session)
    //     // console.log(this.session)
    //     //     // --- 3) Specify the actions when events take place in the session ---
    //     //     // On every new Stream received...
    //     this.$store.state.sessions[myIdx].ovSession.session.on(
    //       "streamCreated",
    //       ({ stream }) => {
    //         // console.log(555, this.$store.state.sessions[myIdx].ovSession.session)
    //         const subscriber =
    //           this.$store.state.sessions[myIdx].ovSession.session.subscribe(
    //             stream
    //           );
    //         for (let i = 0; i < 10; i++) {
    //           if (!this.$store.state.sessions[i].isJoin) {
    //             this.$store.state.sessions[i].ovSession.publisher = subscriber;
    //             // this.subscribers.push(subscriber);
    //             this.$store.state.sessions[i].isJoin = true;
    //             console.log(
    //               "this.$store.state.sessions[i].ovSession.publisher",
    //               this.$store.state.sessions[i].ovSession.publisher
    //             );
    //             break;
    //           }
    //         }
    //         // console.log("subscribers",this.subscribers)
    //       }
    //     );
    //     this.$store.state.sessions[myIdx].ovSession.session.on(
    //       "streamDestroyed",
    //       ({ stream }) => {
    //         console.log(this.$store.state.sessions);
    //         for (let i = 0; i < 10; i++) {
    //           if (this.$store.state.sessions[i].myIdx == -1) {
    //             this.$store.state.sessions[i] = Object.assign(
    //               {},
    //               this.$store.state.session
    //             );
    //             break;
    //           }
    //         }
    //       }
    //     );
    //     this.$store.state.sessions[myIdx].ovSession.session.on(
    //       "exception",
    //       ({ exception }) => {
    //         console.warn(exception);
    //       }
    //     );
    //     this.getToken(this.mySessionId).then((token) => {
    //       this.$store.state.sessions[myIdx].ovSession.session
    //         .connect(token, { clientData: this.myUserName })
    //         .then(() => {
    //           let publisher = this.$store.state.sessions[
    //             myIdx
    //           ].ovSession.OV.initPublisher(undefined, {
    //             audioSource: undefined, // The source of audio. If undefined default microphone
    //             videoSource: undefined, // The source of video. If undefined default webcam
    //             publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
    //             publishVideo: true, // Whether you want to start publishing with your video enabled or not
    //             resolution: "640x480", // The resolution of your video
    //             frameRate: 30, // The frame rate of your video
    //             insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
    //             mirror: false, // Whether to mirror your local video or not
    //           });
    //           // this.mainStreamManager = publisher;
    //           this.$store.state.sessions[myIdx].ovSession.publisher = publisher;
    //           this.$store.state.sessions[myIdx].ovSession.session.publish(
    //             publisher
    //           );
    //         })
    //         .catch((error) => {
    //           console.log(
    //             "There was an error connecting to the session:",
    //             error.code,
    //             error.message
    //           );
    //         });
    //     });
    //     window.addEventListener("beforeunload", this.leaveSession);
    //   },
    //   leaveSession() {
    //     // console.log(this.myIdx)
    //     if (this.$store.state.sessions[this.myIdx].ovSession.session)
    //       this.$store.state.sessions[this.myIdx].ovSession.session.disconnect();
    //     // Empty all properties...
    //     this.$store.state.sessions[this.myIdx].isJoin = false;
    //     this.$store.state.sessions[this.myIdx].isReady = false;
    //     this.$store.state.sessions[this.myIdx].ovSession = {
    //       OV: undefined,
    //       session: undefined,
    //       publisher: undefined,
    //     };
    //     this.$store.state.sessions[this.myIdx].myIdx = -1;
    //     // Remove beforeunload listener
    //     window.removeEventListener("beforeunload", this.leaveSession);
    //   },
    //   // updateMainVideoStreamManager(stream) {
    //   //     if (this.mainStreamManager === stream) return;
    //   //         this.mainStreamManager = stream;
    //   // },
    //   initFrontSession() {
    //     // this.myIdx에 서버에서 받은 idx 할당
    //     console.log("myidx-gameusers", this.myIdx);
    //     console.log("myidx-gameusers", this.$store.state.sessions[this.myIdx]);
    //     this.$store.state.sessions[this.myIdx].isJoin = true;
    //     this.joinSession(this.myIdx);
    //   },
    //   async getToken(mySessionId) {
    //     const sessionId = await this.createSession(mySessionId);
    //     return await this.createToken(sessionId);
    //   },
    //   async createSession(sessionId) {
    //     const response = await axios.post(
    //       APPLICATION_SERVER_URL + "api/sessions",
    //       { customSessionId: String(sessionId) },
    //       {
    //         headers: { "Content-Type": "application/json" },
    //       }
    //     );
    //     return response.data; // The sessionId
    //   },
    //   async createToken(sessionId) {
    //     const response = await axios.post(
    //       APPLICATION_SERVER_URL + "api/sessions/" + sessionId + "/connections",
    //       {},
    //       {
    //         headers: { "Content-Type": "application/json" },
    //       }
    //     );
    //     return response.data; // The token
    //   },
  },
};
</script>

<style scoped>
.user {
  width: 20vw;
  margin-left: 2.5vw;
  margin-right: 2.5vw;
  margin-top: 1vw;
  margin-bottom: 1vw;
}
.users-container {
  display: flex;
  flex-wrap: wrap;
}
img {
  width: 100%;
  aspect-ratio: 5/3.9;
  border-radius: 10px;
}
.speaking {
  outline-style: solid;
  outline-color: #008c06;
  outline-width: 10px;
}
.speaking-blur {
  outline-width: 15px;
  outline-offset: 10px;
  outline-color: #66f986;
}
.ready {
  position: absolute;
  bottom: 0%;
  width: 100%;
  font-size: 1.2vw;
  text-align: center;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}
.unready {
  position: absolute;
  bottom: 0%;
  width: 100%;
  font-size: 1.2vw;
  text-align: center;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}
.user-screen {
  position: relative;
  border-radius: 10px;
  /* opacity: 0%; */
  /* z-index: 5; */
}
.btn-mic-cam {
  position: absolute;
  top: 0%;
  display: none;
  width: 100%;
  height: 100%;
}
.user-name {
  position: absolute;
  top: 0%;
  color: black;
  text-shadow: -1px 0 #ffffff, 0 1px #ffffff, 1px 0 #ffffff, 0 -1px #ffffff;
  font-weight: 600;
}
.screen {
  top: 0%;
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 4;
}
.screen:hover {
  background-color: rgba(0, 0, 0, 30%);
}
.screen:hover > .btn-mic-cam {
  background-color: rgba(0, 0, 0, 30%);
  display: block;
}
.screen:hover > .btn-mic-cam .vote {
  display: block;
}
.vote-msg:hover {
  cursor: pointer;
}
.vote {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0%;
  left: 0%;
  display: none;
}
.vote-msg {
  text-align: center;
  position: relative;
  top: 45%;
  transform: translateY(-50%);
  color: rgb(210, 0, 0);
  font-size: 3vw;
  /* letter-spacing: 1vw; */
  font-weight: 600;
}
.wait {
  /* opacity: 0%; */
}
</style>
