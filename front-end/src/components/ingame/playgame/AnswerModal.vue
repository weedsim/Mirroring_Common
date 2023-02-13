<template>
  <transition name="answer-modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <slot name="header"> 라이어 정답 입력 </slot>
          </div>

          <div class="modal-body">
            <input v-model="answer" />
          </div>

          <div class="modal-footer">
            <div class="submit-answer" @click="sendAnswer">제출</div>
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

export default {
  data() {
    return {
      answer: "",
      API_URL: this.$store.state.API_URL,
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
.submit-answer:hover {
  cursor: pointer;
}
</style>
