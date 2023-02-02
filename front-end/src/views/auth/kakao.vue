<template>
  <div>kakao</div>
</template>

<script>
import axios from "axios";

export default {
  name: "kakao",
  components: {},
  data() {
    return {};
  },
  setup() {},
  created() {
    this.kakaoLogIn();
  },
  mounted() {},
  methods: {
    kakaoLogIn() {
      const URL = "http://localhost:8080";
      let query = window.location.search;
      let param = new URLSearchParams(query);
      let code = param.get("code");
      axios({
        method: "post",
        url: `${URL}/auth/login/kakao?state=kakao&code=${code}`,
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          console.log(res.data.accessToken);
          console.log(res.data.refreshToken);
          console.log(res);
          const payload = {
            token: res.data.accessToken,
            refreshToken: res.data.refreshToken,
          };
          this.$store.dispatch("logInKakao", payload);
        })
        .catch((err) => {
          console.log("실패");
        });
    },
  },
};
</script>

<style scoped></style>
