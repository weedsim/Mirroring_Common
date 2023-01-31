<template>
  <div class="m-0 p-0 game">
    <div class="m-0 p-0 row" style="height:15%">
        <game-title></game-title>
    </div>
    <!-- <div class="m-0 p-0 row" style="height:5%">a_blank</div> -->
    <div class="m-0 p-0 row" style="height:75%">
        <game-users></game-users>
    </div>
    <!-- <div class="m-0 p-0 row" style="height:5%">b_blank</div> -->
    <div class="m-0 p-0 row" style="height:10%">
        <game-confirm></game-confirm>
    </div>
  </div>
</template>

<script>
import GameUsers from "@/components/ingame/GameUsers.vue"
import GameTitle from "@/components/ingame/waitroom/GameTitle.vue"
import GameConfirm from "@/components/ingame/waitroom/GameConfirm.vue"

export default {
  components: { GameUsers, GameTitle, GameConfirm },
  name: "MainGame",
  created() {
    
  },
}
</script>

<style></style>