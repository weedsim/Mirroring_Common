
<template>
  <div class="m-0 p-0 main">
    <div class="m-0 p-0 row" style="height:2.5%;"></div>
    <div class="m-0 p-0 row" style="height:20%;">
        <div class="m-0 p-0 col-6">
            <user-comp></user-comp>
        </div>
        <div class="m-0 p-0 col-6">
            <user-comp></user-comp>
        </div>
    </div>
    <div class="m-0 p-0 row" style="height:5%;"></div>
    <div class="m-0 p-0 row" style="height:20%;">
        <div class="m-0 p-0 col-6">
            <user-comp></user-comp>
        </div>
        <div class="m-0 p-0 col-6">
            <user-comp></user-comp>
        </div>
    </div>
    <div class="m-0 p-0 row" style="height:5%;"></div>
    <div class="m-0 p-0 row" style="height:20%;">
        <div class="m-0 p-0 col-6">
            <user-comp></user-comp>
        </div>
        <div class="m-0 p-0 col-6">
            <user-comp></user-comp>
        </div>
    </div>
    <div class="m-0 p-0 row" style="height:5%;"></div>
    <div class="m-0 p-0 row" style="height:20%;">
        <div class="m-0 p-0 col-6">
            <user-comp></user-comp>
        </div>
        <div class="m-0 p-0 col-6">
            <user-comp></user-comp>
        </div>
    </div>
    <div class="m-0 p-0 row" style="height:2.5%;"></div>
  </div>
</template>

<script>
import RoomComp from "@/components/lobby/RoomComp.vue"

export default {
  components: { RoomComp },
  name: "LobbyMain",
  created() {
    
  },
}
</script>

<style></style>