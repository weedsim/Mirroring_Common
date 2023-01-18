<template>
  <div class="game">
    <div class="row" style="height:20%">
        <game-title></game-title>
    </div>
    <div class="row" style="height:5%">a_blank</div>
    <div class="row" style="height:50%">
        <game-users></game-users>
    </div>
    <div class="row" style="height:5%">a_blank</div>
    <div class="row" style="height:20%">
        <game-confirm></game-confirm>
    </div>
  </div>
</template>

<script>
import GameUsers from "@/components/ingame/GameUsers.vue"
import GameTitle from "@/components/ingame/GameTitle.vue"
import GameConfirm from "@/components/ingame/GameConfirm.vue"

export default {
  components: { GameUsers, GameTitle, GameConfirm },
  name: "MainGame",
  created() {
    
  },
}
</script>

<style></style>