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
              아직 회원이 아니신가요?? <a href="#">회원가입</a>
            </li>
            <li style="font-size: small">
              비밀번호를 잊어버리셨나요? <a href="#">비밀번호 찾기</a>
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
    return {};
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {},
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
  width: 80%;
}
li {
  margin-bottom: 1vh;
}
input {
  width: 100%;
}
</style>
