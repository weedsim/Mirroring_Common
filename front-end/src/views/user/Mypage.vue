<template>
  <navbar/>
  <div class = "mypage" style="width: 100%; height: 95vh">
    <h1>마이 페이지</h1>
    <hr>
    <h3>유저 정보</h3>
    <ul>
      <li>유저 이메일: {{ email }}</li>
      <li>유저 이름: <input type="text" v-model="name" /><button type="submit" class="btn btn-secondary mb-3 btn-sm">변경</button></li>
      <li>현재 비밀번호 입력: <input type="text" /><button type="submit" class="btn btn-secondary mb-3 btn-sm">제출</button></li>
      <li>수정 비밀번호 입력: <input type="text" /></li>
      <li>수정 비밀번호 확인: <input type="text" /><button type="submit" class="btn btn-secondary mb-3 btn-sm">확인</button></li>
    </ul>
    <hr>
    <h3>전적 정보</h3>
    <ul>
      <li>일반 모드 : 113전 61승 52패</li>
      <li>스파이 모드 : 90전 51승 39패</li>
    </ul>
    <hr>
    <h3>리워드</h3>
  </div>
</template>

<script>
import axios from "axios";
import navbar from "@/components/NavView.vue"

export default {
  components: {
    navbar
  },
  data() {
    return {
      name: null,
      email: null,
      pwd: null,
      chkpwd: null,
      API_URL: this.$store.state.API_URL,
    };
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
}</style>
