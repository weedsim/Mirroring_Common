<template>
  <div id="resultModal" name="modal">
    <div class="modal-mask">
      <div class="modal-container">
        <div class="modal-header">
          <!-- <button v-on:click="modaloff">x</button> -->
          <div
            v-if="this.result.winner === `LIAR`"
            style=""
            class="liar-screen"
          >
            <div>
              <OvVideo
                :streamManager="this.getAll[this.result.liarIdx]"
                style="width: 400px; border-radius: 40px"
              />
            </div>
            <div>
              <OvVideo
                v-if="this.result.spyIdx !== null"
                :streamManager="this.getAll()[this.result.spyIdx]"
                style="width: 400px; border-radius: 40px"
              />
            </div>
          </div>
          <div v-else>
            <div
              v-for="(item, index) in this.getAll"
              class="citizen-screen"
              :key="index"
            >
              <OvVideo
                v-if="
                  index !== this.getAll[this.result.liarIdx] &&
                  index !== this.getAll[this.result.spyIdx]
                "
                :streamManager="this.getAll[index]"
                style="width: 300px; border-radius: 30px"
              />
            </div>
          </div>
        </div>
        <div class="modal-body">
          <div>
            <div>
              <div
                style="
                  color: red;
                  font-size: 40px;
                  font-weight: 600;
                  text-align: center;
                "
              >
                {{ result.winner }} <span style="color: black">WIN</span>
              </div>
            </div>
          </div>

          <div>
            <div>
              <div
                style="font-size: 20px; text-align: center; margin-top: 10px"
              >
                제시어 <span style="color: #00aa25">{{ result.word }}</span>
              </div>
            </div>
          </div>
          <ul v-for="(item, index) in result.votes" :key="index">
            <li
              v-if="item.voter !== result.liar"
              v-bind:class="[
                { green: item.isCorrect },
                { red: !item.isCorrect },
              ]"
              style="
                display: flex;
                justify-content: center;
                align-items: center;
              "
            >
              <div style="width: 100px; position: relative">
                {{ item.voter }}
              </div>
              <div style="width: 100px; display: flex; justify-content: center">
                <img
                  v-if="item.isCorrect"
                  src="@/assets/ingame/arrow48g.png"
                  alt=""
                  style="margin: 10px 10px"
                />
                <img
                  v-else
                  src="@/assets/ingame/arrow48r.png"
                  alt=""
                  style="margin: 0 10px"
                />
              </div>
              <div v-if="item.target !== null" style="width: 100px">
                {{ item.target }}
              </div>
              <div v-else style="width: 100px; text-align: end">투표 안함</div>
            </li>

            <li
              v-if="item.voter === result.liar"
              style="
                display: flex;
                justify-content: center;
                align-items: center;
              "
            >
              <div style="width: 100px; position: relative">
                <div class="liar-text">liar</div>
                {{ item.voter }}
              </div>
              <div style="width: 100px; display: flex; justify-content: center">
                <img
                  src="@/assets/ingame/arrow48g.png"
                  alt=""
                  style="margin: 0 10px"
                />
              </div>
              <div v-if="item.target !== null" style="width: 100px">
                {{ item.target }}
              </div>
              <div v-else style="width: 100px; text-align: end">투표 안함</div>
            </li>
          </ul>
        </div>
        <div class="modal-footer">
          <button v-on:click="endGame" class="btn-continue">계속하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
import OvVideo from "@/components/OvVideo.vue";

export default {
  name: "resultModal",
  components: {
    OvVideo,
  },
  data() {
    return {
      localResult: this.result,
    };
  },
  setup() {},
  created() {},
  mounted() {},
  computed: {
    ...mapState(["result", "myIdx", "gameinfo"]),
    ...mapGetters(["getAll"]),
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
      // this.$store.dispatch("chgUnReady", this.myIdx);
    },
  },
};
</script>

<style scoped>
.modal-footer {
  display: flex;
  width: 100%;
  justify-content: center;
}
.liar-text {
  position: absolute;
  left: -25px;
  top: -10px;
  color: red;
  -webkit-transform: rotate(-30deg);
  -moz-transform: rotate(-30deg);
}
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
  min-height: 800px;
  min-width: 600px;
  width: 50vw;
  height: 90vh;
  margin: 5vh 25vw;
  padding: 20px;
  background-color: rgba(255, 255, 255, 95%);
  border-radius: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header {
  height: 40%;
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
  height: 45%;
}
.btn-continue {
  background-color: rgb(217, 217, 217);
  border-radius: 20px;
  border: none;
  width: 180px;
  height: 50px;
  font-size: 20px;
}
.liar-screen {
  display: flex;
  justify-content: center;
  width: 100%;
  height: 100%;
  margin-top: 20px;
}
.citizen-screen {
  display: flex;
  justify-content: center;
  width: 30%;
  height: 50%;
}
</style>
