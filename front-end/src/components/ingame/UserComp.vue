<template>
  <div class="m-1 p-0">
  <div class="m-0 p-0 user" style="height:100%;">
    <!-- <user-comp-speak> -->
        <div class="m-0 p-0 row" style="height:70%;">
            <!-- 화상 화면 및 닉네임 -->
            <img src="@/assets/ingame/headphone.png" alt="">
        </div>
        
          <button type="button" class="btn btn-warning" v-if="$store.state.isShow" style="width:100%"><div class="m-0 p-0" style="font-size: 2vw;">준비중</div></button>
          <button type="button" class="btn btn-success" v-else style="width:100%"><div class="m-0 p-0" style="font-size: 2vw;">준비완료</div></button>
          <!-- <img src="@/assets/icon/icon_headset.jpg" alt="" id="icon_headset"><img src="@/assets/icon/icon_camera.png" alt="" id="icon_camera"> -->
          <button @click="togglev()" style="background:black"><BIconVolumeUpFill style="color:blue" v-if="$store.state.isShow_vol"></BIconVolumeUpFill>
          <BIconVolumeMuteFill style="color:white" v-else></BIconVolumeMuteFill></button>
          <button @click="togglec()" style="background:black"><BIconCameraVideoFill style="color:blue" v-if="$store.state.isShow_cam"></BIconCameraVideoFill>
            <BIconCameraVideoOffFill style="color:white" v-else></BIconCameraVideoOffFill></button>
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
import { BIconVolumeUpFill } from "bootstrap-icons-vue";
import { BIconCameraVideoFill } from "bootstrap-icons-vue";
import { BIconVolumeMuteFill } from "bootstrap-icons-vue";
import { BIconCameraVideoOffFill } from "bootstrap-icons-vue";
export default {
  data(){
    return {
      isShow_vol: false,
      isShow_cam: false
    
    }
  },
  name: "RoomComp",
  components: { BIconVolumeUpFill, BIconCameraVideoOffFill, BIconCameraVideoFill, BIconVolumeMuteFill},
  created() {
  },
  methods:{
    togglev(){
      this.$store.state.isShow_vol = !this.$store.state.isShow_vol
     },
     togglec(){
      this.$store.state.isShow_cam = !this.$store.state.isShow_cam
     }
    }
  
}
</script>

<style>
/* #icon_camera{
  width:50%;
  height:30px;
}
#icon_headset{
  width:50%;
  height:30px;
} */
</style>