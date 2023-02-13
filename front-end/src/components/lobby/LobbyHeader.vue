<template>
  <div class="rooms">방 목록</div>
  <div class="refresh-wrapper">
    <BIconArrowCounterclockwise
      class="btn-refresh"
    ></BIconArrowCounterclockwise>
    <div class="room-enter-wrapper">
      <div class="text-white lobby-text">방 번호로 입장</div>
      <input class="lobby lobby-input" />
      <button class="lobby lobby-back lobby-enter">입장</button>
    </div>
  </div>
</template>

<script>
import RoomComp from "@/components/lobby/RoomComp.vue";
import { BIconArrowCounterclockwise } from "bootstrap-icons-vue";

export default {
  name: "LobbyHeader",
  components: { BIconArrowCounterclockwise },
  created() {},
};
</script>

<style scoped>
.rooms {
  font-size: 4vh;
  color: rgb(207, 207, 207);
  padding-top: 3vh;
  text-align: center;
  margin-bottom: 1vh;
}
.btn-refresh {
  color: white;
  font-size: 3.5vh;
}
.refresh-wrapper {
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
  align-items: center;
}
.room-enter-wrapper {
  display: flex;
}
.lobby-input {
  width: 4vw;
  margin-left: 1vw;
  margin-right: 0.5vw;
}
.lobby-enter {
  width: 3vw;
}
.lobby-text {
  font-size: 2vh;
}
</style>
