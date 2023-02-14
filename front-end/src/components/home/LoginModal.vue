<template>
  <div id="myModal" class="modal">
    <div class="modal-content">
      <div class="login-modal-header">
        <div class="hidden-icon">&times;</div>
        <div class="login-text">로그인</div>
        <div class="close" v-on:click="offModal">&times;</div>
      </div>
      <div class="modal-padding">
        <ul>
          <li class="modal-email-pwd">이메일</li>
          <li>
            <input
              class="modal-input"
              type="text"
              placeholder="이메일을 입력해주세요"
              v-model="useremail"
            />
          </li>
          <li class="modal-email-pwd">비밀번호</li>
          <li>
            <input
              class="modal-input"
              type="password"
              placeholder="비밀번호를 입력해주세요"
              id="userpwd"
              v-model="userpwd"
            />
          </li>
          <li style="margin-bottom: 1vh; margin-top: 1vh">
            <span style="font-size: 1.2vh; color: #684107"
              >비밀번호를 잊어버리셨나요?
            </span>
            <a href="#" style="font-size: 1.2vh; color: #b80000"
              >비밀번호 찾기</a
            >
          </li>
          <li class="login-container">
            <button class="btn-login modal-email-pwd" v-on:click="logIn">
              로그인
            </button>
          </li>
          <li class="text-center" style="margin-bottom: 1vh">
            <span style="font-size: 1.2vh; color: #684107"
              >아직 회원이 아니신가요?
            </span>
            <a href="#" style="font-size: 1.2vh; color: #b80000"> 회원가입</a>
          </li>
          <hr />
          <li class="text-center" style="margin-bottom: 1.7vh; margin-top: 1vh">
            <span style="font-size: 1.5vh">다른 방법으로 로그인 </span>
          </li>
          <li class="btngrp">
            <a
              href="https://accounts.google.com/o/oauth2/v2/auth?client_id=379257368587-calfpqn6bh9ehcfqtd3po48r2ohhpr1l.apps.googleusercontent.com&redirect_uri=http://localhost:8081/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile"
              ><div class="google btn-logo">
                <span class="google-logo"></span
                ><span class="social-text">Google</span>
              </div>
            </a>
            <a
              href="https://kauth.kakao.com/oauth/authorize?client_id=1ff9c0d8399e2993bcc0f81a9c899269&redirect_uri=http://localhost:8081/kakao&response_type=code"
              ><div class="kakao btn-logo">
                <span class="kakao-logo"></span
                ><span class="social-text">Kakao</span>
              </div></a
            >
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "LoginModal",
  components: {},
  data() {
    return {
      useremail: null,
      userpwd: null,
      modalDisplay: "none",
    };
  },
  created() {},
  mounted() {},
  methods: {
    offModal() {
      (this.useremail = null), (this.userpwd = null);
      this.$emit("close", "login"); // 창 닫아주세요.
    },
    // resetval() {
    //   (this.useremail = null), (this.userpwd = null);
    // },
    logIn() {
      const payload = {
        email: this.useremail,
        password: this.userpwd,
      };
      this.$store.dispatch("logIn", payload);
    },
    // logIn() {
    //   axios({
    //     method: "POST",
    //     url: `${this.$store.state.API_URL}/users/login`,
    //     headers: {
    //       "Content-Type": "multipart/form-data",
    //       // "Content-Type": "application/json",
    //     },
    //     bodys: {
    //       email: this.useremail,
    //       password: this.userpwd,
    //     },
    //   })
    //     .then((res) => {
    //       console.log(res.data);
    //       const payload = {
    //         token: res.data.data.accessToken,
    //         refreshToken: res.data.data.refreshToken,
    //       };
    //       this.$store.commit("SAVE_TOKEN", payload);
    //       this.offModal();
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // },
  },
};
</script>

<style scoped>
.btngrp {
  display: flex;
  justify-content: space-evenly;
}
.btn-logo {
  border-radius: 20px;
  width: 8vw;
  height: 6vh;
  display: flex;
  align-items: center;
}
.social-text {
  margin-left: 0.6vw;
  font-size: 1vw;
}
.google-logo {
  margin-left: 1.5vh;
  width: 3.5vh;
  height: 3.5vh;
  background-image: url(../../assets/icon/btngoogle.png);
  background-repeat: no-repeat;
  display: flex;
  justify-content: center;
  background-size: cover;
}
.kakao-logo {
  margin-left: 1.5vh;
  width: 3.5vh;
  height: 3.5vh;
  background-image: url(../../assets/icon/btnkakao.png);
  background-repeat: no-repeat;
  display: flex;
  justify-content: center;
  background-size: cover;
}
.kakao {
  background-color: #fee500;
}
.google {
  background-color: white;
}
.login-container {
  display: flex;
  justify-content: center;
}
.btn-login {
  background-color: rgba(253, 253, 253, 70%);
  border-radius: 20px;
  width: 7vw;
  height: 4vh;
  border: 0px;
}
.btn-login:hover {
  background-color: rgb(253, 253, 253);
}
.modal-email-pwd {
  font-weight: 500;
  font-size: 2vh;
  margin-top: 1vh;
  margin-bottom: 1vh;
}
.modal-input {
  background-color: rgb(217, 217, 217);
  border-radius: 20px;
  border: 0px;
  width: 100%;
  height: 4vh;
  padding: 20px;
}
.login-modal-header {
  display: flex;
  margin-top: 1.2vh;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 3vh;
}
.modal-padding {
  padding-left: 1.5vw;
  padding-right: 1.5vw;
}
.modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  margin-top: 20vh;
  margin-left: 39vw;
  background-color: rgba(255, 255, 255, 75%);
  padding: 20px;
  border: 1px solid #888;
  width: 22vw;
  height: 65vh;
  border-radius: 30px;
}

/* The Close Button */
.hidden-icon {
  visibility: hidden;
  font-size: 5vh;
}
.close {
  color: #aaaaaa;
  font-size: 5vh;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
.login-text {
  font-size: 3.5vh;
  font-weight: 500;
  text-align: center;
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
