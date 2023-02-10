<template>
  <div class="m-0 p-0 game">
    <div class="m-0 p-0 row" style="height: 15%">
      <game-title v-if="playgame !== true"></game-title>
      <game-header v-if="playgame === true"></game-header>
    </div>
    <!-- <div class="m-0 p-0 row" style="height:5%">a_blank</div> -->
    <div class="m-0 p-0 row" style="height: 50%">
      <game-users></game-users>
    </div>
    <!-- <div class="m-0 p-0 row" style="height:5%">b_blank</div> -->
    <div class="pt-5 m-0 row" style="height: 35%">
      <game-confirm v-if="playgame !== true"></game-confirm>
      <game-bottom v-if="playgame === true"></game-bottom>
    </div>
  </div>
</template>

<script>
import GameUsers from "@/components/ingame/GameUsers.vue";
import GameTitle from "@/components/ingame/waitroom/GameTitle.vue";
import GameConfirm from "@/components/ingame/waitroom/GameConfirm.vue";
import GameHeader from "@/components/ingame/playgame/GameHeader.vue";
import GameBottom from "@/components/ingame/playgame/GameBottom.vue";

export default {
  components: { GameUsers, GameTitle, GameConfirm, GameHeader, GameBottom },
  name: "MainGame",
  data() {
    return {
      playgame: false,
    };
  },
  created() {},
  computed: {
    changedPlaygame() {
      return this.$store.state.gameinfo.isPlaying;
    },
  },
  watch: {
    changedPlaygame(val) {
      this.playgame = val;
    },
  },
  methods: {
    test() {
      console.log();
    },
  },
};
</script>

<style></style>
