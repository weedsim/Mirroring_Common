<template>
  <div class="users-container">
    <div
      class="user"
      v-for="(item, index) in this.getAll"
      :key="index"
      v-on:click="vote(index)"
    >
      <!-- v-on:click="vote(index)" -->
      <div
        class=""
        v-bind:class="{ 'speaking-blur': gameinfo.participants[index].mic }"
      >
        <!-- <div class="p-3 speaking-blur"> -->
        <div
          class="user-screen"
          v-bind:class="{ speaking: gameinfo.participants[index].mic }"
        >
          <!-- $store.state.sessions[index].isJoin -->
          <OvVideo :streamManager="item" class="ov-video" />
          <!-- <img src="@/assets/ingame/headphone.png" v-else alt="" /> -->
          <div class="screen"></div>
          <div
            class="ready"
            style="background-color: rgba(0, 135, 70, 81%)"
            v-if="this.gameinfo.isPlaying === false && index === this.isMaster"
          >
            방장
          </div>

          <div
            class="ready"
            style="background-color: rgba(0, 135, 70, 81%)"
            v-if="
              this.gameinfo.isPlaying === false &&
              index !== this.isMaster &&
              this.gameinfo.participants[index].isReady
            "
          >
            <!-- this.$store.state.sessions[index].isReady -->
            준비완료
          </div>

          <div
            class="unready"
            style="background-color: rgba(255, 176, 57, 81%)"
            v-if="
              this.gameinfo.isPlaying === false &&
              index !== this.isMaster &&
              !this.gameinfo.participants[index].isReady
            "
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
import VueCookies from "vue-cookies";
import jwtDecode from "vue-jwt-decode";
import { mapState, mapGetters, mapActions } from "vuex";

axios.defaults.headers.post["Content-Type"] = "application/json";

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
      participants: this.$store.state.gameinfo.participants,
      active: true,
    };
  },
  created() {
    console.log("여기야!!", this.$store.state.gameinfo.participants);
    console.log("여기야!!2", this.$store.state.openvidu);
    console.log("여기야!!3", this.$store.state.myIdx);
    console.log("마스터입니까?", this.isMaster);
  },
  mounted() {},
  computed: {
    ...mapActions(["setDeleteOpenvidu"]),
    ...mapGetters(["getAll", "isMaster"]),
    ...mapState(["gameinfo", "openvidu", "myIdx", "API_URL"]),
    chkparti() {
      return this.$store.state.gameinfo.participants;
    },
    chkpublisher() {
      return this.$store.state.openvidu.publisher;
    },
    chksubscribers() {
      // return this.$store.state.openvidu.subscribers;
      return this.$store.getters.get_subscribers;
    },
    chkmyIdx() {
      return this.$store.state.myIdx;
    },
  },
  watch: {
    chkparti: {
      handler(newVal) {
        console.log("parti 와치", newVal);
        this.participants = newVal;
      },
      immediate: true,
      deep: true,
    },
    chkpublisher(newVal) {
      console.log("시계는 와치", newVal);
      this.publisher = newVal;
    },
    chksubscribers: {
      handler(newVal) {
        console.log("subscribers 와치", newVal);
        this.subscribers = this.$store.getters.get_subscribers;
      },
      immediate: true,
      deep: true,
    },
    chkmyIdx(newVal) {
      console.log("시계는 와치", newVal);
      this.myIdx = newVal;
    },
  },
  methods: {
    getThis() {
      console.log(this.subscribers);
    },
    vote(targetIndex) {
      console.log("myidx: " + this.myIdx + " targetidx: " + targetIndex);
      axios({
        method: "POST",
        url: `${this.API_URL}/rooms/${this.$store.state.gameinfo.roomId}/games/vote`,
        headers: {
          // "Content-Type": "multipart/form-data",
          // "Content-Type": "application/json",
        },
        data: {
          voter: this.myIdx,
          target: targetIndex,
        },
      })
        .then((res) => {
          console.log("투표완료", res.data);
        })
        .catch((err) => {
          console.log("투표실패", err);
        });
    },
  },
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
.ov-video {
  width: 100%;
  height: 100%;
  border-radius: 10px;
  margin: 0;
  padding: 0;
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
