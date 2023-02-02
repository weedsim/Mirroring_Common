<template>
  <div
    class="modal fade"
    id="createRoomModal"
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
          <div v-if="gamemode === 'normal'" class="speech-bubble m-4">
            <h4>클래식모드</h4>
            <p>라이어는 1명인 기본 모드</p>
          </div>

          <div v-else-if="gamemode === 'spy'" class="speech-bubble-two m-4">
            <h4>스파이모드</h4>
            <p>라이어 1명, 스파이 1명인 모드</p>
          </div>

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
import axios from "axios";
import router from "@/router";

// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";

export default {
  components: {},
  data() {
    return {
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
    resetval() {
      (this.gamemode = "normal"), // 게임모드 기본설정
        (this.roomchk = false), // 비밀번호 체크여부
        (this.roompwd = null), // 게임방 비밀번호
        (this.roomtitle = null), // 게임방 제목
        (this.playercnt = null), // 참가인원
        (this.talktime = null); // 발언시간
    },
    // 비빌번호 체크 해제시 초기화
    chksecret() {
      if (this.roomchk) {
        this.roompwd = null;
      }
    },
    // 생성버튼 클릭시 이벤트
    createGame() {
      // console.log(this.roompwd);
      // console.log(this.roomtitle);
      // console.log(this.playercnt);
      // console.log(this.talktime);
      // console.log(this.gamemode);
      // console.log(`${this.$store.state.accessToken}`);
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
            Authorization: `Bearer ${this.$store.state.accessToken}`,
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
            // 만약 성공을했다면... room/${roomId}로 인게임.vue로 보낸다.
            // 1. state 방진입 isEnter -> true 단, 방진입직후에는 isEnter를 false로 바꿔줘야된다.
            this.roomPwd = null;
            this.$store.dispatch("setIsEnter");
            // 응답결과로는 토큰과 roomId가 올것이다.
            // router.push({ name: "room", params: { roomId: 1 } });
            // 테스트용으로는 임의로 roomId를 설정한다.
            router.push({ name: "room", params: { roomId: res.data.roomId } });
          })
          .catch((err) => {
            this.roomPwd = null;

            console.log("실패");
            console.log(err);
            // 테스트용
            // this.$store.dispatch("setIsEnter");
            // router.push({ name: "room", params: { roomId: 13 } });
            alert("게임방 진입에 실패하셨습니다");
          });
      }
    },
  },
};
</script>

<style scoped>
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
  top: 0;
  left: 50%;
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
  top: 0;
  left: 50%;
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
</style>
