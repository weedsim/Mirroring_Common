<template>
  <div
    class="modal fade"
    id="loginModal"
    tabindex="-1"
    aria-labelledby="loginModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="loginModalLabel">로그인</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body" style="text-align: left">
          <ul style="list-style: none">
            <li>이메일</li>
            <li>
              <input
                type="text"
                placeholder="이메일을 입력해주세요"
                class="form-control"
                v-model="useremail"
              />
            </li>
            <li style="margin-top: 3vh">비밀번호</li>
            <li>
              <input
                type="password"
                placeholder="비밀번호를 입력해주세요"
                id="userpwd"
                class="form-control"
                v-model="userpwd"
              />
            </li>
            <li style="text-align: center; margin-top: 3vh">
              <button
                type="button"
                class="btn btn-primary btn-sm"
                v-on:click="logIn()"
                data-bs-dismiss="modal"
              >
                로그인
              </button>
            </li>
            <hr />
            <li class="btngrp">
              <a
                href="https://accounts.google.com/o/oauth2/v2/auth?client_id=379257368587-calfpqn6bh9ehcfqtd3po48r2ohhpr1l.apps.googleusercontent.com&redirect_uri=http://localhost:8081/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile"
                ><img src="@/assets/icon/btngoogle.png" alt="" />
              </a>
              <a
                href="https://kauth.kakao.com/oauth/authorize?client_id=1ff9c0d8399e2993bcc0f81a9c899269&redirect_uri=http://192.168.32.111:8081/kakao&response_type=code"
                ><img src="@/assets/icon/btnkakao.png" alt=""
              /></a>
              <!-- <div id="kakaoAnckor"></div> -->
            </li>
            <li style="font-size: small">
              아직 회원이 아니신가요?
              <a href="#" data-bs-toggle="modal" data-bs-target="#signupModal"
                >회원가입</a
              >
            </li>
            <li style="font-size: small">
              비밀번호를 잊어버리셨나요?
              <a href="#" data-bs-toggle="modal" data-bs-target="#pwdModal"
                >비밀번호 찾기</a
              >
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
  setup() {},
  created() {
    this.setAnchor();
  },
  mounted() {},
  methods: {
    setAnchor() {
      //   const APPLICATION_SERVER_URL =
      //     process.env.NODE_ENV === "production"
      //       ? "http://192.168.32.171:5000/"
      //       : "http://localhost:5000/";
      //   let ka = document.getElementById("kakaoAnckor");
      //   let link = document.createElement("a");
      //   link.href = `https://kauth.kakao.com/oauth/authorize?client_id=1ff9c0d8399e2993bcc0f81a9c899269&redirect_uri=${APPLICATION_SERVER_URL}/kakao&response_type=code`;
      //   link.img = "@/assets/icon/btnkakao.png";
      //   ka.appendChild(link);
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

<style scoped>
#loginModalLabel {
  margin: 0 auto;
  padding-left: 25%;
}
ul {
  display: table;
  /* margin-left: auto; */
  margin-right: auto;
  margin-left: auto;
  width: 80%;
  padding: 0px;
}
li {
  margin-bottom: 1vh;
}
.btngrp {
  display: flex;
}
.btngrp p:nth-child(1):hover {
  border: 1px solid red;
}
</style>

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
