<template>
  <transition name="answer-modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <div class="header">
              <span style="color: red">라이어</span>로 지목당했습니다!
            </div>
          </div>

          <div class="modal-body">
            <div class="answer-info">정답을 입력해주세요</div>
            <div style="display: flex; justify-content: center">
              <input class="input-answer" v-model="answer" />
            </div>
          </div>

          <div class="modal-footer">
            <button class="submit-answer" @click="sendAnswer">제출</button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import VueCookies from "vue-cookies";

import { mapState } from "vuex";
export default {
  data() {
    return {
      answer: "",
      roomId: this.$store.state.gameinfo.roomId,
    };
  },
  setup() {
    const show = ref(false);

    const open = () => {
      show.value = true;
    };

    const close = () => {
      show.value = false;
    };

    return {
      show,
      open,
      close,
    };
  },
  computed: {
    ...mapState(["API_URL"]),
  },
  methods: {
    sendAnswer() {
      let ans = this.answer;
      console.log("정답입력 전송 axios 시작");
      console.log(ans);
      axios({
        method: "POST",
        // url: `${API_URL}/rooms`,
        url: `${this.API_URL}/rooms/${this.roomId}/games/answer`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
        data: {
          answer: ans,
        },
      })
        .then((res) => {
          this.subjects = res.data;
          this.$store.commit("OFF_ANSWER");
        })
        .catch((err) => {
          console.log("정답제출실패");
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.answer-info {
  font-size: 30px;
  text-align: center;
  margin-top: 70px;
}
.header {
  font-size: 40px;
  text-align: center;
  margin-top: 40px;
  font-weight: 500;
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
.modal-footer {
  display: flex;
  justify-content: center;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 600px;
  height: 500px;
  margin: 0px auto;
  padding: 20px;
  background-image: url(@/assets/ingame/liaranswer.png);
  border-radius: 20px;
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
.input-answer {
  border-left-width: 0;
  border-right-width: 0;
  border-top-width: 0;
  border-bottom-width: 1;
  background-color: transparent;
  width: 200px;
  margin-top: 60px;
  font-size: 20px;
  text-align: center;
  padding-bottom: 5px;
}
.input-answer:focus {
  outline: none;
}
.submit-answer {
  background-color: rgba(217, 217, 217, 60%);
  width: 120px;
  height: 50px;
  border: none;
  border-radius: 20px;
  font-size: 20px;
  margin-top: 40px;
  text-align: center;
}
</style>
