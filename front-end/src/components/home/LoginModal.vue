<template>
  <div>
    <div>
      <div>
        <div>
          <h1>로그인</h1>
          <button v-on:click="offModal">x</button>
        </div>
        <div>
          <ul>
            <li>이메일</li>
            <li>
              <input
                type="text"
                placeholder="이메일을 입력해주세요"
                v-model="useremail"
              />
            </li>
            <li>비밀번호</li>
            <li>
              <input
                type="password"
                placeholder="비밀번호를 입력해주세요"
                id="userpwd"
                v-model="userpwd"
              />
            </li>
            <li>
              <button v-on:click="logIn()">로그인</button>
            </li>
            <hr />
            <li class="btngrp">
              <a
                href="https://accounts.google.com/o/oauth2/v2/auth?client_id=379257368587-calfpqn6bh9ehcfqtd3po48r2ohhpr1l.apps.googleusercontent.com&redirect_uri=http://localhost:8081/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile"
                ><img src="@/assets/icon/btngoogle.png" alt="" />
              </a>
              <a
                href="https://kauth.kakao.com/oauth/authorize?client_id=1ff9c0d8399e2993bcc0f81a9c899269&redirect_uri=http://localhost:8081/kakao&response_type=code"
                ><img src="@/assets/icon/btnkakao.png" alt=""
              /></a>
            </li>
            <li>
              아직 회원이 아니신가요?
              <a href="#">회원가입</a>
            </li>
            <li>
              비밀번호를 잊어버리셨나요?
              <a href="#">비밀번호 찾기</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginModal",
  components: {},
  data() {
    return {
      useremail: null,
      userpwd: null,
    };
  },
  created() {},
  mounted() {},
  methods: {
    offModal() {
      this.$emit("close", "login"); // 창 닫아주세요.
    },
    resetval() {
      (this.useremail = null), (this.userpwd = null);
    },
    logIn() {
      const payload = {
        email: this.useremail,
        password: this.userpwd,
      };
      this.$store.dispatch("logIn", payload);
    },
  },
};
</script>

<style scoped></style>

<!-- 
  -기능
  1. 로그인 버튼
    - 로그인 버튼 클릭시, 로그인 진행
      - 로그인 완료시 토큰을 vuex에 저장. 메인페이지로 이동
      - 로그인 실패시 현재상태유지, alert창 로그인실패 안내
  
  2. 회원가입 링크
    - 회원가입 링크 클릭시, 로그인 모달창 닫힘
    - 회원가입 모달창 켜짐
  
  3. 비밀번호 찾기 링크
    - 비밀번호 찾기 링크 클릭시, 로그인 모달창 닫힘
    - 비밀번호 찾기 모달창 켜짐
 -->
