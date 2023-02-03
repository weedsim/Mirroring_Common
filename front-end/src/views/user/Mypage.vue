<template>
  <div>
    <ul>
      <li>유저 이메일: {{ email }}</li>
      <li>유저 이름: <input type="text" v-model="name" /></li>
      <li>현재 비밀번호 입력: <input type="text" /></li>
      <li>수정 비밀번호 입력: <input type="text" /></li>
      <li>수정 비밀번호 확인: <input type="text" /></li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {},
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

<style scoped></style>
