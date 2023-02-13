<template>
  <navbar />
  <div class="mainlobby">
    <div class="lobby-container">
      <lobby-header></lobby-header>
      <lobby-main></lobby-main>
      <lobby-footer></lobby-footer>
    </div>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import navbar from "@/components/NavView.vue";
import LobbyHeader from "@/components/lobby/LobbyHeader.vue";
import LobbyMain from "@/components/lobby/LobbyMain.vue";
import LobbyFooter from "@/components/lobby/LobbyFooter.vue";
import VueCookies from "vue-cookies";

export default {
  name: "InLobby",
  components: {
    navbar,
    LobbyHeader,
    LobbyMain,
    LobbyFooter,
  },
  created() {
    this.getReIssue();
  },
  methods: {
    getReIssue() {
      const payload = {
        accessToken: VueCookies.get("accessToken"),
        refreshToken: VueCookies.get("refreshToken"),
      };
      this.$store.dispatch("reIssue", payload);
    },
  },
};
</script>

<style scoped>
.mainlobby {
  background-image: url(../../assets/lobby/lobby.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  height: 92vh;
  display: flex;
  justify-content: center;
}
.lobby-container {
  width: 50vw;
}
</style>
