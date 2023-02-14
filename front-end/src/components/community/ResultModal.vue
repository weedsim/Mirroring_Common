<template>
  <div id="resultModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="signupModalLabel">게임 결과</h1>
          <!-- <BIconCircleFill style="color:gray; text-align:center;"></BIconCircleFill> -->

          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            v-on:click="resetval"
          ></button>
        </div>
        <div class="modal-body" style="text-align: left">
          <BIconCircleFill
            style="color: gray; width: 100px; height: 100px; margin-left: 188px"
          ></BIconCircleFill>

          <ul style="list-style: none; text-align: center">
            <div class="m-0 p-0 row" style="width: 100%">
              <div class="m-0 p-0 col-3"></div>
              <div class="m-0 p-0 col-3">
                <h1 style="text-align: center; color: red; font-weight: bold">
                  LIAR
                </h1>
              </div>
              <div class="m-0 p-0 col-3">
                <h1 style="text-align: center; font-weight: bold">WIN</h1>
              </div>
              <div class="m-0 p-0 col-3"></div>
            </div>

            <div class="m-0 p-0 row" style="width: 100%">
              <div class="m-0 p-0 col-4"></div>
              <div class="m-0 p-0 col-2">
                <div style="text-align: center">제시어</div>
              </div>
              <div class="m-0 p-0 col-2">
                <div style="text-align: center; color: green">정치가</div>
              </div>
              <div class="m-0 p-0 col-4"></div>
            </div>
            <li style="text-align: center">
              Rosa<BIconArrowRight
                id="arrow"
                style="width: 80px; height: 25px"
              ></BIconArrowRight
              >Adams
            </li>
            <li style="text-align: center">
              Mai<BIconArrowRight
                id="arrow"
                style="width: 80px; height: 25px"
              ></BIconArrowRight
              >Josh
            </li>
            <li style="text-align: center">
              Josh<BIconArrowRight
                id="arrow"
                style="width: 80px; height: 25px"
              ></BIconArrowRight
              >Jaxon
            </li>
            <li style="text-align: center">
              Nina<BIconArrowRight
                id="arrow"
                style="width: 80px; height: 25px"
              ></BIconArrowRight
              >Josh
            </li>
            <li style="text-align: center">
              Jaxon<BIconArrowRight
                id="arrow"
                style="width: 80px; height: 25px"
              ></BIconArrowRight
              >Josh
            </li>
            <li style="text-align: center">
              Adams<BIconArrowRight
                id="arrow"
                style="width: 80px; height: 25px"
              ></BIconArrowRight
              >Nina
            </li>
          </ul>
          <div style="text-align: center">
            <button class="btn btn-secondary" type="button">계속하기</button>
          </div>
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
