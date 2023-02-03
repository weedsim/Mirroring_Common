<template>
  <div class="m-0 p-0 title" style="height: 100%">
    <div class="m-0 p-0 row" style="height: 10%"></div>
    <div class="m-0 p-0 row" style="height: 80%">
      <div class="m-0 p-0 col-3">
        <div class="m-0 p-0" id="title" style="color: white; height: 100%">
          주제어
        </div>
      </div>
      <div class="m-0 p-0 col-6">
        <div class="m-0 p-0" id="title" style="color: white; height: 100%">
          타이머
          <button v-on:click="showRemaining">test</button>
          {{ displaySeconds }} : {{ displayMilliSeconds }}
        </div>
      </div>
      <div class="m-0 p-0 col-3"></div>
    </div>
    <div class="m-0 p-0 row" style="height: 10%"></div>
  </div>
</template>

<script>
export default {
  name: "GameHeader",
  components: {},
  data() {
    return {
      displaySeconds: 0,
      displayMilliSeconds: 0,
    };
  },
  computed: {
    _seconds() {
      return 1000;
    },
    _milliseconds() {
      return 10;
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
    showRemaining() {
      var distance = 1000 * 20;

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
    },
  },

  setup() {},
  mounted() {},
};
</script>

<style scoped></style>
