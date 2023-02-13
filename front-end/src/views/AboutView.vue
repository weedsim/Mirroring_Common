<template>
  <div class="about" style="width: 100%; height: 100vh">
    <div class="row">
      <div class="col-1"></div>
      <div class="col-8">
        <main-game style="width: 100%; height: 100vh"></main-game>
      </div>
      <div class="col-2">
        <main-chat style="width: 100%; height: 100vh"></main-chat>
      </div>
      <div class="col-1"></div>
    </div>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import MainChat from "../components/ingame/MainChat.vue";
import MainGame from "../components/ingame/MainGame.vue";

export default {
  name: "AboutView",
  components: {
    MainChat,
    MainGame,
  },
};
</script>
