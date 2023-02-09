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
  <!-- Modal -->
  <!-- <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          ...
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Understood</button>
        </div>
      </div>
    </div>
  </div> -->
  <div
    class="modal fade"
    id="staticBackdrop"
    tabindex="-1"
    aria-labelledby="createRoomModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="createRoomModalLabel">게임생성</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            v-on:click="resetval"
          ></button>
        </div>
        <div class="modal-body" style="text-align: left">
          <ul style="list-style: none" class="m-0 p-0 gameoption">
            <li>방 제목</li>
            <li>
              <input type="text" class="form-control" v-model="roomtitle" />
            </li>
            <li>
              비밀번호
              <input type="checkbox" v-model="roomchk" v-on:click="chksecret" />
            </li>
            <li>
              <input
                type="text"
                v-bind:disabled="!roomchk"
                class="form-control"
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
                    <option v-bind:value="4">4명</option>
                    <option v-bind:value="5">5명</option>
                    <option v-bind:value="6">6명</option>
                    <option v-bind:value="7">7명</option>
                    <option v-bind:value="8">8명</option>
                    <option v-bind:value="9">9명</option>
                    <option v-bind:value="10">10명</option>
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
                    <option v-bind:value="15">15초</option>
                    <option v-bind:value="20">20초</option>
                    <option v-bind:value="25">25초</option>
                    <option v-bind:value="30">30초</option>
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
// import { mapState } from "vuex";
import MainChat from "../components/ingame/MainChat.vue";
import MainGame from "../components/ingame/MainGame.vue";
import axios from "axios";
import { NativeEventSource, EventSourcePolyfill } from "event-source-polyfill";
import router from "@/router";

export default {
  name: "InGame",
  components: {
    MainChat,
    MainGame,
  },
  data() {
    return {
      roomId: this.$route.params.roomId,
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
          const gameinfo = JSON.parse(JSON.parse(event.data).value);
          this.$store.dispatch("setGameInfo", gameinfo);
        }
        // 게임시작시 isPlaying == true
        if (type === "message") {
          if (val === "game start") {
            this.$store.dispatch("setIsPlaying");
          }
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
