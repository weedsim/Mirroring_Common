<template>
  <div class="title-container" style="display: flex">
    <div class="room-number">{{ $store.state.gameinfo.roomId }}</div>
    <div class="room-title">{{ $store.state.gameinfo.title }}</div>
  </div>
  <div class="setting-container">
    <router-link :to="{ path: 'InLobby' }">
      <button class="btn-exit" v-on:click="chkinfo">나가기</button>
    </router-link>
    <div class="btn-setting" v-on:click="test">방 설정 변경</div>
  </div>
  <!-- <ResultModal v-if="resultShow" v-on:close="offModal" /> -->
</template>

<script>
// import ResultModal from "@/components/ingame/playgame/ResultModal.vue";

export default {
  name: "GameTitle",
  components: {
    // ResultModal,
  },
  data() {
    return {
      resultShow: false,
    };
  },
  created() {},
  methods: {
    test() {
      console.log("test!!!~");
      this.resultShow = true;
    },
    offModal() {
      this.resultShow = false;
    },
    chkinfo() {
      console.log(this.$store.state.openvidu);
    },
  },
};
</script>

<style scoped>
.title-container {
  display: flex;
  justify-content: center;
  margin-top: 5vh;
}
.title {
  width: 50vh;
  height: 7vh;
}
#icon_setting {
  width: 30%;
  height: 20px;
  margin-left: 15px;
  color: white;
}
.btn-setting {
  color: white;
  background-color: none;
  margin-bottom: 1vh;
}
.btn-setting:hover {
  cursor: pointer;
}
.room-number {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 6vw;
  height: 6vh;
  background-color: #909090;
  background-color: rgba(144, 144, 144, 80%);
  border-top-left-radius: 3vh;
  border-bottom-left-radius: 3vh;
  color: white;
  font-size: 3vh;
  text-align: center;
}
.setting-container {
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
}
.room-title {
  display: flex;
  align-items: center;
  width: 20vw;
  background-color: rgba(255, 255, 255, 80%);
  border-top-right-radius: 3vh;
  border-bottom-right-radius: 3vh;
  color: black;
  font-size: 2.5vh;
  font-weight: 500;
  padding-left: 1vw;
}
.btn-exit {
  height: 3vh;
  background-color: rgba(158, 158, 158, 30%);
  border: none;
  margin-left: 10px;
  margin-right: 30px;
  margin-bottom: 1vh;
  border-radius: 10px;
  font-weight: 400;
  color: white;
}
</style>
