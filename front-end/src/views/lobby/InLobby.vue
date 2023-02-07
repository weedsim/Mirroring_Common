<template>
  <navbar />
  <div class="m-0 p-0 mainlobby">
    <outer-container></outer-container>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import navbar from "@/components/NavView.vue";
import MainLobby from "@/components/lobby/MainLobby.vue";
import OuterContainer from "@/components/OuterContainer.vue";
import VueCookies from "vue-cookies";

export default {
  name: "InLobby",
  components: {
    navbar,
    MainLobby,
    OuterContainer,
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
