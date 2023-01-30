<template>
    <div class="m-0 p-0 users">
      <div class="m-0 p-0 row" style="height:40%;">
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="0"></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="1"></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="2"></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="3"></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="4"></user-comp>
          </div>
      </div>
      <div class="m-0 p-0 row" style="height:10%;"></div>
      <div class="m-0 p-0 row" style="height:40%;">

          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="5"></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="6"></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="7"></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="8"></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx="9"></user-comp>
          </div>
      </div>
    </div>
</template>
  
<script>
import UserComp from "@/components/ingame/UserComp.vue"
import axios from "axios";

export default {
    components: { UserComp },
    name: "GameUser",
    el:'#liar',
    data(){
        return{
            
        }
    },
    created() {
        // 세션 구조 복사
        // console.log(this.$store.state.session)
        // this.mySession = Object.assign({}, this.$store.modules.session),
        this.initFrontSession()
        // 1초마다 통신하여 세션 싱크
        setInterval(()=>{
            // this.syncFrontSession()
        }, 1000)
    },
    methods:{
        initFrontSession(){
            let sessions = this.$store.state.sessions
            for(let i = 0; i < 10; i++) {
                if(!sessions[i].isJoin){
                    sessions[i].isJoin = true;
                    this.$store.state.myIdx = i;
                    // myIdx에 유저 접속했다고 백에 알리기(x)
                    break;
                }
            }
            console.log(this.myIdx)
        },

        // 세션 통신 부분
        // 세션 통신이 여기서만 이뤄지기 때문에 라우터 튜닝 안할껍니다

        // 백엔드에서 세션 받아오기
        getFrontSessionFromBackSession(){
            let sessions
            // axios로 세션 받아와서 세션 구조 맞춰주기(x)
            // 프론트랑 백이랑 세션 구조 동일하게 맞추기(x)
            return sessions
        },

        // 프론트 세션 싱크로
        syncFrontSession(){
            this.$store.state.sessions = this.getFrontSessionFromBackSession()
        },

        // 백엔드로 세션 보내기
        postFrontSessionToBackSession(){
            let session = this.mySession
            // axios로 세션보내기(x)
        }
        
    },
  methods:{
    async getToken() {
        const sessionId = await this.createSession(this.state.mySessionId);
        return await this.createToken(sessionId);
    },
    async createSession(sessionId) {
        const response = await axios.post(this.APPLICATION_SERVER_URL + 'api/sessions', { customSessionId: sessionId }, {
            headers: { 'Content-Type': 'application/json', },
        });
        return response.data; // The sessionId
    },
    async createToken(sessionId) {
        const response = await axios.post(this.APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections', {}, {
            headers: { 'Content-Type': 'application/json', },
        });
        return response.data; // The token
    }
  }
}
</script>

<style></style>