<template>
  <div class="user" style="height:100%;">
    <!-- <user-comp-speak> -->
        <div class="row" style="height:80%;">
            <!-- 화상 화면 및 닉네임 -->
            <user-display></user-display>
            webrtc & nickname
        </div>
        <div class="row" style="height:5%;">
            blank
        </div>
        <div class="row" style="height:15%;">
            camera & headset
        </div>
    <!-- </user-comp-speak> -->
  </div>
</template>

<script>
import UserDisplay from "./UserDisplay.vue"
export default {
  name: "RoomComp",
  created() {
    
  },
  components:{UserDisplay}
}
</script>

<style></style>