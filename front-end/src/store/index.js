import { createStore } from "vuex";
import axios from "axios";
import router from "@/router";
import VueCookies from "vue-cookies";
import createPersistedState from "vuex-persistedstate";
import playGameStore from "@/store/modules/playgame.js";
import jwtDecode from "vue-jwt-decode";

// const API_URL = "http://127.0.0.1:5000";
const API_URL = "http://localhost:5000";
// const API_URL = "http://192.168.91.171:5000";
// const API_URL = "https://i8a706.p.ssafy.io/api";
// const API_URL = "http://i8a706.p.ssafy.io:8080";

const storageStata = createPersistedState({
  paths: ["playGameStore"],
});

export default createStore({
  modules: {
    playGameStore: playGameStore,
  },
  plugins: [storageStata],
  state: {
    openvidu: {
      OV: undefined,
      session: undefined,
      publisher: undefined,
      subscribers: [],
    },

    API_URL: API_URL,
    // API_URL: "http://i8a706.p.ssafy.io:8080",
    isEnter: true, // 게임방 진입시 라우터가드를 위한 state
    isShow: false,
    accessToken: VueCookies.get("accessToken"),
    refreshToken: VueCookies.get("refreshToken"),
    playgames: false,
    myIdx: -1,
    rooms: [],
    gameinfo: [], // 게임참가자 정보저장
    myRole: undefined,
    timer: -1,
    liarAnswerModal: false,
    curSpeakIdx: "on",
    NowPageNum: 1,
    resultModal: false,
    result: [],
    liar: -1,
  },
  getters: {
    isMaster(state) {
      for (var i = 0; i < state.gameinfo.participants.length; i++) {
        if (state.gameinfo.participants[i].userId === state.gameinfo.master)
          return i;
      }
    },
    getAll(state) {
      var result = [];
      if (state.openvidu.subscribers.length === 0) {
        result.push(state.openvidu.publisher);
        console.log("결과", result);
        return result;
      }
      for (var i = 0; i < state.openvidu.subscribers.length; i++) {
        if (i === state.myIdx) {
          result.push(state.openvidu.publisher);
        }
        result.push(state.openvidu.subscribers[i]);
        console.log("결과", result);
      }
      if (state.myIdx === state.openvidu.subscribers.length) {
        result.push(state.openvidu.publisher);
      }
      console.log("결과", result);
      return result;
    },
    isLogin() {
      console.log("로그인상태getters", VueCookies.isKey("accessToken"));
      if (VueCookies.isKey("accessToken")) {
        return true;
      }
      return false;
    },
    isParticipants(state) {
      return state.gameinfo.participants;
    },
    GET_ROOM(state, idx) {
      return state.rooms[idx];
    },
    GET_ROOMS(state) {
      return state.rooms;
    },
    get_subscribers(state) {
      return state.openvidu.subscribers;
    },
  },
  mutations: {
    SET_LIAR(state, payload) {
      state.liar = payload;
    },
    DELETE_SUBSCRIBER(state, payload) {
      console.log(payload);
      if (payload >= 0) {
        state.openvidu.subscribers.splice(payload, 1);
      }
    },
    INIT_GAMEINFO(state) {
      console.log("initgameinfo-mutation");
      state.gameinfo = [];
    },
    INIT_SESSION(state) {
      console.log("initsession-mutation");
      const session = state.openvidu.session;
      if (session) session.disconnect();
    },
    SET_DELETE_OPENVIDU(state) {
      state.openvidu.OV = undefined;
      state.openvidu.session = undefined;
      state.openvidu.publisher = undefined;
      state.openvidu.subscribers = [];
    },
    SET_SUBSCRIBERS(state, payload) {
      console.log("이거 구독자여야만해", payload);
      console.log("여기다가 넣었어", state.openvidu.subscribers);
      state.openvidu.subscribers.push(payload);
      console.log("구독자에 잘 넣었어");
    },
    // 회원가입 && 로그인
    SAVE_TOKEN(state, payload) {
      console.log("토큰저장" + payload);
      // state.accessToken = payload.token;
      VueCookies.set("accessToken", payload.token);
      state.accessToken = payload.token;
      VueCookies.set("refreshToken", payload.refreshToken);
      state.refreshToken = payload.refreshToken;

      router.push({ name: "main" });
      // router.go(0);
    },
    DELETE_TOKEN(state) {
      state.accessToken = null;
      state.refreshToken = null;
      VueCookies.remove("accessToken");
      VueCookies.remove("refreshToken");
      VueCookies.remove("accessToken");
      router.push({ name: "main" }).catch(() => {});
      // router.go(0);
    },
    // 방목록 저장할 뮤테이션(rooms)
    SET_ROOMS(state, payload) {
      state.rooms = payload.rooms;
    },
    // 라우터가드를 위한 isEnter 값 변경
    SET_ISENTER(state) {
      state.isEnter = true;
    },
    // 라우터가드를 위한 isEnter 값 초기화 - 인게임에 들어가면 실행시킬것.
    RESET_ISENTER(state) {
      state.isEnter = false;
    },
    // 게임플레이 진입시 playgames 값 변경
    SET_ISPLAY(state) {
      state.playgames = true;
    },
    // 게임진입후 playgames 초기화
    RESET_ISPLAY(state) {
      state.playgames = false;
    },
    // 게임진입시 게임참가자 정보저장
    SET_GAMEINFO(state, payload) {
      console.log("mutation실행");
      console.log(payload);
      state.gameinfo = payload;
    },
    // 게임레디상태변경(ready)
    CHG_ISREADY(state, payload) {
      console.log("CHG_ISREADY 뮤테이션");
      state.gameinfo.participants[payload].isReady = true;
    },
    CHG_ISUNREADY(state, payload) {
      console.log("CHG_ISUNREADY 뮤테이션");
      state.gameinfo.participants[payload].isReady = false;
    },
    // 게임시작시(playing)
    SET_ISPLAYING(state) {
      state.gameinfo.isPlaying = true;
    },
    // 게임종료시(result창받고 난뒤)
    RESET_ISPLAYING(state) {
      state.gameinfo.isPlaying = false;
    },
    SET_MYIDX(state, payload) {
      state.myIdx = payload;
    },
    // 게임중 주제부여
    SET_ROLE(state, payload) {
      state.myRole = payload;
    },
    // 게임중 타이머세팅
    SET_TIMER(state, payload) {
      state.timer = payload;
    },
    // 타이머 0으로 세팅
    RESET_TIMER(state) {
      state.timer = 0;
    },
    ON_ANSWER(state) {
      state.liarAnswerModal = true;
    },
    OFF_ANSWER(state) {
      state.liarAnswerModal = false;
    },
    SET_CURSPEAKER(state, payload) {
      state.curSpeakIdx = payload;
      if (payload === "off") {
        for (let i = 0; i < state.gameinfo.participants.length; i++) {
          state.gameinfo.participants[i].mic = false;
        }
      } else if (payload === "on") {
        for (let i = 0; i < state.gameinfo.participants.length; i++) {
          state.gameinfo.participants[i].mic = true;
        }
      } else {
        for (let i = 0; i < state.gameinfo.participants.length; i++) {
          state.gameinfo.participants[i].mic = false;
        }
        state.gameinfo.participants[payload].mic = true;
      }
    },
    SET_PAGENUM(state, payload) {
      state.NowPageNum = payload;
    },
    SET_OPENVIDU(state, payload) {
      console.log("여기는 mutation openvidu야", payload);
      state.openvidu = payload;
      console.log("여기는 mutation openvidu야", state.openvidu);
    },
    ON_RESULT(state, payload) {
      state.resultModal = true;
      state.result = payload;
    },
    OFF_RESULT(state) {
      state.resultModal = false;
      state.result = [];
      state.mysubject = null;
    },
  },
  actions: {
    signUp(context, payload) {
      console.log("감자");
      console.log(payload);
      axios({
        method: "post",
        url: `${API_URL}/users/sign-up/`,
        headers: {
          // "Content-Type": "multipart/form-data",
        },
        data: {
          name: payload.name,
          password: payload.password,
          email: payload.email,
        },
      }).then((res) => {
        console.log(res.data);
      });
    },
    logIn(context, payload) {
      axios({
        method: "POST",
        url: `${API_URL}/users/login`,
        data: {
          email: payload.email,
          password: payload.password,
        },
      })
        .then((res) => {
          console.log(res.data);
          // context.commit("SAVE_TOKEN", res.data.key);
          // console.log(res.data.data.accessToken);
          const payload = {
            token: res.data.data.accessToken,
            refreshToken: res.data.data.refreshToken,
          };
          context.commit("SAVE_TOKEN", payload);
          // router.replace({ name: "main" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    logInKakao(context, payload) {
      // console.log(payload);
      context.commit("SAVE_TOKEN", payload);
    },
    logOut(context, payload) {
      axios({
        method: "post",
        url: `${API_URL}/users/logout`,
        headers: {
          Authorization: `Bearer ${payload.accessToken}`,
          "refresh-token": payload.refreshToken,
        },
        data: {
          accessToken: payload.accessToken,
          refreshToken: payload.refreshToken,
        },
      })
        .then((res) => {
          if (res) {
            console.log(res);
            context.commit("DELETE_TOKEN");
            // router.replace({ name: "main" });
          }
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
    },
    getRooms(context, payload) {
      axios({
        method: "get",
        // url: `${API_URL}/rooms?pageNumber=${페이지네이션 번호}`,
        url: `${API_URL}/rooms`,
        data: {
          pageNumber: payload,
        },
      })
        .then((res) => {
          // 받아온 방정보 8개는 뷰엑스 스토어로 저장할 예정
          const payload = {
            rooms: res.data,
          };
          // commit을 통해 mutations에 정의된 setRooms 호출
          context.commit("SET_ROOMS", payload);
          return this.state.rooms;
          // console.log(this.$store.state.rooms);
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
    },
    // 방목록 8개 받아오기
    setRooms(context, payload) {
      axios({
        method: "get",
        // url: `${API_URL}/rooms?pageNumber=${페이지네이션 번호}`,
        url: `${API_URL}/rooms`,
        data: {
          pageNumber: payload,
        },
      })
        .then((res) => {
          console.log("불러온 방 목록 :", res.data);
          const payload = {
            rooms: res.data,
          };
          context.commit("SET_ROOMS", payload.rooms);
        })
        .catch((err) => {
          console.log("방목록 불러오기 실패");
          console.log(err);
        });
    },
    // 방진입 토큰 true
    setIsEnter(context) {
      context.commit("SET_ISENTER");
    },
    // 방진입 직후 토큰 false
    resetIsEnter(context) {
      context.commit("RESET_ISENTER");
    },
    // 게임플레이시 playgames true
    setPlaygames(context) {
      context.commit("SET_ISPLAY");
    },
    // 게임플레이방 진입후 playgames false
    resetPlaygames(context) {
      context.commit("RESET_ISPLAY");
    },
    // 게임방 진입 및 이벤트 발생시 게임정보 저장
    setGameInfo(context, payload) {
      console.log("action실행");
      console.log(payload);
      context.commit("SET_GAMEINFO", payload);
    },
    // 게임레디상태 변경
    chgReady(context, payload) {
      context.commit("CHG_ISREADY", payload);
    },
    // 게임레디상태 변경
    chgUnReady(context, payload) {
      context.commit("CHG_ISUNREADY", payload);
    },
    // 게임플레이상태 변경
    setIsPlaying(context) {
      context.commit("SET_ISPLAYING");
    },
    // REISSUE요청
    reIssue(context, payload) {
      console.log(payload);
      axios({
        method: "POST",
        url: `${API_URL}/users/reissue`,
        headers: {
          Authorization: `Bearer ${payload.accessToken}`,
          "refresh-token": payload.refreshToken,
        },
        // data: {
        //   accessToken: payload.accessToken,
        //   refreshToken: payload.refreshToken,
        // },
      })
        .then((res) => {
          console.log("reissue 테스트중입니다. ");
          console.log(res.data);
          // const payload = {
          //   token: res.data,
          //   refreshToken: payload.refreshToken,
          // };
          // context.commit("SAVE_TOKEN", payload);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setMyIdx(context, payload) {
      console.log("디코더", jwtDecode.decode(VueCookies.get("accessToken")));
      let myid = Number(jwtDecode.decode(VueCookies.get("accessToken")).id);
      console.log(myid);
      console.log(payload);
      for (var i = 0; i < payload.participants.length; i++) {
        if (myid === payload.participants[i].userId) {
          context.commit("SET_MYIDX", i);
          break;
        }
      }
      // console.log("setMyIdx에서 :", payload);
      // context.commit("SET_MYIDX", payload.participants.length - 1);
      // console.log(
      //   "setmyIdx에서 myidx에 저장되는 값 : ",
      //   payload.participants.length - 1
      // );
    },
    setSubscribers(context, payload) {
      console.log("와야하는데..");
      context.commit("SET_SUBSCRIBERS", payload);
    },
    setOpenvidu(context, payload) {
      context.commit("SET_OPENVIDU", payload);
    },
    deleteSubscriber(context, payload) {
      context.commit("DELETE_SUBSCRIBER", payload);
    },
    initSession(context) {
      console.log("initsession-actions");
      console.log(this.state.gameinfo);
      if (this.state.gameinfo.length !== 0)
        axios({
          method: "patch",
          url: `${this.state.API_URL}/rooms/${this.state.gameinfo.roomId}/exit`,
          headers: {
            Authorization: `Bearer ${VueCookies.get("accessToken")}`,
          },
        })
          .then((res) => {
            console.log(res);
            context.commit("INIT_SESSION");
            context.commit("INIT_GAMEINFO");
            context.commit("SET_DELETE_OPENVIDU");
          })
          .catch((err) => {
            console.log("실패");
            console.log(err);
          });
    },
    setDeleteOpenvidu(context) {
      context.commit("SET_DELETE_OPENVIDU");
    },
    ban(context, payload) {
      const index = Number(payload);
      axios({
        method: "patch",
        url: `${this.state.API.URL}/rooms/${this.state.gameinfo.roomId}/ban/${this.state.gameinfo.participants[index].userId}`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
    },
  },
});
