<template>
  <div
    class="modal fade"
    id="signupModal"
    tabindex="-1"
    aria-labelledby="signupModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="signupModalLabel">회원가입</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            v-on:click="resetval"
          ></button>
        </div>
        <div class="modal-body" style="text-align: left">
          <ul style="list-style: none">
            <li>이메일</li>
            <li style="display: flex; justify-content: space-between">
              <input
                type="text"
                placeholder="이메일을 입력해주세요"
                id="useremail"
                class="form-control"
                style="width: 70%"
                v-model="useremail"
              />
              <button
                type="button"
                class="btn btn-primary btn-sm"
                style="margin-right: 0px"
                v-on:click="chkemail"
              >
                중복확인
              </button>
            </li>
            <li>사용 불가능한 이메일 입니다.</li>
            <li>닉네임</li>
            <li style="display: flex; justify-content: space-between">
              <input
                type="text"
                placeholder="닉네임을 입력해주세요"
                id="usernickname"
                class="form-control"
                style="width: 70%"
                v-model="usernickname"
              />
              <button
                type="button"
                class="btn btn-primary btn-sm"
                v-on:click="chknick"
              >
                중복확인
              </button>
            </li>
            <li>사용 불가능한 닉네임 입니다.</li>
            <li style="margin-top: 3vh">비밀번호</li>
            <li>
              <input
                type="password"
                placeholder="비밀번호를 입력해주세요"
                class="form-control"
                v-model="userpwd"
              />
            </li>
            <li>비밀번호는 알파벳, 숫자를 포함한 최소 0자리 입니다.</li>
            <li style="margin-top: 3vh">비밀번호 확인</li>
            <li>
              <input
                type="password"
                placeholder="비밀번호를 입력해주세요"
                class="form-control"
                v-model="userpwdtwo"
                v-on:keyup="chkpwd"
              />
            </li>
            <li>비밀번호가 일치하지 않습니다.</li>
            <li style="text-align: center; margin-top: 3vh">
              <button
                type="button"
                class="btn btn-primary btn-sm"
                v-on:click="signUp()"
              >
                회원가입
              </button>
            </li>
            <hr />
            <li style="font-size: small">
              이미 회원이신가요?
              <a href="#" data-bs-toggle="modal" data-bs-target="#loginModal"
                >로그인</a
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
  name: "SignupModal",
  components: {},
  data() {
    return {
      useremail: null,
      usernickname: null,
      userpwd: null,
      userpwdtwo: null,
    };
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {
    // 모달닫힐때 초기화
    resetval() {
      (this.useremail = null),
        (this.usernickname = null),
        (this.userpwd = null),
        (this.userpwdtwo = null);
    },
    // 이메일 중복검사
    chkemail() {},
    // 닉네임 중복검사
    chknick() {},
    // 비밀번호 일치여부 확인
    chkpwd() {
      if (this.userpwd !== this.userpwdtwo) {
        console.log(this.userpwd + this.userpwdtwo);
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

      this.$store.dispatch("signUp", payload);
    },
  },
};
</script>

<style scoped>
#signupModalLabel {
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
