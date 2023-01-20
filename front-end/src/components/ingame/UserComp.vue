<template>
  <div class="m-1 p-0">
  <div class="m-0 p-0 user" style="height:100%;">
    <!-- <user-comp-speak> -->
        <div class="m-0 p-0 row" style="height:70%;">
            <!-- 화상 화면 및 닉네임 -->
            <img src="@/assets/headphone.png" alt="">
        </div>
        
          <button type="button" class="btn btn-warning" style="width:100%">준비완료</button>
          <!-- <img src="@/assets/icon/icon_headset.jpg" alt="" id="icon_headset"><img src="@/assets/icon/icon_camera.png" alt="" id="icon_camera"> -->
          <BIconMicFill></BIconMicFill>
          <div class="m-0 p-0 row" style="height:%;">
          
        </div>
        <!-- <div class="m-0 p-0 row" style="height:10%;">
            camera & headset
        </div> -->
    <!-- </user-comp-speak> -->
  </div>
  </div>
</template>

<script>
import { BIconMicFill } from "bootstrap-icons-vue";

export default {
  name: "RoomComp",
  components: { BIconMicFill },
  created() {
    
  },
}
</script>

<style>
#icon_camera{
  width:50%;
  height:30px;
}
#icon_headset{
  width:50%;
  height:30px;
}
</style>