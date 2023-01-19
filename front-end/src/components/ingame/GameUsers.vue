<template>
  <div class="users">
    <div class="row" style="height:40%;">
        <div class="col-4">
            <user-comp></user-comp>
        </div>
        <div class="col-4">
            <user-comp></user-comp>
        </div>
        <div class="col-4">
            <user-comp></user-comp>
        </div>
    </div>
    <div class="row" style="height:10%;"></div>
    <div class="row" style="height:40%;">
        <div class="col-4">
            <user-comp></user-comp>
        </div>
        <div class="col-4">
            <user-comp></user-comp>
        </div>
        <div class="col-4">
            <user-comp></user-comp>
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
  created() {
    
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