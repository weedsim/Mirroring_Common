<template>
  <!-- <user-comp> -->
  <div class="p-0">
    <div class="p-3 speaking-blur">
      <div class="m-0 p-0 user screen speaking" style="border-radius: 10px">
        <div class="m-0 p-0 row">
          <!-- 화상 화면 및 닉네임 -->
          <user-display
            :curIdx="curIdx"
            v-if="$store.state.sessions[curIdx].isJoin"
            alt=""
          >
          </user-display>

          <img
            class="p-0 m-0"
            src="@/assets/ingame/headphone.png"
            style="aspect-ratio: 5/3.9; width: 100%; border-radius: 10px"
            v-else
            alt=""
          />
          <div
            class="ready m-0 p-0"
            style="background-color: rgba(0, 135, 70, 81%)"
            v-if="this.$store.state.sessions[curIdx].isReady"
          >
            준비완료
          </div>

          <div
            class="unready m-0 p-0"
            style="background-color: rgba(255, 176, 57, 81%)"
            v-else
          >
            대기중
          </div>
        </div>
        <!-- 준비 부분을 컴포넌트로 만들어야되는데 그냥 데이터 상속받아서 처리하겠습니다 -->
        <div class="user-name mt-2 ms-2 p-0">닉네임</div>

        <div class="btn-mic-cam mt-1 ms-2 p-0">
          <button @click="togglev()" style="background: none; border-width: 0">
            <BIconVolumeMuteFill
              style="color: red"
              v-if="$store.state.isShow_vol"
            ></BIconVolumeMuteFill>
            <BIconVolumeUpFill style="color: white" v-else></BIconVolumeUpFill>
          </button>
          <button @click="togglec()" style="background: none; border-width: 0">
            <BIconCameraVideoOffFill
              style="color: red"
              v-if="$store.state.isShow_cam"
            ></BIconCameraVideoOffFill>
            <BIconCameraVideoFill
              style="color: white"
              v-else
            ></BIconCameraVideoFill>
          </button>
        </div>
        <div class="m-0 p-0 row" style="height: %"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { BIconVolumeUpFill } from "bootstrap-icons-vue";
import { BIconCameraVideoFill } from "bootstrap-icons-vue";
import { BIconVolumeMuteFill } from "bootstrap-icons-vue";
import { BIconCameraVideoOffFill } from "bootstrap-icons-vue";
import UserDisplay from "@/components/ingame/UserDisplay.vue";

export default {
  data() {
    return {
      isShow_vol: false,
      isShow_cam: false,
    };
  },
  name: "RoomComp",

  components: {
    BIconVolumeUpFill,
    BIconCameraVideoOffFill,
    BIconCameraVideoFill,
    BIconVolumeMuteFill,
    UserDisplay,
  },
  computed: {
    // checkReady(idx) {
    //   let tmp = this.$store.getters.isParticipants;
    //   let length = tmp.length;
    //   if (Number(idx) < length) {
    //     return this.$store.getters.isParticipants[Number(idx)].isReady;
    //   } else {
    //     return false;
    //   }
    // },
  },
  created() {
    // console.log(11111111,this.curIdx)
  },
  props: {
    curIdx: String,
  },
  methods: {
    togglev() {
      this.$store.state.isShow_vol = !this.$store.state.isShow_vol;
    },
    togglec() {
      this.$store.state.isShow_cam = !this.$store.state.isShow_cam;
    },
    test() {
      console.log(this.$store.state.gameinfo.participants[this.curIdx]);
    },
  },
};
</script>

<style scoped>
/* #icon_camera{
  width:50%;
  height:30px;
}
#icon_headset{
  width:50%;
  height:30px;
} */
.speaking {
  border-radius: 10px;
  outline-style: solid;
  outline-color: #008c06;
}
.speaking-blur {
  border-radius: 10px;
  box-shadow: 0 0 5rem -3rem #66f986 inset;
}
.ready {
  position: absolute;
  bottom: 0%;
  width: 100%;
  font-size: 1.2vw;
  text-align: center;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}
.unready {
  position: absolute;
  bottom: 0%;
  width: 100%;
  font-size: 1.2vw;
  text-align: center;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}
.screen {
  position: relative;
}
.btn-mic-cam {
  position: absolute;
  top: 0%;
  opacity: 0%;
}
.user-name {
  position: absolute;
  top: 0%;
  color: black;
  text-shadow: -1px 0 #ffffff, 0 1px #ffffff, 1px 0 #ffffff, 0 -1px #ffffff;
  font-weight: 600;
}

.screen:hover {
  background-color: black;
  opacity: 90%;
}
.screen:hover > .btn-mic-cam {
  opacity: 100%;
}
</style>
