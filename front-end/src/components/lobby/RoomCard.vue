<template>
  <!-- 받은 룸아이디를 created 혹은 computed로 백에 방정보를 요청하는 함수 실행 -->
  <!-- 만약 프롭스로 받아온 데이터가 없을때 다른 이미지 처리를 해줘야함. -->
  <div class="m-0 p-0 col-6 roomwrapper">
    <!-- 일반방일때 -->
    <div
      v-if="isPrivate === false"
      class="card m-0 p-0"
      style="width: 95%; height: 95%"
      v-on:click="joinRoom(id)"
    >
      <div class="row g-0">
        <div class="col-md-2 imgwrapper">
          <img
            v-if="mode === 'normal'"
            src="@/assets/home/mode-one.jpg"
            class="img-fluid rounded-start modeimg"
            alt="..."
          />
          <img
            v-if="mode === 'spy'"
            src="@/assets/home/mode-two.jpg"
            class="img-fluid rounded-start modeimg"
            alt="..."
          />
        </div>
        <div class="col-md-10">
          <div class="card-body m-0 px-2">
            <ul class="card-title m-0 p-0">
              <li>No.{{ id }}</li>
              <li class="ellipsis">{{ title }}</li>
            </ul>
            <p class="card-text">{{ curCount }}/{{ maxCount }}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 비밀방일때 -->
    <div
      v-if="isPrivate === true"
      class="card m-0 p-0"
      style="width: 95%; height: 95%"
      data-bs-toggle="modal"
      data-bs-target="#secretRoomModal"
    >
      <div class="row g-0">
        <div class="col-md-2 imgwrapper">
          <img
            v-if="mode === 'normal'"
            src="@/assets/home/mode-one.jpg"
            class="img-fluid rounded-start modeimg"
            alt="..."
          />
          <img
            v-if="mode === 'spy'"
            src="@/assets/home/mode-two.jpg"
            class="img-fluid rounded-start modeimg"
            alt="..."
          />
        </div>
        <div class="col-md-10">
          <div class="card-body m-0 px-2">
            <ul class="card-title m-0 p-0">
              <li>No.{{ id }}</li>
              <li class="ellipsis">{{ title }}</li>
            </ul>
            <p class="card-text">{{ curCount }}/{{ maxCount }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 비밀방 들어가는 모달 -->
  <div
    class="modal fade"
    id="secretRoomModal"
    tabindex="-1"
    aria-labelledby="secretRoomModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="secretRoomModalLabel">
            비밀번호 입력
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            v-on:click="resetRoomData"
          ></button>
        </div>
        <div class="modal-body" style="text-align: left">
          <ul style="list-style: none">
            <li style="display: flex; justify-content: space-between">
              <input
                type="text"
                placeholder="비밀번호를 입력해주세요"
                class="form-control"
                style="width: 70%"
                v-model="rommPwd"
              />
              <button
                type="button"
                class="btn btn-primary btn-sm"
                v-on:click="joinRoom(id)"
                data-bs-dismiss="modal"
              >
                입력
              </button>
            </li>
          </ul>
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

// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";
const APPLICATION_SERVER_URL =
  // process.env.NODE_ENV === "production"
  //   ? "http://192.168.91.171:5000/"
  //   : "http://localhost:5000/";
  "http://192.168.91.171:5000/";
export default {
  name: "RoomCard",
  props: ["roomIdx"],
  components: {},
  data() {
    return {
      OV: null,
      session: null,
      publisher: null,
      subcribers: [],

      id: null,
      title: null,
      maxCount: null,
      curCount: null,
      isPlaying: null,
      mode: null,
      isPrivate: null,
      rommPwd: null,
      API_URL: this.$store.state.API_URL,
    };
  },
  setup() {},
  created() {
    this.test();
  },
  mounted() {
    // if (this.roomIdx < this.$store.state.rooms.length) {
    //   this.id = this.$store.state.rooms[this.roomIdx].id;
    //   this.title = this.$store.state.rooms[this.roomIdx].title;
    //   this.maxCount = this.$store.state.rooms[this.roomIdx].maxCount;
    //   this.curCount = this.$store.state.rooms[this.roomIdx].curCount;
    //   this.isPlaying = this.$store.state.rooms[this.roomIdx].isPlaying;
    //   this.mode = this.$store.state.rooms[this.roomIdx].mode;
    //   this.isPrivate = this.$store.state.rooms[this.roomIdx].isPrivate;
    // }
  },
  computed: {},
  methods: {
    test() {
      if (this.roomIdx < this.$store.state.rooms.length) {
        this.id = this.$store.state.rooms[this.roomIdx].id;
        this.title = this.$store.state.rooms[this.roomIdx].title;
        this.maxCount = this.$store.state.rooms[this.roomIdx].maxCount;
        this.curCount = this.$store.state.rooms[this.roomIdx].curCount;
        this.isPlaying = this.$store.state.rooms[this.roomIdx].isPlaying;
        this.mode = this.$store.state.rooms[this.roomIdx].mode;
        this.isPrivate = this.$store.state.rooms[this.roomIdx].isPrivate;
      }
    },
    resetRoomData() {
      this.rommPwd = null;
    },
    // 게임방 진입 메서드
    // 1. patch로 방진입을 '시도'한다.
    // 2. 응답결과가 성공일 경우에는 해당 게임방 room/{roomId}로 보내준다.
    // 3. 응답결과가 실패일 경우에는 방진입 실패 alert창을 띄워준다.
    joinRoom(roomId) {
      console.log(roomId);
      console.log(`${VueCookies.get("accessToken")}`);
      axios({
        method: "patch",
        url: `${this.API_URL}/rooms/${roomId}/enter`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
        data: {
          password: this.rommPwd,
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

// 1. 방아이디를 받아서 방정보를 요청하는 함수 실행
// 2. 방정보를 data에 보관하고 보여주는 형식으로 진행
</script>

<style scoped>
.roomwrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
.card {
  border: 2px solid #5b3700;
  background-color: #949494;
}
.imgwrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
.modeimg {
  width: 50px;
  height: 50px;
  border-radius: 10px !important;
}
.card-title {
  list-style: none;
  display: flex;
  justify-content: space-between;
}
/* .card-title li {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
} */
.card-title li:nth-child(1) {
  text-align: left;
  margin-right: 20px;
}
.card-title li:nth-child(2) {
  text-align: right;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.card-text {
  text-align: right;
}
</style>
