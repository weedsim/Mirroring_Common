<template>
  <navbar />
  <div class="m-0 p-0">
    <div class="m-0 p-0 mypagemain" style="height: 95vh">
      <div class="m-0 p-0 row">
        <table class="table">
          <tr>
            <td></td>
          </tr>
        </table>

        <ul style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
          <li><h1 class="usermail">유저 이메일: {{ email }} </h1></li>
          <li>유저 이름: <input type="text" v-model="username" v-bind:disabled="!isinputActive" placeholder="{{ name }}"/> <button id="namebtn" @click="namedupli">이름 변경</button> </li>
          <li>수정 비밀번호 입력: <input type="text" v-model="modifyPW" /></li>
          <li>수정 비밀번호 확인: <input type="text" v-model="remodifyPW" /> <button id="pwbtn" @click="patchPW">비밀 번호 수정</button></li>
        </ul>
      </div>
      <hr class="my-4">
      <!-- 전적확인란 -->
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
      name: null,
      email: null,
      pwd: null,
      chkpwd: null,
      API_URL: this.$store.state.API_URL,
      remodifyPW: "",
      modifyPW: "",
      isinputActive: false,
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
        // headers: {
        //   Authorization: `Bearer ${this.$store.state.accessToken}`,
        // },
        data: {
          accessToken: this.$store.state.accessToken,
        },
      })
        .then((res) => {
          // console.log(res.data.accessToken);
          // console.log(res.data.refreshToken);
          console.log(res);
        })
        .catch((err) => {
          console.log("실패");
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
  .mypagemain {
    background-image: url(../../assets/home/maintop.jpg);
  }
  hr.my-4 {
    border: 1px solid white;
  }
</style>