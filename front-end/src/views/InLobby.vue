<template>
  <div class="about">
    <!-- <left-container></left-container> -->
    <!-- <main-lobby style="width:100%;height:100vh;"></main-lobby> -->
    <outer-container></outer-container>
    <!-- <right-container></right-container> -->
  </div>
</template>

<script>
// import { mapState } from "vuex";
import MainLobby from "@/components/lobby/MainLobby.vue";

// import LeftContainer from "@/components/LeftContainer.vue";
// import RightContainer from "@/components/RightContainer.vue";
import OuterContainer from "@/components/OuterContainer.vue";

export default {
    name:"InLobby",
    components: {
        
    MainLobby
    // ,LeftContainer
    // ,RightContainer
    , OuterContainer
    },
}

</script>
