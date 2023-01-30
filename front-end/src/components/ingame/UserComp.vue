<template>
  <div class="m-1 p-0">
  <div class="m-0 p-0 user" style="height:100%;">
    <!-- <user-comp-speak> -->
        <div class="m-0 p-0 row" style="height:70%;">
            <!-- 화상 화면 및 닉네임 -->
            <user-display v-if="$store.state.sessions[curIdx].isJoin" alt="">
            </user-display>
            
            <img src="@/assets/ingame/headphone.png" v-else alt="">
        </div>
            <!-- 준비 부분을 컴포넌트로 만들어야되는데 그냥 데이터 상속받아서 처리하겠습니다 -->
            <div class="ready">
                <button type="button" class="btn btn-warning" v-if="$store.state.sessions[curIdx].isReady" style="width:100%"><div class="m-0 p-0" style="font-size: 2vw;">준비중 </div></button>
          
                <button type="button" class="btn btn-success" v-else style="width:100%"><div class="m-0 p-0" style="font-size: 2vw;">준비완료</div></button>
            </div>
          <!-- <img src="@/assets/icon/icon_headset.jpg" alt="" id="icon_headset"><img src="@/assets/icon/icon_camera.png" alt="" id="icon_camera"> -->
          <BIconMicFill style="color:white"></BIconMicFill>
          <BIconHeadset style="color:white"></BIconHeadset>
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
import { BIconHeadset } from "bootstrap-icons-vue";
import UserDisplay from "@/components/ingame/UserDisplay.vue"

export default {
  name: "RoomComp",
  components: { BIconMicFill, BIconHeadset, UserDisplay},
  created() {
  },
  props: {
    curIdx : String
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