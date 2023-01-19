<template>
  <navbar />
  <div class="m-0 p-0 mainlobby">
    <!-- <left-container></left-container> -->
    <!-- <main-lobby style="width:100%;height:100vh;"></main-lobby> -->
    <outer-container></outer-container>
    <!-- <right-container></right-container> -->
  </div>
</template>

<script>
// import { mapState } from "vuex";
import navbar from "@/components/NavView.vue";
import MainLobby from "@/components/lobby/MainLobby.vue";

// import LeftContainer from "@/components/LeftContainer.vue";
// import RightContainer from "@/components/RightContainer.vue";
import OuterContainer from "@/components/OuterContainer.vue";

export default {
  name: "InLobby",
  components: {
    navbar,
    MainLobby,
    // ,LeftContainer
    // ,RightContainer
    OuterContainer,
  },
};
</script>

<style>
.mainlobby {
  /* background-image: url(../../assets/lobby/lobby.jpg); */
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
