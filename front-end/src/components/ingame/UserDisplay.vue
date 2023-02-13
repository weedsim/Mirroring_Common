<template>
  <user-video
    :stream-manager="$store.state.sessions[curIdx].ovSession.publisher"
  />
  <!-- @click="updateMainVideoStreamManager(publisher)"  -->
  <!-- <user-video v-for="sub in $store.state.sessions[curIdx].ovSession.subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"
          @click="updateMainVideoStreamManager(sub)" /> -->
  <!-- </div> -->
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/UserVideo";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL =
  //   process.env.NODE_ENV === "production"
  //     ? "http://localhost:5000/"
  //     : "http://192.168.91.171:5000/";
  "http://localhost:5000";
// "http://192.168.91.171:5000/";
export default {
  name: "UserDisplay",

  components: {
    UserVideo,
  },

  props: {
    curIdx: String,
  },
  data() {
    return {
      // OpenVidu objects

      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,

      // Join form
      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
    };
  },
  created() {
    // this.joinSession();
    console.log(444, this.$store.state.sessions[this.curIdx].ovSession);
    //
    // console.log("OV,", this.OV)
    // console.log("session,", this.session,)
    // console.log("mainStreamManager,", this.mainStreamManager,)
    // console.log("publisher,", this.publisher,)
    // console.log("subscribers", this.subscribers)
  },
  methods: {
    joinSession() {
      // --- 1) Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- 2) Init a session ---
      this.session = this.OV.initSession();

      // --- 3) Specify the actions when events take place in the session ---

      // 새로운 유저가 어느 위치에 들어오는지 확인
      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        // const subscriber = this.session.subscribe(stream);

        for (let i = 0; i < 10; i++) {
          //     console.log(this.$store.state.sessions[i].isJoin)
          if (!this.$store.state.sessions[i].isJoin) {
            this.$store.state.sessions[i].ovSession.session.subscribe(stream);
            break;
          }
        }
        // console.log("this.$state.store.sessions", this.$store.state.sessions)
        // this.$state.store.sessions[this.curIdx].ovSession = subscriber
        // console.log("this.subscribers",this.subscribers)
        // console.log("this.curIdx", this.curIdx)
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        // 채팅이벤트
        // https://docs.openvidu.io/en/stable/cheatsheet/send-messages/
        // console.log("USER DATA: " + stream.connection.data);
        // const index = this.subscribers.indexOf(stream.streamManager, 0);
        // if (index >= 0) {
        //   this.subscribers.splice(index, 1);
        // }
        // for(let i=0;i<10;i++){
        //     // let session = this.$store.state.sessions[i].ovSession
        //     console.log(333333, index)
        //     if(index === i) {
        //         console.log(222222)
        //         this.$state.store.sessions[i].isJoin = false
        //         this.$store.state.sessions[i].ovSession = {}
        //     //     // this.$state.store.sessions[i].ovSession = subscriber
        //     //     // this.$state.store.sessions[i].isJoin = true;
        //         break;
        //     }
        // }
        // axios.get().then(
        //     axios.
        //     c
        // ).catch()
        // const index = this.subscribers.indexOf(stream.streamManager, 0);
        // if (index >= 0) {
        //   this.subscribers.splice(index, 1);
        // }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- 4) Connect to the session with a valid user token ---

      // Get a token from the OpenVidu deployment
      this.getToken(this.mySessionId).then((token) => {
        // First param is the token. Second param can be retrieved by every user on event
        // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- 5) Get your own camera stream with the desired properties ---

            // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
            // element: we will manage it on our own) and with the desired properties
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            // Set the main video in the page to display our webcam and store our Publisher
            // this.mainStreamManager = publisher;
            this.publisher = publisher;
            // console.log(this.curIdx)
            // console.log("this.$store", this.$store.state.sessions[this.curIdx])

            this.$store.state.sessions[this.curIdx].ovSession.session =
              publisher;
            this.$store.state.sessions[this.curIdx].isJoin = true;

            for (let i = 0; i < 10; i++) {
              console.log(this.$store.state.sessions[i].isJoin);
            }

            // --- 6) Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      //   window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      // Empty all properties...
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      let ovSession = {
        OV: this.OV,
        session: this.session,
        mainStreamManager: this.mainStreamManager,
        publisher: this.publisher,
        sessions: this.sessions,
      };

      this.$store.state.sessions[this.curIdx].ovSession = ovSession;
      this.$store.state.sessions[this.curIdx].isJoin = false;

      // Remove beforeunload listener
      window.removeEventListener("beforeunload", this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    /**
     * --------------------------------------------
     * GETTING A TOKEN FROM YOUR APPLICATION SERVER
     * --------------------------------------------
     * The methods below request the creation of a Session and a Token to
     * your application server. This keeps your OpenVidu deployment secure.
     *
     * In this sample code, there is no user control at all. Anybody could
     * access your application server endpoints! In a real production
     * environment, your application server must identify the user to allow
     * access to the endpoints.
     *
     * Visit https://docs.openvidu.io/en/stable/application-server to learn
     * more about the integration of OpenVidu in your application server.
     */
    async getToken(mySessionId) {
      const sessionId = await this.createSession(mySessionId);
      return await this.createToken(sessionId);
    },

    async createSession(sessionId) {
      console.log(APPLICATION_SERVER_URL);
      const response = await axios.post(
        APPLICATION_SERVER_URL + "api/sessions",
        { customSessionId: sessionId },
        {
          headers: { "Content-Type": "application/json" },
        }
      );
      return response.data; // The sessionId
    },

    async createToken(sessionId) {
      const response = await axios.post(
        APPLICATION_SERVER_URL + "api/sessions/" + sessionId + "/connections",
        {},
        {
          headers: { "Content-Type": "application/json" },
        }
      );
      return response.data; // The token
    },
  },
};
</script>
