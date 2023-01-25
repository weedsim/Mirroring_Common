<template>
  <div class="outer">
    <div class="row" id="outerContainer">
      <div class="col-3"></div>
      <div class="col-6">
        <!-- 반복문을 통해 컨테이너 추가 가능 -->

        <!-- <component :is="comp" style="width: 100%; height: 100%"></component> -->
      </div>
      <div class="col-3"></div>
    </div>
  </div>
</template>

<script>
import MainLobby from "@/components/lobbby/MainLobby.vue";

export default {
  name: "OuterContainer",
  components: {
    MainLobby,
  },
  data() {
    return {
      comp: "MainLobby",
      comps: ["MainLobby"],
    };
  },
};
</script>

<style scoped>
/* div {
  padding: 0px;
  margin: 0px;
} */
.outer {
  height: 95vh;
}
#outerContainer {
  margin: 0px;
}
.tmp {
  background-color: red;
}
</style>
