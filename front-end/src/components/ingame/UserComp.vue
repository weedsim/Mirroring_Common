<template>
  <!-- <user-comp> -->
  <div class="p-0">
    <div class="p-3" :class="{ 'speaking-blur': active }">
      <!-- <div class="p-3 speaking-blur"> -->
      <div class="m-0 p-0 user screen" :class="{ speaking: active }">
        <div class="m-0 p-0 row">
          <!-- 화상 화면 및 닉네임 -->
          <!-- <user-display :curIdx="curIdx" alt=""> </user-display> -->
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

        <div class="btn-mic-cam p-0">
          <div class="vote">
            <div style="position: absolute">
              <button
                @click="togglev()"
                style="background: none; border-width: 0"
              >
                <BIconVolumeMuteFill
                  style="color: red"
                  v-if="$store.state.isShow_vol"
                ></BIconVolumeMuteFill>
                <BIconVolumeUpFill
                  style="color: white"
                  v-else
                ></BIconVolumeUpFill>
              </button>
              <button
                @click="togglec()"
                style="background: none; border-width: 0"
              >
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
            <div class="vote-msg wait" v-on:click="vote">지 목</div>
          </div>
        </div>
        <!-- <div class="m-0 p-0 row" style="height: %"></div> -->
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
import axios from "axios";
import VueCookies from "vue-cookies";

export default {
  data() {
    return {
      isShow_vol: false,
      isShow_cam: false,
      API_URL: this.$store.state.API_URL,
      active: true,
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
    chkSpeaker() {
      return this.$store.state.curSpeakIdx;
    },
  },
  watch: {
    chkSpeaker(newVal) {
      this.chgActive(newVal);
    },
  },
  created() {
    // console.log(11111111,this.curIdx)
  },
  props: {
    curIdx: String,
  },
  methods: {
    chgActive(newVal) {
      console.log("active값이 바뀝니다~!");
      console.log("발언자idx: " + newVal);
      console.log("내idx: " + this.$store.state.myIdx);
      //만약에 새로운값이 자기자신의 인덱스이거나 on일때는 true로 변경 아닐경우에는 false로 변경
      if (newVal === Number(this.$store.state.myIdx)) {
        this.active = true;
      } else if (newVal === "on") {
        this.active = true;
      } else {
        this.active = false;
      }
    },
    togglev() {
      this.$store.state.isShow_vol = !this.$store.state.isShow_vol;
    },
    togglec() {
      this.$store.state.isShow_cam = !this.$store.state.isShow_cam;
    },
    // 투표전송하기.
    vote() {
      console.log("voter", this.$store.state.myIdx);
      console.log("target", this.curIdx);
      axios({
        method: "POST",
        url: `${this.API_URL}/rooms/${this.$store.state.gameinfo.roomId}/games/vote`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
        data: {
          voter: this.$store.state.myIdx,
          target: this.curIdx,
        },
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log("투표실패", err);
        });
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
  border-radius: 10px;
  /* opacity: 0%; */
  /* z-index: 5; */
}
.btn-mic-cam {
  position: absolute;
  top: 0%;
  display: none;
  width: 100%;
  height: 100%;
}
.user-name {
  position: absolute;
  top: 0%;
  color: black;
  text-shadow: -1px 0 #ffffff, 0 1px #ffffff, 1px 0 #ffffff, 0 -1px #ffffff;
  font-weight: 600;
}
.screen:hover > .btn-mic-cam {
  background-color: rgba(0, 0, 0, 30%);
  display: block;
}
.screen:hover > .btn-mic-cam .vote {
  display: block;
}
.vote-msg:hover {
  cursor: pointer;
}
.vote {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0%;
  left: 0%;
  display: none;
}
.vote-msg {
  text-align: center;
  position: relative;
  top: 45%;
  transform: translateY(-50%);
  color: rgb(210, 0, 0);
  font-size: 3vw;
  /* letter-spacing: 1vw; */
  font-weight: 600;
}
.wait {
  /* opacity: 0%; */
}
</style>
