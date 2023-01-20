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
                id="useremain"
                class="form-control"
              />
            </li>
            <li style="margin-top: 3vh">비밀번호</li>
            <li>
              <input
                type="password"
                placeholder="비밀번호를 입력해주세요"
                id="userpwd"
                class="form-control"
              />
            </li>
            <li style="text-align: center; margin-top: 3vh">
              <button type="button" class="btn btn-primary btn-sm">
                로그인
              </button>
            </li>
            <hr />
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
            <li>
              <div style="display: flex; justify-content: space-between;">
                <button type="button" class="btn btn-primary btn-sm" @click="google">구글</button>
                  <button type="button" class="btn btn-primary btn-sm" @click="kakao">카카오</button>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { mapMutations, mapActions } from 'vuex'

export default {
  name: "LoginModal",
  components: {},
  data() {
    return {
      email: '',
      password: ''
    };
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {
    kakao(){
          location.href = 'http://localhost:3000/oauth2/authorization/google?redirect_uri=http://localhost:3000/oauth/redirect';
    },
    google(){
          location.href = 'http://localhost:3000/oauth2/authorization/google?redirect_uri=http://localhost:3000/oauth/redirect';
    },
    login() {
      axios.post('http://localhost:8080/login',{email:this.email,password:this.password})
        .then(res => {
            const token = res.data.body.token
            console.log(token)
        });
    }
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
