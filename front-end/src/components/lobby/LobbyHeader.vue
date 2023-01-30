<template>
  <div class="m-0 p-0 header" style="height: 100%">
    <div class="m-0 p-0 row" style="height: 10%"></div>
    <div class="m-0 p-0 row" style="height: 80%">
      <div class="m-0 p-0 col-3"></div>
      <div class="m-0 p-0 col-6 lobbytitlewrapper">
        <div class="m-0 p-0 rounded-3 lobbytitle" style="color: white">
          <h1>방목록</h1>
        </div>
      </div>
      <div class="m-0 p-0 col-3 iconwrapper">
        <div class="m-0 p-0 rounded-3">
          <BIconArrowCounterclockwise
            class="fs-3"
            style="color: white"
          ></BIconArrowCounterclockwise>
        </div>
      </div>
    </div>
    <div class="m-0 p-0 row" style="height: 10%"></div>
  </div>
</template>

<script>
import RoomComp from "@/components/lobby/RoomComp.vue";
import { BIconArrowCounterclockwise } from "bootstrap-icons-vue";

export default {
  name: "LobbyHeader",
  components: { BIconArrowCounterclockwise },
  created() {},
};
</script>

<style scoped>
.lobbytitlewrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
.lobbytitle > h1 {
  text-align: center;
}
.iconwrapper {
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
}
</style>
