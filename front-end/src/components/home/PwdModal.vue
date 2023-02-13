<template>
  <div>
    <div>
      <div>
        <div>
          <h1>비밀번호 찾기</h1>
          <button v-on:click="offModal">x</button>
        </div>
        <div>
          <ul>
            <li>이메일</li>
            <li>
              <input type="text" placeholder="이메일을 입력해주세요" />
              <button type="button">발송</button>
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
  methods: {
    offModal() {
      this.$emit("close", "pwd"); // 창 닫아주세요.
    },
  },
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
