<template>
  <div
    class="modal fade"
    id="pwdModal"
    tabindex="-1"
    aria-labelledby="PwdModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="PwdModalLabel">비밀번호 찾기</h1>
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
            <li style="display: flex; justify-content: space-between">
              <input
                type="text"
                placeholder="이메일을 입력해주세요"
                class="form-control"
                style="width: 70%"
              />
              <button type="button" class="btn btn-primary btn-sm">발송</button>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PwdModal",
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
#PwdModalLabel {
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
/* ul {
  display: table;
  margin-right: auto;
  margin-left: auto;
  width: 80%;
  padding: 0px;
} */
li {
  margin-bottom: 1vh;
}
</style>
