<template>
  <div class="m-0 p-0 lobby">
    <!-- <div class="m-0 p-0 row" style="height: 20%; background-color: red"> -->
    <div class="m-0 p-0 row" style="height: 20%">
      <lobby-header></lobby-header>
    </div>
    <!-- <div class="m-0 p-0 row" style="height: 80%; background-color: blue"> -->
    <div class="m-0 p-0 row" style="height: 60%">
      <lobby-main></lobby-main>
    </div>
    <!-- <div class="m-0 p-0 row" style="height: 20%; background-color: yellow"> -->
    <div class="m-0 p-0 row" style="height: 20%">
      <lobby-footer></lobby-footer>
    </div>
  </div>
</template>

<script>
import LobbyHeader from "@/components/lobby/LobbyHeader.vue";
import LobbyMain from "@/components/lobby/LobbyMain.vue";
import LobbyFooter from "@/components/lobby/LobbyFooter.vue";

export default {
  components: {
    LobbyHeader,
    LobbyMain,
    LobbyFooter,
  },
  name: "MainLobby",
  created() {},
};
</script>

<style></style>
