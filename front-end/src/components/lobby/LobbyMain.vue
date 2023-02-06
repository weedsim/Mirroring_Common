<template>
  <div class="m-0 p-0 main">
    <div class="m-0 p-0 row" style="height: 2.5%"></div>
    <div class="m-0 p-0 row" style="height: 20%">
      <!-- 뷰엑스 스토어에서 방정보 8개를 인덱스 형식으로 받아서 강제로 방 아이디를 각 룸카드에 넘김 -->
      <RoomCard v-bind:roomIdx="0"></RoomCard>
      <RoomCard v-bind:roomIdx="1"></RoomCard>
    </div>
    <div class="m-0 p-0 row" style="height: 5%"></div>
    <div class="m-0 p-0 row" style="height: 20%">
      <RoomCard v-bind:roomIdx="2"></RoomCard>
      <RoomCard v-bind:roomIdx="3"></RoomCard>
    </div>
    <div class="m-0 p-0 row" style="height: 5%"></div>
    <div class="m-0 p-0 row" style="height: 20%">
      <RoomCard v-bind:roomIdx="4"></RoomCard>
      <RoomCard v-bind:roomIdx="5"></RoomCard>
    </div>

    <div class="m-0 p-0 row" style="height: 5%"></div>
    <div class="m-0 p-0 row" style="height: 20%">
      <RoomCard v-bind:roomIdx="6"></RoomCard>
      <RoomCard v-bind:roomIdx="7"></RoomCard>
    </div>
    <div class="m-0 p-0 row" style="height: 2.5%"></div>
  </div>
</template>

<script>
import RoomCard from "@/components/lobby/RoomCard.vue";
import axios from "axios";

// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";

export default {
  components: { RoomCard },
  name: "LobbyMain",
  data() {
    return {
      NowPageNum: 1,
      API_URL: this.$store.state.API_URL,
    };
  },
  created() {
    this.getRooms();
  },
  methods: {
    // 게임방 목록을 받아오는 메서드
    getRooms() {
      axios({
        method: "get",
        // url: `${API_URL}/rooms?pageNumber=${페이지네이션 번호}`,
        url: `${this.API_URL}/rooms`,
        data: {
          pageNumber: `${this.NowPageNum}`,
        },
      })
        .then((res) => {
          console.log(res.data);
          // 받아온 방정보 8개는 뷰엑스 스토어로 저장할 예정
          const payload = {
            rooms: res.data,
          };
          // 우선 미작동
          this.$store.dispatch("setRooms", payload);
          // console.log(this.$store.state.rooms);
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
