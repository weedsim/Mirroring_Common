<template>
  <div class="m-0 p-0 about"  style="width:100%;height:100vh;">
    <div class="m-0 p-0 row">
        <div class="m-0 p-0 col-1">
        </div>
        <div class="m-0 p-0 col-7">
            <main-game style="width:100%;height:100vh;"></main-game>
        </div>
        <div class="m-0 p-0 col-3">
            <main-chat style="width:100%;height:100vh;"></main-chat>
        </div>
        <div class="m-0 p-0 col-1">
        </div>
    </div>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import MainChat from "../components/ingame/MainChat.vue";
import MainGame from "../components/ingame/MainGame.vue";

export default {
    name:"InGame",
    components: {
    MainChat, MainGame
  }
}




</script>

<style scoped>
.about{
  background-image: url(../assets/ingame/ingame_bg.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  opacity: 0.8;
}


</style>
