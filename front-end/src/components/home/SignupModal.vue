<template>
  <div id="myModal" class="modal">
    <div class="modal-content">
      <div class="signup-modal-header">
        <div class="hidden-icon">&times;</div>
        <div class="signup-text">회원가입</div>
        <div class="close" @click="offModal">&times;</div>
      </div>
      <div class="modal-padding">
        <ul>
          <li class="modal-email-pwd-name">이메일</li>
          <li class="signup-container">
            <input
              class="modal-input"
              type="text"
              placeholder="이메일을 입력해주세요"
              v-model="useremail"
            />
            <button
              class="modal-btn-duplicate modal-email-pwd-name"
              v-on:click="chkemail"
            >
              중복확인
            </button>
          </li>
          <li class="modal-email-pwd-name" v-if="email_positive">
            사용 불가능한 이메일 입니다.
          </li>
          <li class="modal-email-pwd-name" v-if="email_negative">
            사용 가능한 이메일 입니다.
          </li>
          <li class="modal-email-pwd-name">닉네임</li>
          <li class="signup-container">
            <input
              class="modal-input"
              type="text"
              placeholder="닉네임을 입력해주세요"
              v-model="usernickname"
            />
            <button
              class="modal-btn-duplicate modal-email-pwd-name"
              v-on:click="chknick"
            >
              중복확인
            </button>
          </li>
          <li class="modal-email-pwd-name" v-if="name_positive">
            사용 불가능한 닉네임 입니다.
          </li>
          <li class="modal-email-pwd-name" v-if="name_negative">
            사용 가능한 닉네임 입니다.
          </li>
          <li class="modal-email-pwd-name">비밀번호</li>
          <li>
            <input
              class="modal-input"
              type="password"
              placeholder="비밀번호를 입력해주세요"
              v-model="userpwd"
            />
          </li>
          <li class="modal-email-pwd-name">비밀번호 확인</li>
          <li>
            <input
              class="modal-input"
              type="password"
              placeholder="비밀번호를 입력해주세요"
              v-model="userpwdtwo"
              v-on:keyup="chkpwd"
            />
          </li>
          <li class="signup-container">
            <button class="signup-btn" v-on:click="signUp()">회원가입</button>
          </li>
          <li class="already">
            이미 회원이신가요?
            <a href="#" style="color: #a00000">로그인</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "SignupModal",
  components: {},
  data() {
    return {
      useremail: null,
      usernickname: null,
      userpwd: null,
      userpwdtwo: null,

      email_positive: false, //메일 중복
      email_negative: false, //메일 사용 가능
      name_positive: false, //닉네임 중복
      name_negative: false, //닉네임 사용 가능
      pwmismatch: false, //비밀번호 불일치
      pwmatch: false, //비밀번호 일치
    };
  },
  setup() {},
  computed: {
    ...mapState["API_URL"],
  },
  created() {},
  watched() {},
  mounted() {},
  methods: {
    // 모달닫힐때 초기화
    offModal() {
      (this.useremail = null),
        (this.usernickname = null),
        (this.userpwd = null),
        (this.userpwdtwo = null),
        (this.name_positive = false),
        (this.name_negative = false),
        (this.email_positive = false),
        (this.email_negative = false),
        (this.pwmismatch = false),
        (this.pwmatch = false);
      this.$emit("close", "signup"); // 창 닫아주세요.
    },
    // 이메일 중복검사
    chkemail() {
      axios({
        method: "get",
        url: `${this.API_URL}/users/duplicate`,
        params: {
          email: this.useremail,
        },
      })
        .then((res) => {
          if (res.data === true) {
            alert("이미 가입된 이메일 입니다.");
            this.email_positive = true;
            this.email_negative = false;
          } else {
            console.log("중복이 아닙니다.");
            this.email_positive = false;
            this.email_negative = true;
          }
        })
        .catch((err) => {
          console.log("메일중복확인실패");
          console.log(err);
        });
    },
    // 닉네임 중복검사
    chknick() {
      axios({
        method: "get",
        url: `${this.API_URL}/users/duplicate`,
        params: {
          name: this.usernickname,
        },
      })
        .then((res) => {
          if (res.data === true) {
            alert("중복입니다. 다시 작성해주세요");
            this.name_positive = true;
            this.name_negative = false;
          } else {
            console.log("중복이 아닙니다.");
            this.name_positive = false;
            this.name_negative = true;
          }
        })
        .catch((err) => {
          console.log("이름중복확인실패");
          console.log(err);
        });
    },
    // 비밀번호 일치여부 확인
    chkpwd() {
      if (this.userpwd !== this.userpwdtwo) {
        this.pwmismatch = true;
        this.pwmatch = false;
        console.log(this.userpwd + this.userpwdtwo);
      } else {
        this.pwmismatch = false;
        this.pwmatch = true;
      }
    },
    // 회원가입
    signUp() {
      const name = this.usernickname;
      const password = this.userpwd;
      const email = this.useremail;

      const payload = {
        name: name,
        password: password,
        email: email,
      };

      if (this.useremail === null || this.usernickname === null) {
        //이메일이나 닉네임이 빈게 있으면
        console.log("비었다.");
      } else {
        if (this.email_negative === true && this.name_negative === true) {
          this.$store.dispatch("signUp", payload);
          this.offModal();
        } else if (
          this.email_negative === true &&
          this.name_negative === false
        ) {
          console.log("닉네임이 중복입니다.");
        } else if (
          this.email_negative === false &&
          this.name_negative === true
        ) {
          console.log("이미 가입된 이메일 입니다.");
        }
      }
    },
  },
};
</script>

<style scoped>
.signup-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.already {
  text-align: center;
}

.signup-btn {
  background-color: rgba(253, 253, 253, 70%);
  border-radius: 20px;
  width: 170px;
  height: 50px;
  border: 0px;
  margin: 25px;
  font-size: 20px;
}

.signup-btn:hover {
  background-color: rgb(253, 253, 253);
}

.modal-email-pwd-name {
  font-weight: 500;
  font-size: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
}

.modal-btn-duplicate {
  background-color: rgb(253, 253, 253, 70%);
  border-radius: 20px;
  border: 0px;
  width: 150px;
  height: 4vh;
  font-weight: 500;
  font-size: 15px;
  margin-left: 40px;
}

.modal-btn-duplicate:hover {
  background-color: rgb(253, 253, 253);
}

.modal-input {
  background-color: rgb(217, 217, 217);
  border-radius: 20px;
  border: 0px;
  width: 100%;
  height: 48px;
  padding: 20px;
}

.signup-modal-header {
  display: flex;
  margin-top: 12px;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.modal-padding {
  padding-left: 20px;
  padding-right: 20px;
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
  margin-left: calc(50vw - 200px);
  background-color: rgba(255, 255, 255, 85%);
  padding: 20px;
  border: 1px solid #888;
  width: 500px;
  height: 700px;
  border-radius: 30px;
}

/* The Close Button */
.hidden-icon {
  visibility: hidden;
  font-size: 50px;
}

.close {
  color: #aaaaaa;
  font-size: 50px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.signup-text {
  font-size: 35px;
  font-weight: 500;
  text-align: center;
}
</style>

<!-- 
  -기능
  1. 이메일 입력
    - 입력실시간 유효성 테스트
    - 유효성 테스트 결과 입력칸 아래 경고

  2. 중복확인 버튼
    - 이메일 중복확인 진행
    - 중복시 이메일입력칸 아래 경고
    - 비중복시 이메일입력칸 아래 통과 경고
    - 이메일 입력칸에 새롭게 입력시, 경고 삭제 및 중복확인 체크여부 초기화
  
  3. 닉네임 입력
    - 1과 동
  
  4. 중복확인 버튼
    - 2와 동

  5. 비밀번호 입력
    - 입력실시간 유효성 테스트
  
  6. 비밀번호 확인 입력
    - 비밀번호 입력과 다를 경우, 비밀번호 확인 입력칸 아래 경고

  7. 회원가입 버튼
    - 이메일 중복검사 확인
    - 닉네임 유효성, 중복검사 확인
    - 비밀번호 유효성 및 비밀번호 확인까지 확인
    - 이후 회원가입이 정상이라면, alert 해당메일로 인증링크 발송안내, 모달창 닫기
    - 비정상일 경우, alert 가입실패안내, 모달창 유지

  8. 로그인 링크(ㅇ)
    - 로그인 링크 클릭시, 로그인 모달창 열림
 -->
