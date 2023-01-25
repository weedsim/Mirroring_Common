<template>
  <Navbar />
  <!-- <div class="BigLobby"> -->
  <div class="BigLobby" style="width: 100%; height: 95vh">
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8">
        <main-lobby style="width: 100%; height: 78vh"></main-lobby>
      </div>
      <div class="col-2"></div>
    </div>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import MainLobby from "@/components/lobby/MainLobby.vue";
import Navbar from "@/components/NavView.vue";

export default {
  name: "Lobby",
  components: {
    MainLobby,
    Navbar,
  },
};
</script>

<style scoped>
.BigLobby {
  background-image: url(../../assets/lobby/lobby.png);
  background-size: 102vw 100vh;
  background-repeat: no-repeat;
  background-position-x: -10px;
}
div {
  margin: 0px;
  padding: 0px;
}
</style>
