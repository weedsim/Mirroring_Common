<template>
  <game-title v-if="playgame !== true"></game-title>
  <game-header v-else></game-header>
  <game-users style="height: 70vh"></game-users>
  <game-confirm v-if="playgame !== true"></game-confirm>
  <game-bottom v-else></game-bottom>
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
