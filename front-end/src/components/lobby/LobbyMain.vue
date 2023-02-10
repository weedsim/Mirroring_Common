<template>
  <div class="rooms">
    <div class="rooms-wrapper">
      <div
        class="room"
        v-for="(room, index) in rooms"
        :key="index"
        v-on:click="joinRoom(index)"
      >
        <div class="card">
          <div class="card-image-wrapper">
            <img class="card-image" src="@/assets/home/mode-one.jpg" />
          </div>
          <div>
            <div class="room-id-title">
              <div>No.{{ room.id }}</div>
              <div>{{ room.title }}</div>
            </div>
            <div>{{ room.curCount }} / {{ room.maxCount }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";
import axios from "axios";

export default {
  name: "LobbyMain",
  data() {
    return {
      NowPageNum: 1,
      API_URL: this.$store.state.API_URL,
      rooms: [],
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
          pageNumber: this.$route.query.page,
        },
      })
        .then((res) => {
          // 받아온 방정보 8개는 뷰엑스 스토어로 저장할 예정
          console.log(res.data);
          this.rooms = res.data;
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
    },
    joinRoom() {},
  },
};
</script>

<style scoped>
.rooms {
  height: 65vh;
  border: 1px solid red;
}
.rooms-wrapper {
  display: flex;
  flex-wrap: wrap;
}
.room {
  height: 13vh;
  border: 1px solid red;
  padding: 10px;
  width: 50%;
}
.room-id-title {
  width: 100%;
  display: flex;
}
.roomwrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
.card-image-wrapper {
  padding: 2vh;
}
.card {
  display: flex;
  border: 2px solid #5b3700;
  background-color: #949494;
  border-radius: 20px;
  width: 100%;
  height: 100%;
}
.card-image {
  height: 100%;
}
</style>
