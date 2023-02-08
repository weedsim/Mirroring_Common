<template>
  <div
    class="mt-2 pt-4"
    style="width: 40%; display: flex; justify-content: flex-end"
  >
    <button class="circle me-3 p-0">
      <BIconVolumeUpFill
        style="color: black; font-size: 1.5vw"
      ></BIconVolumeUpFill>
    </button>
    <button class="circle">
      <BIconCameraVideoOffFill
        style="color: black; font-size: 1.2vw"
      ></BIconCameraVideoOffFill>
    </button>
  </div>
  <div class="m-0 pt-4" style="width: 40%">
    <button class="btn-ready">준비</button>
  </div>
  <div class="m-0 p-0 col-4"></div>
</template>

<script>
import axios from "axios";
// import store from '@/store';
// import { useStore } from "vuex";

export default {
  // setup() {
  //   const store = useStore();
  //   // const toggle = store.commit("isShow", store.state.isShow = !store.state.isShow)
  // },
  data() {
    return {
      API_URL: this.$store.state.API_URL,
    };
  },

  name: "GameConfirm",
  created() {
    // this.toggle = store.commit("isShow", store.state.isShow = !store.state.isShow)
  },
  methods: {
    toggle() {
      // console.log(this.$store.state.isShow)
      //   this.$store.state.isShow = !this.$store.state.isShow
      let myIdx = this.$store.state.myIdx;
      // console.log(myIdx)
      this.$store.state.sessions[myIdx].isReady =
        !this.$store.state.sessions[myIdx].isReady;
    },
    isReady() {
      console.log(this.$store.state.gameinfo);
      console.log(this.$store.state.gameinfo.roomId);
      axios({
        method: "PATCH",
        // url: `${API_URL}/rooms`,
        url: `${this.API_URL}/rooms/${this.$store.state.gameinfo.roomId}/ready`,
        headers: {
          Authorization: `Bearer ${this.$store.state.accessToken}`,
        },
        data: {},
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log("레디실패");
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.btn-ready {
  width: 10vw;
  height: 6vw;
  background-color: rgba(158, 158, 158, 30%);
  border: none;
  border-radius: 10px;
  font-size: 1.5vw;
  font-weight: 400;
  color: white;
}
.circle {
  width: 3vw;
  height: 3vh;
  border-radius: 1.5vw;
}
</style>
