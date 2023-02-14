<template>
  <div id="resultModal">
    <div>
      <div>
        <div>
          <h1>게임 결과</h1>

          <button v-on:click="modaloff">x</button>
        </div>
        <div>
          <ul>
            <div>
              <div></div>
              <div>
                <h1>본인진영(data)</h1>
              </div>
              <div>
                <h1>승패(data)</h1>
              </div>
              <div></div>
            </div>

            <div>
              <div></div>
              <div>
                <div>제시어</div>
              </div>
              <div>
                <div>제시어정답(data)</div>
              </div>
              <div></div>
            </div>
            <li>지목한사람(data) [화살표] 지목당한사람(data) {for문}</li>
          </ul>
          <div>
            <button>계속하기(대기방이동)</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "resultModal",
  components: {},
  data() {
    return {};
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {
    modaloff() {
      this.$store.commit("OFF_RESULT");
      // wait룸으로 돌아가기.
    },
  },
};
</script>

<style scoped></style>
