<template>
  <div class="modal fade" id="createRoomModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <div class="login-modal-header">
            <div class="hidden-icon">&times;</div>
            <div class="login-text">방 생성</div>
            <div class="close" v-on:click="offModal">&times;</div>
          </div>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            v-on:click="resetval"
          ></button>
        </div>
        <div class="modal-body" style="text-align: left">
          <div class="modselector">
            <input
              type="radio"
              id="normal"
              value="normal"
              name="mode"
              class="radiobtn"
              v-model="gamemode"
              checked
            />
            <label for="normal" class="normalmode modeimg chkmode"></label>
            <input
              type="radio"
              id="spy"
              value="spy"
              name="mode"
              v-model="gamemode"
              class="radiobtn"
            />
            <label for="spy" class="spymode modeimg chkmode"></label>
          </div>
          <div v-if="gamemode === 'normal'" class="speech-bubble describe">
            <h4>클래식모드</h4>
            <p>라이어는 1명인 기본 모드</p>
          </div>

          <div
            v-else-if="gamemode === 'spy'"
            class="speech-bubble-two describe"
          >
            <h4>스파이모드</h4>
            <p>라이어 1명, 스파이 1명인 모드</p>
          </div>

          <ul style="list-style: none" class="m-0 p-0 gameoption">
            <li>방 제목</li>
            <li>
              <input type="text" class="modal-input" v-model="roomtitle" />
            </li>
            <li>
              비밀번호
              <input type="checkbox" v-model="roomchk" v-on:click="chksecret" />
            </li>
            <li>
              <input
                type="text"
                v-bind:disabled="!roomchk"
                class="modal-input"
                v-model="roompwd"
              />
            </li>
          </ul>
          <div class="optionwrapper">
            <div class="m-0 p-0" style="width: 50%">
              <ul
                style="list-style: none; text-align: center"
                class="m-0 p-0 gameoption"
              >
                <li>인원 수</li>
                <li>
                  <select name="player" id="" v-model="playercnt">
                    <option value="null">선택</option>
                    <option v-bind:value="6">6명</option>

                    <!-- <option v-bind:value="4">4명</option>
                    <option v-bind:value="5">5명</option>
                    <option v-bind:value="6">6명</option>
                    <option v-bind:value="7">7명</option>
                    <option v-bind:value="8">8명</option>
                    <option v-bind:value="9">9명</option>
                    <option v-bind:value="10">10명</option> -->
                  </select>
                </li>
              </ul>
            </div>
            <div class="m-0 p-0" style="width: 50%">
              <ul
                style="list-style: none; text-align: center"
                class="m-0 p-0 gameoption"
              >
                <li>발언 시간</li>
                <li>
                  <select name="time" id="" v-model="talktime">
                    <option value="null">선택</option>
                    <option v-bind:value="10">10초</option>
                  </select>
                </li>
              </ul>
            </div>
          </div>
          <div class="btnwrapper">
            <button
              type="button"
              class="btn btn-primary btn-sm"
              data-bs-dismiss="modal"
              v-on:click="createGame"
            >
              생성
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";
import VueCookies from "vue-cookies";
import { OpenVidu } from "openvidu-browser";

const APPLICATION_SERVER_URL = "http://192.168.91.171:5000/";
// process.env.NODE_ENV === "production"
//   ? "http://192.168.91.171:5000/"
//   : "http://localhost:5000/";
// "http://localhost:5000/";
// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";

export default {
  components: {},
  data() {
    return {
      OV: null,
      session: null,
      publisher: null,
      subcribers: [],
      gamemode: "normal", // 게임모드 기본설정
      roomchk: false, // 비밀번호 체크여부
      roompwd: null, // 게임방 비밀번호
      roomtitle: null, // 게임방 제목
      playercnt: null, // 참가인원
      talktime: null, // 발언시간
      API_URL: this.$store.state.API_URL,
    };
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {
    // 모달창 닫으면 초기화
    offModal() {
      (this.gamemode = "normal"), // 게임모드 기본설정
        (this.roomchk = false), // 비밀번호 체크여부
        (this.roompwd = null), // 게임방 비밀번호
        (this.roomtitle = null), // 게임방 제목
        (this.playercnt = null), // 참가인원
        (this.talktime = null); // 발언시간
      this.$emit("close");
    },
    // 비빌번호 체크 해제시 초기화
    chksecret() {
      if (this.roomchk) {
        this.roompwd = null;
      }
    },
    // 생성버튼 클릭시 이벤트
    createGame() {
      if (
        this.roomtitle === null ||
        this.playercnt === null ||
        this.talktime === null ||
        (this.roomchk === true && this.roompwd === null)
      ) {
        alert("미입력된 설정값이 있습니다.");
      } else {
        axios({
          method: "post",
          // url: `${API_URL}/rooms`,
          url: `${this.API_URL}/rooms`,
          headers: {
            // accessToken: this.$cookies.get("accessToken"),
            Authorization: `Bearer ${VueCookies.get("accessToken")}`,
          },
          data: {
            title: this.roomtitle,
            mode: this.gamemode,
            password: this.roompwd,
            maxCount: this.playercnt,
            timeout: this.talktime,
          },
        })
          .then((res) => {
            console.log(res.data);
            this.$store.dispatch("setMyIdx", res.data);

            // 만약 성공을했다면... room/${roomId}로 인게임.vue로 보낸다.
            // 1. state 방진입 isEnter -> true 단, 방진입직후에는 isEnter를 false로 바꿔줘야된다.
            this.roomPwd = null;
            this.offModal();
            this.$store.dispatch("setIsEnter");
            // 2. 기본방정보 저장
            this.$store.dispatch("setGameInfo", res.data);
            // 응답결과로는 토큰과 roomId가 올것이다.
            // router.push({ name: "room", params: { roomId: 1 } });
            // 테스트용으로는 임의로 roomId를 설정한다.
            console.log(4444444444444);
            this.joinSession(res.data.roomId);
          })
          .catch((err) => {
            this.roomPwd = null;

            console.log("실패");
            console.log(err);
            // 테스트용
            // this.$store.dispatch("setIsEnter");
            // router.push({ name: "room", params: { roomId: 13 } });
            // alert("게임방 진입에 실패하셨습니다");
          });
      }
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
        console.log("들어왔어?");
        const subscriber = this.session.subscribe(stream, undefined);
        console.log("subscriber", subscriber);
        this.$store.dispatch("setSubscribers", subscriber);
        console.log("됐냐?");
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
            if (this.subscribers === undefined) {
              this.subscribers = [];
            }
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
.describe {
  margin-top: 2vh;
}
#createRoomModalLabel {
  margin: 0 auto;
  padding-left: 25%;
}
.modselector {
  display: flex;
  justify-content: space-evenly;
}
.modselector input:checked + .chkmode {
  border: #2a7428 5px solid;
}
.radiobtn {
  position: absolute;
  left: -9999px;
}
.normalmode {
  background-image: url("@/assets/home/mode-one.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
.spymode {
  background-image: url("@/assets/home/spyimg.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
.modeimg {
  width: 90px;
  height: 90px;
}
.optionwrapper {
  display: flex;
  justify-content: center;
}

.speech-bubble {
  position: relative;
  background: #00aabb;
  border-radius: 0.4em;
}

.speech-bubble:after {
  content: "";
  position: absolute;
  top: 1%;
  left: 60%;
  width: 0;
  height: 0;
  border: 22px solid transparent;
  border-bottom-color: #00aabb;
  border-top: 0;
  margin-left: -115px;
  margin-top: -22px;
}
.speech-bubble-two {
  position: relative;
  background: #00aabb;
  border-radius: 0.4em;
}

.speech-bubble-two:after {
  content: "";
  position: absolute;
  top: 1%;
  left: 39%;
  width: 0;
  height: 0;
  border: 22px solid transparent;
  border-bottom-color: #00aabb;
  border-top: 0;
  margin-left: 75px;
  margin-top: -22px;
}

.gameoption li {
  margin: 10px 0px;
}

.btnwrapper {
  text-align: center;
  margin-top: 10px;
}
select {
  width: 100px;
}
.modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}
.login-modal-header {
  display: flex;
  margin-top: 0.5vh;
  justify-content: space-between;
  align-items: center;
}

/* Modal Content */
.modal-content {
  margin-top: 20vh;
  margin-left: 39vw;
  background-color: rgba(255, 255, 255, 75%);
  padding: 20px;
  border: 1px solid #888;
  width: 22vw;
  height: 75vh;
  border-radius: 30px;
}
.login-text {
  font-size: 3.5vh;
  font-weight: 500;
  text-align: center;
}

/* The Close Button */
.hidden-icon {
  visibility: hidden;
  font-size: 5vh;
}
.close {
  color: #aaaaaa;
  font-size: 5vh;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
.modal-input {
  background-color: rgb(217, 217, 217);
  border-radius: 20px;
  border: 0px;
  width: 100%;
  height: 4vh;
  padding: 20px;
}
</style>
