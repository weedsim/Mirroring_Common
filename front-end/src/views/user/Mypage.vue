<template>
  <navbar />
  <div class="m-0 p-0">
    <div class="m-0 p-0 mypage" style="height: 95vh; width: 100%; ">
      <div class="m-0 p-0 row" style="display: flex; justify-content: center; align-items: center;">
        <h1>마이페이지</h1>
        <hr>
        <h3>유저 정보</h3>
        <table class="usertable thead-dark" style="margin: 0 auto;">
          <tbody>
          <tr>
            <th>유저 이메일 : </th>
            <th>{{ email }}</th>
          </tr>
          <tr>
            <td style="color: white; margin-right: 1%;">유저 이름:</td>
            <td><input type="text" v-model="username" v-bind:disabled="!isinputActive" /> <button id="namebtn" @click="namedupli">변경</button> </td>
          </tr>
          <tr>
            <td style="color: white;">수정 비밀번호 입력 : </td>
            <td><input type="text" v-model="modifyPW" /></td>
          </tr>
          <tr>
            <td style="color: white;">수정 비밀번호 확인 : </td>
            <td><input type="text" v-model="remodifyPW" /> <button id="pwbtn" @click="patchPW">수정</button></td>
          </tr>
        </tbody>
        </table>
      </div>
      <hr class="my-4 hist ">
      <!-- 전적확인란 -->
      <h3>전적 정보</h3>
      <table class="historytable thead-dark" style="margin: 0 auto;">
        <thead>
          <tr>
            <th scope="col">normal</th>
            <th scope="col">spy</th>
          </tr>
          <tr>
            <td>{{ normalhistory }}</td>
            <td>{{ spyhistory }}</td>
          </tr>
          <tr v-for="(item, index) in normallist" :key="index">
            <td>{{ item.result }}</td>
            <td>{{ item.role }}</td>
            <td>{{ item.playedTime }}</td>
            <td>{{ item.users.join(', ') }}</td>
            <td v-if="spylist[index]">{{ spylist[index].result }}</td>
            <td v-if="spylist[index]">{{ spylist[index].role }}</td>
            <td v-if="spylist[index]">{{ spylist[index].playedTime }}</td>
            <td v-if="spylist[index]">{{ spylist[index].users.join(', ') }}</td>
          </tr>
        </thead>
      </table>
      <!-- <hr class="my-4 reward">
      리워드란 -->
    </div>
  </div>
</template>

<script>
import navbar from "@/components/NavView.vue";
import axios from "axios";

export default {
  components: { navbar },
  data() {
    return {
      username: null,
      email: null,
      pwd: null,
      chkpwd: null,
      API_URL: this.$store.state.API_URL,
      remodifyPW: "",
      modifyPW: "",
      isinputActive: false,
      normalhistory: null, //노말모드 전체 승패
      spyhistory: null, //스파이모드 전체 승패
      normalwin: null,  //노말모드 승수
      normallose: null,   //노말모드 패수
      normalplayed:null,   //노말모드 플레이한 수
      spywin: null,   //스파이모드 승수
      spylose: null,    //스파이모드 패수
      spyplayed: null,    //스파이모드 플레이한 수
      normallist: null,   //노말모드 리스트
      spylist: null,   //스파이모드 리스트

    };
  },
  watch: {
    //한글자마다 비밀번호 일치하는지 확인
    remodifyPW(newValue) {
      if(this.modifyPW !== newValue){
        console.log("비밀번호 불일치")
      }
    }
  },
  setup() {},
  created() {
    this.userInfo();
    
  },
  mounted() {},
  methods: {
    userInfo() {
      axios({
        method: "get",
        url: `${this.API_URL}/users`,
        headers: {
          Authorization: `Bearer ${this.$store.state.accessToken}`,
        },
      })
        .then((res) => {
          this.name = res.data.name;
          this.email = res.data.email;
          // console.log(res.data.accessToken);
          console.log(res);
        })
        .catch((err) => {
          console.log("유저정보실패");
          console.log(err);
        });
        axios({
          method: "get",
          url: `${this.API_URL}/history/normal`,
          headers: {
            Authorization: `Bearer ${this.$store.state.accessToken}`,
          },
        })
        .then((res) => {
          this.normallist = res;
          let countwin = 0;
          let countlose = 0;
          for(let i = 0; i<res.length; i++){
            if(res[i].result === "win"){
              countwin++;
            }
            else{
              countlose++;
            }
          }
          this.normalwin = countwin;
          this.normallose = countlose;
          this.normalplayed = res.length;
          this.normalhistory = this.normalplayed + '전 ' + this.normalwin + '승 ' + this.normallose + '패';
        })
        .catch((err) => {
          console.log("노말모드실패");
          console.log(err);
        });
        axios({
          method: "get",
          url: `${this.API_URL}/history/spy`,
          headers: {
            Authorization: `Bearer ${this.$store.state.accessToken}`,
          },
        })
        .then((res) => {
          this.spylist = res;
          let countwin = 0;
          let countlose = 0;
          for(let i = 0; i < res.length; i++){
            if(res[i].result === "win"){
              countwin++;
            }
            else{
              countlose++;
            }
          }
          this.spywin = countwin;
          this.spylose = countlose;
          this.spyplayed = res.length;
          this.spyhistory = this.spyplayed + '전 ' + this.spywin + '승 ' + this.spylose + '패';
        })
        .catch((err) => {
          console.log("스파이모드실패");
          console.log(err);
        });
    },
    namedupli() {  //중복확인
      this.isinputActive = true;
      const btnElement = document.getElementById('namebtn');
      btnElement.innerText = '중복 확인';
      axios({
        method: "get",
        url: `${this.API_URL}/users/duplicate`,
        headers:{
          
        },
        body: {
          'name': `${this.name}`
        }
      })
      .then((res) => {
          console.log(res);
          if(res==true) {
            console.log("성공")
          }
          else{
            console.log("실패")
          }
        })
    },
    patchPW() { //비밀번호 변경되게 입력
      if(this.modifyPW === this.remodifyPW) {
        axios({
          method: "put",
          url: `${this.API_URL}/users/modify/password`,
          headers:{
            Authorization: `Bearer ${this.$store.state.accessToken}`,
          },
          body: {
            "password": `{this.remodifyPW}`,
          }
        })
        .then((res) => {
          console.log(res);
          if(res==true) {
            console.log("성공")
          }
          else{
            console.log("실패")
          }
        })
      } else{
        console.log("비밀번호가 다르게 입력되었습니다.")
      }
    },
  },
};
</script>

<style scoped>
  .mypage {
    background-image: url(../../assets/lobby/lobby.png);
    background-size: 102vw 100vh;
    background-repeat: no-repeat;
    background-position-x: -10px;
    color:white;
  }
  hr.my-4 {
    border: 1px solid white;
  }
  .usertable{
    border-collapse: separate;
    border-spacing: 1vh;
    border: 1px solid white;
  }
  th, td{
    border: 1px solid white;
  }
  
  
</style>