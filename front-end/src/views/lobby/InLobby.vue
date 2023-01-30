<template>
  <navbar />
  <div class="m-0 p-0 mainlobby">
    <outer-container></outer-container>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import navbar from "@/components/NavView.vue";
import MainLobby from "@/components/lobby/MainLobby.vue";
import OuterContainer from "@/components/OuterContainer.vue";

export default {
  name: "InLobby",
  components: {
    navbar,
    MainLobby,
    OuterContainer,
  },
};
</script>

<style>
.mainlobby {
  background-image: url(../../assets/lobby/lobby.jpg);
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
