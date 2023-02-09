<template>
  <modal v-if="showModal" v-on:close="closeModal" />
  <div
    class="mt-3 pt-4"
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
    <button class="btn-ready" v-on:click="isReady()" v-if="!Master">
      준비
    </button>
    <button class="btn-ready" v-on:click="isStart()" v-else>시작</button>
  </div>
</template>

<script>
import axios from "axios";
import jwtDecode from "vue-jwt-decode";
import VueCookies from "vue-cookies";
import Modal from "./SubjectModal.vue";

// import store from '@/store';
// import { useStore } from "vuex";

export default {
  components: {
    Modal,
  },
  // setup() {
  //   const store = useStore();
  //   // const toggle = store.commit("isShow", store.state.isShow = !store.state.isShow)
  // },
  data() {
    return {
      API_URL: this.$store.state.API_URL,
      Master: false,
      showModal: false,
    };
  },

  name: "GameConfirm",
  created() {
    // this.toggle = store.commit("isShow", store.state.isShow = !store.state.isShow)
    this.isMaster();
  },
  computed: {
    // isMaster() {
    //   if (
    //     this.$store.state.gameinfo.master ===
    //     jwtDecode.decode(VueCookies.get("accessToken")).id
    //   ) {
    //     return true;
    //   }
    //   return false;
    // },
  },
  methods: {
    closeModal() {
      this.showModal = false;
    },
    isMaster() {
      // if (
      //   this.$store.state.gameinfo.master ===
      //   jwtDecode.decode(VueCookies.get("accessToken")).id
      // )
      if (
        this.$store.state.gameinfo.master ===
        jwtDecode.decode(VueCookies.get("accessToken")).id
      ) {
        this.Master = true;
      }
    },
    toggle() {
      // console.log(this.$store.state.isShow)
      //   this.$store.state.isShow = !this.$store.state.isShow
      let myIdx = this.$store.state.myIdx;
      // console.log(myIdx)
      this.$store.state.sessions[myIdx].isReady =
        !this.$store.state.sessions[myIdx].isReady;
    },
    isStart() {
      console.log("start버튼 누름요!");
      this.showModal = true;
      // axios({
      //   method: "POST",
      //   url: `${this.API_URL}/rooms/${this.$store.state.gameinfo.roomId}/games/start`,
      //   headers: {
      //     Authorization: `Bearer ${VueCookies.get("accessToken")}`,
      //   },
      //   data: {},
      // })
      //   .then((res) => {
      //     console.log(res.data);
      //     console.log(this.$store.state.gameinfo);
      //     console.log(this.$store.state.sessions);
      //   })
      //   .catch((err) => {
      //     console.log("시작실패");
      //     console.log(err);
      //   });
    },
    isReady() {
      console.log("ready버튼 누름요!");
      axios({
        method: "PATCH",
        // url: `${API_URL}/rooms`,
        url: `${this.API_URL}/rooms/${this.$store.state.gameinfo.roomId}/ready`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
        data: {},
      })
        .then((res) => {
          console.log(res.data);
          console.log(this.$store.state.gameinfo);
          console.log(this.$store.state.sessions);
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
  width: 28vh;
  height: 8vh;
  background-color: rgba(158, 158, 158, 30%);
  border: none;
  border-radius: 10px;
  font-size: 1.5vw;
  font-weight: 400;
  color: white;
}
.circle {
  width: 5vh;
  height: 5vh;
  border-radius: 2.5vh;
}
</style>
