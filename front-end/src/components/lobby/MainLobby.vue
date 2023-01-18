<template>
  <div class="lobby">
    <div class="row" style="height:20%">
        <lobby-header></lobby-header>
    </div>
    <div class="row" style="height:80%">
        <lobby-main></lobby-main>
    </div>
    <div class="row" style="height:20%">
        <lobby-footer></lobby-footer>
    </div>
  </div>
</template>

<script>
import LobbyHeader from "@/components/lobby/LobbyHeader.vue"
import LobbyMain from "@/components/lobby/LobbyMain.vue"
import LobbyFooter from "@/components/lobby/LobbyFooter.vue"

export default {
  components: { 
    LobbyHeader ,LobbyMain ,LobbyFooter
    },
  name: "MainLobby",
  created() {
    
  },
}
</script>

<style></style>