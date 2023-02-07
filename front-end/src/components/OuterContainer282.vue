<template>
  <div class="m-0 p-0 outer">
    <div class="m-0 p-0 row">
      <div class="m-0 p-0 col-2"></div>
      <div class="m-0 p-0 col-8">
        <!-- 반복문을 통해 컨테이너 추가 가능 -->
        <component :is="comp" style="width: 100%; height: 95vh"></component>
      </div>
      <div class="m-0 p-0 col-2"></div>
    </div>
  </div>
</template>

<script>
import MainLobby from "@/components/lobby/MainLobby.vue";
import MainCommunity from "@/components/community/MainCommunity.vue";

export default {
  name: "OuterContainer",
  components: {
    MainCommunity,
  },
  data() {
    return {
      comp: "MainCommunity",
      comps: ["MainCommunity"],
    };
  },
};
</script>
