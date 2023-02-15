<template>
  <div id="resultModal" name="modal">
    <div class="modal-mask">
      <div class="modal-container">
        <div class="modal-header">
          <h1>게임 결과</h1>

          <!-- <button v-on:click="modaloff">x</button> -->
        </div>
        <div class="modal-body">
          <div>
            <div></div>
            <div>
              <h1>{{ result.winner }}</h1>
            </div>
            <div>
              <h1>Winner</h1>
            </div>
            <div></div>
          </div>

          <div>
            <div></div>
            <div>
              <div>제시어</div>
            </div>
            <div>
              <div>{{ result.word }}</div>
            </div>
            <div></div>
          </div>
          <ul v-for="(item, index) in result.votes" :key="index">
            <li
              v-if="item.voter !== result.liar"
              v-bind:class="[
                { green: item.isCorrect },
                { red: !item.isCorrect },
              ]"
            >
              {{ item.voter }} -> {{ item.target }}
            </li>
            <li v-if="item.voter === result.liar">
              {{ item.voter }}(liar) -> {{ item.target }}
            </li>
          </ul>
        </div>
        <div class="modal-footer">
          <button v-on:click="endGame">계속하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "resultModal",
  components: {},
  data() {
    return {
      localResult: this.result,
    };
  },
  setup() {},
  created() {},
  mounted() {},
  computed: {
    ...mapState(["result"]),
  },
  methods: {
    modaloff() {
      this.$store.commit("OFF_RESULT");
      // wait룸으로 돌아가기. 게임-> playoff로 돌리기
    },
    endGame() {
      // 일단 결과창 닫고,
      this.$store.commit("OFF_RESULT");
      this.$store.commit("RESET_ISPLAYING");
    },
  },
};
</script>

<style scoped>
.green {
  color: green;
}
.red {
  color: red;
}
ul {
  list-style: none;
}
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 500px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}
</style>
