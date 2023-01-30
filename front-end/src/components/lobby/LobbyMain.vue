<template>
  <div class="m-0 p-0 main">
    <div class="m-0 p-0 row" style="height: 2.5%"></div>
    <div class="m-0 p-0 row" style="height: 20%">
      <RoomCard></RoomCard>
      <RoomCard></RoomCard>
    </div>
    <div class="m-0 p-0 row" style="height: 5%"></div>
    <div class="m-0 p-0 row" style="height: 20%">
      <RoomCard></RoomCard>
      <RoomCard></RoomCard>
    </div>
    <div class="m-0 p-0 row" style="height: 5%"></div>
    <div class="m-0 p-0 row" style="height: 20%">
      <RoomCard></RoomCard>
      <RoomCard></RoomCard>
    </div>

    <div class="m-0 p-0 row" style="height: 5%"></div>
    <div class="m-0 p-0 row" style="height: 20%">
      <RoomCard></RoomCard>
      <RoomCard></RoomCard>
    </div>
    <div class="m-0 p-0 row" style="height: 2.5%"></div>
  </div>
</template>

<script>
import RoomCard from "@/components/lobby/RoomCard.vue";

export default {
  components: { RoomCard },
  name: "LobbyMain",
  created() {},
};
</script>

<style></style>
