<template>
  <navbar />
  <div class="m-0 p-0 mainlobby">
    <outer-container363></outer-container363>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import navbar from "@/components/NavView.vue";
import MainLobby from "@/components/lobby/MainLobby.vue";
import OuterContainer363 from "@/components/OuterContainer363.vue";
import VueCookies from "vue-cookies";

export default {
  name: "InLobby",
  components: {
    navbar,
    MainLobby,
    OuterContainer363,
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

<style>
.mainlobby {
  background-image: url(../../assets/lobby/lobby.jpg);
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
