<template>
  <div class="m-0 p-0 header" style="height: 100%">
    <div class="m-0 p-0 row" style="height: 10%"></div>
    <div class="m-0 p-0 row" style="height: 80%">
      <div class="m-0 p-0 col-3"></div>
      <div class="m-0 p-0 col-6">
        <div class="m-0 p-0 rounded-3">방목록</div>
      </div>
      <div class="m-0 p-0 col-3">
        <div class="m-0 p-0 rounded-3">
          <div class="m-0 p-0 row">
            <div class="m-0 p-0 col-9"></div>
            <div class="m-0 p-0 col-3">
              <BIconArrowCounterclockwise></BIconArrowCounterclockwise>
            </div>
          </div>
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

<style></style>
