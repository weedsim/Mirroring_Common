<template>
  <div v-bind:class="[{ citizen: iscitizen }, { liar: !iscitizen }]">
    <div style="width: 12.5vw"></div>
    <div style="width: 75vw">
      <main-game style="height: 100vh"></main-game>
    </div>
    <div style="width: 12.5vw"></div>
  </div>
  <SettingModal v-if="setting === true" />
  <AnswerModal v-if="isshow === true" />
  <ResultModal v-if="isresult === true" />
</template>

<script>
// import { mapState } from "vuex";
import MainChat from "../components/ingame/MainChat.vue";
import MainGame from "../components/ingame/MainGame.vue";
import SettingModal from "@/components/ingame/SettingModal.vue";
import axios from "axios";
import { mapState, mapGetters, mapActions } from "vuex";
import { NativeEventSource, EventSourcePolyfill } from "event-source-polyfill";
import router from "@/router";
import AnswerModal from "@/components/ingame/playgame/AnswerModal.vue";
import VueCookies from "vue-cookies";
import ResultModal from "@/components/ingame/playgame/ResultModal.vue";

export default {
  name: "InGame",
  components: {
    MainChat,
    MainGame,
    AnswerModal,
    SettingModal,
    ResultModal,
  },
  data() {
    return {
      roomId: this.$route.params.roomId,
      message: "",
      isshow: false,
      iscitizen: true,
      isresult: false,
    };
  },
  created() {
    this.linkSSE();
  },
  computed: {
    chkShow() {
      return this.$store.state.liarAnswerModal;
    },
    chkRole() {
      return this.$store.state.mysubject;
    },
    chkresult() {
      return this.$store.state.resultModal;
    },

    ...mapState["API_URL"],
  },
  watch: {
    chkShow(newVal) {
      this.isshow = newVal;
    },
    chkresult(newVal) {
      this.isresult = newVal;
    },
    chkRole(newVal) {
      if (newVal === "liar") {
        this.iscitizen = false;
      } else {
        this.iscitizen = true;
      }
    },
  },
  // 이방들어올때 룸ID가 있어야함. 이거받고.
  methods: {
    linkSSE() {
      // console.log(this.$store.state.accessToken);
      const headers = {
        Authorization: "Bearer " + VueCookies.get("accessToken"),
      };
      const source = new EventSourcePolyfill(
        `${this.API_URL}/sse/connect?roomId=${this.roomId}`,
        // 기본45000 -> 1시간으로 변경
        { headers, heartbeatTimeout: 1000 * 60 * 60 }
      );

      source.addEventListener("message", (event) => {
        // console.log(JSON.parse(event.data));
        console.log(JSON.parse(event.data));
        const type = JSON.parse(event.data).type;
        const val = JSON.parse(event.data).value;
        // 레디상태
        if (type === "ready") {
          this.$store.dispatch("chgReady", Number(val));
        }
        // 언레디상태
        if (type === "unready") {
          this.$store.dispatch("chgUnReady", Number(val));
        }
        // 방정보 업데이트
        if (type === "room") {
          console.log("room정보가 왔어요");
          const gameinfo = JSON.parse(val);
          this.$store.dispatch("setGameInfo", gameinfo);
        }
        // 게임시작시 isPlaying == true
        if (type === "message") {
          if (val === "game start") {
            this.$store.dispatch("setIsPlaying");
            this.$store.commit("SET_CURSPEAKER", "off");
          }

          if (val === "vote start") {
            console.log("투표를 추합중입니다 - 전체발언가능 on.");
            this.$store.commit("SET_CURSPEAKER", "on");
          }

          if (val === "vote end") {
            console.log("투표가 끝났습니다.");
          }
          if (val === "write answer") {
            console.log("라이어는 정답을 입력해주세요.");
            this.$store.commit("ON_ANSWER");
          }
        }

        // 주제어를 받는다면
        if (type === "role") {
          console.log("역할이 들어왔어요~!", val);
          console.log("json.parse했음", JSON.parse(val));
          this.$store.commit("SET_ROLE", JSON.parse(val));
        }

        // 타이머 설정
        if (type === "time") {
          console.log("타이머 시간은 " + val);
          console.log(this.$store.state.timer);
          this.$store.commit("SET_TIMER", Number(val));
          console.log(this.$store.state.timer);
        }

        // 발언자 설정
        if (type === "curSpeaker") {
          console.log("지금발언자의idx는 " + val);
          this.$store.commit("SET_CURSPEAKER", Number(val));
        }

        // 결과물 받기
        if (type === "result") {
          console.log("결과" + val);
          this.$store.commit("ON_RESULT", JSON.parse(val)); // 결과물 모달 온
          this.$store.commit("OFF_ANSWER"); // 결과물 받으면 정답입력창 off
        }
      });
    },
    chgflag() {
      this.$store.dispatch("setPlaygames");
      // SSE 받는 방법
    },
    startTest() {
      axios({
        method: "POST",
        // url: `${this.API_URL}/rooms/${roomId}/enter`,
        url: `${this.API_URL}/rooms/${this.roomId}/games/start`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
        data: {},
      })
        .then((res) => {
          console.log(res.data);
          // this.$store.dispatch("setPlaygames");
          // router.push({ name: "room", params: { roomId: res.data.roomId } });
          // router.push({ name: "room", params: { roomId: this.roomId } });
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
      this.chgflag();
    },
  },
};
</script>

<style scoped>
.citizen {
  background-image: url(../assets/ingame/ingame_bg.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  display: flex;
  height: 100vh;
}
.liar {
  background-image: url(../assets/ingame/ingame_bg_liar.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  display: flex;
  height: 100vh;
}
</style>
