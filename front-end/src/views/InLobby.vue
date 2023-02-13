<template>
  <div class="m-0 p-0 about">
    <!-- <left-container></left-container> -->
    <!-- <main-lobby style="width:100%;height:100vh;"></main-lobby> -->
    <outer-container282></outer-container282>
    <!-- <right-container></right-container> -->
  </div>
</template>

<script>
// import { mapState } from "vuex";
import MainLobby from "@/components/lobby/MainLobby.vue";

// import LeftContainer from "@/components/LeftContainer.vue";
// import RightContainer from "@/components/RightContainer.vue";
import OuterContainer282 from "@/components/OuterContainer282.vue";

export default {
  name: "InLobby",
  components: {
    MainLobby,
    // ,LeftContainer
    // ,RightContainer
    OuterContainer282
    },
}

</script>
