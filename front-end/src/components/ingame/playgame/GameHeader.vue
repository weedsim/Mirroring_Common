<template>
  <div class="timer">{{ displaySeconds }} : {{ displayMilliSeconds }}</div>
</template>

<script>
export default {
  name: "GameHeader",
  components: {},
  data() {
    return {
      displaySeconds: 0,
      displayMilliSeconds: 0,
      testTime: 0,
    };
  },
  computed: {
    _seconds() {
      return 1000;
    },
    _milliseconds() {
      return 10;
    },
    setTimer() {
      return this.$store.state.timer;
    },
  },
  watch: {
    setTimer(newTime) {
      console.log("시간이변경됨");
      this.runTimer(newTime);
    },
  },
  created() {
    this.countDownTimer();
  },
  methods: {
    countDownTimer() {
      if (this.countDown > 0) {
        setTimeout(() => {
          this.countDown -= 1;
          this.countDownTimer();
        }, 1000);
      }
    },
    runTimer(newTime) {
      if (newTime === 0) {
        this.displaySeconds = 0;
        this.displayMilliSeconds = 0;
        return;
      }
      var distance = newTime;
      console.log("타이머돌아가요: " + newTime);
      const timer = setInterval(() => {
        // 20기본 타이머 20초 설정
        if (distance < 0) {
          clearInterval(timer);
          return;
        }
        var seconds = Math.floor(distance / 1000);
        var milliseconds = Math.floor((distance - seconds * 1000) / 10);
        this.displaySeconds = seconds;
        this.displayMilliSeconds = milliseconds;
        distance -= 10;
      }, 10);
      this.$store.commit("RESET_TIMER");
    },
  },

  setup() {},
  mounted() {},
};
</script>

<style scoped>
.timer {
  color: red;
  text-align: center;
  font-size: 3vw;
  margin-top: 5vh;
  height: 9vh;
  margin-bottom: 10px;
}
</style>
