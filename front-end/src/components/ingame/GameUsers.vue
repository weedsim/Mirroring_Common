<template>
  <div class="m-0 p-0 users">
    <div class="m-0 p-0 row" style="height:40%;">
        <div class="m-0 p-0 col-4">
            <user-comp></user-comp>
        </div>
        <div class="m-0 p-0 col-4">
            <user-comp></user-comp>
        </div>
        <div class="m-0 p-0 col-4">
            <user-comp></user-comp>
        </div>
    </div>
    <div class="m-0 p-0 row" style="height:10%;"></div>
    <div class="m-0 p-0 row" style="height:40%;">
        <div class="m-0 p-0 col-4">
            <user-comp></user-comp>
        </div>
        <div class="m-0 p-0 col-4">
            <user-comp></user-comp>
        </div>
        <div class="m-0 p-0 col-4">
            <user-comp></user-comp>
        </div>
    </div>
  </div>
</template>

<script>
import UserComp from "@/components/ingame/UserComp.vue"

export default {
  components: { UserComp },
  name: "GameUser",
  created() {
    
  },
}
</script>

<style></style>