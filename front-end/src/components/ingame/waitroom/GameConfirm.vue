<template>
  <div class="m-0 p-0 confirm">
    <div class="m-0 p-0 row" style="height: 100%">
      <!-- <div class="m-0 p-0 col-2"><button type="button" @click="toggle()" class="btn btn-success btn-lg" id="btn_ready">Ready</button></div> -->
      <div class="m-0 p-0 col-2">
        <button
          type="button"
          v-on:click="isReady()"
          class="btn btn-success btn-lg"
          id="btn_ready"
        >
          Ready
        </button>
      </div>

      <div class="m-0 p-0 col-5"></div>
      <div class="m-0 p-0 col-3"></div>
      <div class="m-0 p-0 col-2">
        <router-link :to="{ path: 'InLobby' }"
          ><button type="button" class="btn btn-danger btn-lg" id="btn_quit">
            Quit
          </button></router-link
        >
      </div>
    </div>
  </div>
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

<style>
#btn_ready {
  width: fit-content;
  margin: auto;
}
#btn_quit {
  width: fit-content;
  margin: auto;
}
</style>
