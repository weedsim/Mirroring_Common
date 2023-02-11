<template>
  <div class="m-0 p-0 about" style="width: 100%; height: 100vh">
    <div class="m-0 p-0 row">
      <div class="m-0 ps-5 pe-4 col-9">
        <main-game style="width: 100%; height: 100vh"></main-game>
      </div>
      <div class="m-0 pe-4 col-3">
        <main-chat style="width: 100%; height: 100vh"></main-chat>
      </div>
    </div>
  </div>
  <SettingModal />
</template>

<script>
// import { mapState } from "vuex";
import MainChat from "../components/ingame/MainChat.vue";
import MainGame from "../components/ingame/MainGame.vue";
import axios from "axios";
import { NativeEventSource, EventSourcePolyfill } from "event-source-polyfill";
import router from "@/router";
import SettingModal from "@/components/ingame/waitroom/SettingModal.vue";

export default {
  name: "InGame",
  components: {
    MainChat,
    MainGame,
    SettingModal,
  },
  data() {
    return {
      roomId: "1",
      API_URL: this.$store.state.API_URL,
      message: "",
    };
  },
  created() {
    this.linkSSE();
  },
  mounted() {},
  // 이방들어올때 룸ID가 있어야함. 이거받고.
  methods: {
    linkSSE() {
      // console.log(this.$store.state.accessToken);
      const headers = {
        Authorization: "Bearer " + this.$store.state.accessToken,
      };
    //   const source = new EventSourcePolyfill(
    //     `${this.API_URL}/sse/connect?roomId=${this.roomId}`,
    //     // 기본45000 -> 1시간으로 변경
    //     { headers, heartbeatTimeout: 1000 * 60 * 60 }
    //   );
    //   console.log(source);

    //   // 소스저장
    //   this.$store.dispatch("setSource", source);
      console.log("vuex source", this.$store.state.sessions);

    //   source.addEventListener("message", (event) => {
    //     // console.log(JSON.parse (event.data));
    //     console.log("event.data", JSON.parse(event.data));
    //     const type = JSON.parse(event.data).type;
    //     const val = JSON.parse(event.data).value;
    //     // 레디상태
    //     if (type === "ready") {
    //       this.$store.dispatch("chgReady", Number(val));
    //     }
    //     // 언레디상태
    //     if (type === "unready") {
    //       this.$store.dispatch("chgUnReady", Number(val));
    //     }
    //     // 방정보 업데이트
    //     if (type === "room") {
    //       const gameinfo = JSON.parse(JSON.parse(event.data).value);
    //       this.$store.dispatch("setGameInfo", gameinfo);
    //     }
    //     // 게임시작시 isPlaying == true
    //     if (type === "message") {
    //       if (val === "game start") {
    //         this.$store.dispatch("setIsPlaying");
    //       }
    //     }
    //   });
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
          Authorization: `Bearer ${this.$store.state.accessToken}`,
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
.about {
  background-image: url(../assets/ingame/ingame_bg.jpg);
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
