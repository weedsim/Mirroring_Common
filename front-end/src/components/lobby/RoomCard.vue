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

// const API_URL = "http://127.0.0.1:8080";
const API_URL = "http://i8a706.p.ssafy.io:8080";

export default {
  name: "RoomCard",
  props: ["roomIdx"],
  components: {},
  data() {
    return {
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
  created() {},
  mounted() {
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
  computed: {},
  methods: {
    resetRoomData() {
      this.rommPwd = null;
    },
    // 게임방 진입 메서드
    // 1. patch로 방진입을 '시도'한다.
    // 2. 응답결과가 성공일 경우에는 해당 게임방 room/{roomId}로 보내준다.
    // 3. 응답결과가 실패일 경우에는 방진입 실패 alert창을 띄워준다.
    joinRoom(roomId) {
      axios({
        method: "patch",
        url: `${this.API_URL}/rooms/${roomId}/enter`,
        data: {
          password: this.rommPwd,
        },
      })
        .then((res) => {
          console.log(res.data);
          // 만약 성공을했다면... room/${roomId}로 인게임.vue로 보낸다.
          // 1. state 방진입 isEnter -> true 단, 방진입직후에는 isEnter를 false로 바꿔줘야된다.
          this.roomPwd = null;
          this.$store.dispatch("setIsEnter");
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
