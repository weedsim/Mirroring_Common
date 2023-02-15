<template>
  <modal v-if="showModal" v-on:close="isStart" />
  <div style="display: flex; padding-top: 3vh">
    <div
      class=""
      style="
        width: 40%;
        display: flex;
        flex-direction: row-reverse;
        align-items: center;
        padding-right: 40px;
      "
    >
      <button class="circle">
        <!-- 마이크 이미지 -->
        <img src="@/assets/ingame/mic32-on.png" alt="" class="tmpImg" />
      </button>
      <button class="circle">
        <!-- 비디오 이미지 -->
        <img src="@/assets/ingame/video30-on.png" alt="" class="tmpImg" />
      </button>
    </div>
    <div class="" style="display: flex; align-items: center">
      <button class="btn-ready" v-on:click="isReady()" v-if="!Master">
        준비
      </button>
      <button class="btn-ready" v-on:click="onSubjectModal()" v-else>
        시작
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import jwtDecode from "vue-jwt-decode";
import VueCookies from "vue-cookies";
import Modal from "./SubjectModal.vue";

import { mapState } from "vuex";
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
    ...mapState(["API_URL"]),
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
      this.$store.state.gameinfo.participants[myIdx].isReady =
        !this.$store.state.gameinfo.participants[myIdx].isReady;
    },
    isStart() {
      this.showModal = false;
      console.log("게임~ 시작~~~하겠습니다~!");
      console.log("게임방정보", this.$store.state.gameinfo);
      axios({
        method: "POST",
        url: `${this.API_URL}/rooms/${this.$store.state.gameinfo.roomId}/games/start`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
        data: {},
      })
        .then((res) => {
          console.log("게임시작성공.");
          console.log(res.data);
        })
        .catch((err) => {
          console.log("시작실패");
          console.log(err);
        });
    },
    onSubjectModal() {
      this.showModal = true;
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
  width: 50px;
  height: 50px;
  border-radius: 25px;
  margin-left: 10px;
}
.tmpImg {
  display: block;
  margin: 0px auto;
  padding: 0px;
  width: 30px;
}
</style>
