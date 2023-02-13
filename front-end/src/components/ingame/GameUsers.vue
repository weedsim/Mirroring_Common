<template>
  <div class="users-container">
    <div
      class="user"
      v-for="(item, index) in $store.state.gameinfo.participants"
      :key="index"
    >
      <div class="speaking-blur" :class="{ 'speaking-blur': active }">
        <!-- <div class="p-3 speaking-blur"> -->
        <div class="user-screen speaking" :class="{ speaking: active }">
          <!-- $store.state.sessions[index].isJoin -->
          <OvVideo
            v-if="this.myIdx === index"
            :stream-manager="this.$store.state.openvidu.publisher"
            :myIdx="this.myIdx"
            :index="index"
          />
          <OvVideo
            v-if="this.myIdx !== index"
            :stream-manager="this.$store.state.openvidu.subscribers[index]"
            :myIdx="this.myIdx"
            :index="index"
          />
          <!-- <img src="@/assets/ingame/headphone.png" v-else alt="" /> -->
          <div class="screen"></div>
          <div
            class="ready"
            style="background-color: rgba(0, 135, 70, 81%)"
            v-if="this.$store.state.gameinfo.participants[index].isReady"
          >
            <!-- this.$store.state.sessions[index].isReady -->
            준비완료
          </div>

          <div
            class="unready"
            style="background-color: rgba(255, 176, 57, 81%)"
            v-else
          >
            대기중
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import OvVideo from "@/components/OvVideo.vue";
import { OpenVidu } from "openvidu-browser";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL =
  // process.env.NODE_ENV === "production"
  //   ? "http://localhost:5000/"
  //   : "http://192.168.91.171:5000/";
  "http://localhost:5000/";

export default {
  components: {
    OvVideo,
  },
  name: "GameUser",
  el: "#liar",
  data() {
    return {
      OV: this.$store.state.openvidu.OV,
      session: this.$store.state.openvidu.session,
      publisher: this.$store.state.openvidu.publisher,
      subscribers: this.$store.state.openvidu.subscribers,
      myIdx: this.$store.state.myIdx,
    };
  },
  created() {
    console.log("여기야!!", this.$store.state.gameinfo.participants);
    console.log("여기야!!2", this.$store.state.openvidu);
    console.log("여기야!!3", this.$store.state.myIdx);
  },
  computed: {},
  methods: {},
};
</script>

<style scoped>
.user {
  width: 20vw;
  margin-left: 2.5vw;
  margin-right: 2.5vw;
  margin-top: 1vw;
  margin-bottom: 1vw;
}
.users-container {
  display: flex;
  flex-wrap: wrap;
}
img {
  width: 100%;
  aspect-ratio: 5/3.9;
  border-radius: 10px;
}
.speaking {
  outline-style: solid;
  outline-color: #008c06;
  outline-width: 10px;
}
.speaking-blur {
  outline-width: 15px;
  outline-offset: 10px;
  outline-color: #66f986;
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
.user-screen {
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
.screen {
  top: 0%;
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 4;
}
.screen:hover {
  background-color: rgba(0, 0, 0, 30%);
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
