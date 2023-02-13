<template>
  <div class="m-0 p-0 playgamewrapper" style="width: 100%; height: 100vh">
    <div class="m-0 p-0 row">
      <div class="m-0 p-0 col-1"></div>
      <div class="m-0 p-0 col-8">
        <main-game
          style="width: 100%; height: 100vh"
          v-bind:playgame="playgame"
        ></main-game>
      </div>
      <div class="m-0 p-0 col-2">
        <main-chat style="width: 100%; height: 100vh"></main-chat>
      </div>
      <div class="m-0 p-0 col-1"></div>
    </div>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import MainChat from "@/components/ingame/MainChat.vue";
import MainGame from "@/components/ingame/MainGame.vue";

export default {
  name: "PlayRoom",
  components: {
    MainChat,
    MainGame,
  },
  data() {
    return {
      playgame: true,
    };
  },
};
</script>

<style scoped>
.playgamewrapper {
  background-image: url(@/assets/ingame/ingame_bg.jpg);
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
