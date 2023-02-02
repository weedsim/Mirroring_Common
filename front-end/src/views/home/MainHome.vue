<template>
  <navbar />
  <div class="m-0 p-0 maintest">
    <div class="m-0 p-0 maintop" style="height: 95vh">
      <div class="m-0 p-0 maintopwrapper">
        <h1>Liar Game</h1>
        <button type="button" class="btn btn-light">게임 시작</button>
      </div>
    </div>
    <div class="m-0 p-0 mainbottom" style="height: 100vh">
      <div class="mb-3 pt-5 mainbottomtitle">
        <h1>게임 모드</h1>
      </div>
      <div class="m-0 p-0 mainbottomcardwrapper row">
        <div class="mt-5 p-0 col-1"></div>
        <div class="mt-5 p-0 col-4">
          <div class="m-0 p-0 card">
            <div class="m-0 p-0 card-img-top modeone">
              <div class="m-0 p-0 cardframe"></div>
            </div>
            <div class="m-0 p-0 card-body">
              <h5 class="card-title">일반 모드</h5>
              <p class="card-text text-truncate">
                시민들은 제시어를 알고, 라이어는 모른다. <br />누가 라이어인지
                대화를 통해 맞추어야만 한다.
              </p>
            </div>
          </div>
        </div>
        <div class="mt-5 p-0 col-2"></div>

        <div class="mt-5 p-0 card col-4">
          <div class="m-0 p-0 card">
            <div class="m-0 p-0 card-img-top modetwo">
              <div class="m-0 p-0 cardframe"></div>
            </div>
            <div class="m-0 p-0 card-body">
              <h5 class="card-title">스파이 모드</h5>
              <p class="card-text text-truncate">
                서로 정체를 알고있는 라이어와 스파이. <br />누가 스파이인지 혹은
                라이어인지 맞추어야만 한다.
              </p>
            </div>
          </div>
        </div>
        <div class="mt-5 p-0 col-1"></div>
      </div>
    </div>
  </div>
</template>

<script>
import navbar from "@/components/NavView.vue";

export default {
  components: { navbar },
  data() {
    return {};
  },
  setup() {},
  created() {
    this.test();
  },
  mounted() {},
  methods: {
    test() {
      let accessToken = this.$store.state.accessToken;
      let refreshToken = this.$store.state.refreshToken;
      console.log(accessToken);
      console.log(refreshToken);
    },
  },
};
</script>

<style scoped>
.maintop {
  background-image: url(../../assets/home/maintop.jpg);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  display: flex;
  position: relative;
  justify-content: center;
}
.mainbottom {
  background-image: url(../../assets/home/home-bottom.jpg);
  background-size: 100% 100%;
}

h1 {
  /* margin: 0px;
  padding: 0px; */
}
.maintopwrapper {
  text-align: center;
  position: absolute;
  top: 40%;
}
.maintopwrapper h1 {
  color: red;
}
.maintopwrapper button {
  opacity: 0.7;
}

.mainbottomtitle {
  /* padding-top: 10vh; */
  margin-left: 10vh;
  padding-bottom: 5vh;
  color: #ede0ce;
}

.mainbottomcardwrapper {
  height: auto;
}
.card {
  background-color: #332332;
  padding: 0px;
}
.modeone {
  height: 45vh;
  background-image: url(../../assets/home/mode-one.jpg);
  background-size: cover;
}
.modetwo {
  height: 45vh;
  background-image: url(../../assets/home/mode-two.jpg);
  background-size: cover;
}
.cardframe {
  height: 45vh;
  background-image: url(../../assets/home/mode-frame.png);
  background-size: 100% 212%;
  background-position: left top;
  background-repeat: no-repeat;
}
.cardframe:hover {
  height: 45vh;
  background-image: url(../../assets/home/mode-frame.png);
  background-size: 100% 212%;
  background-position: 0px 95%;
  background-repeat: no-repeat;
}

.card-title {
  color: #ede0ce;
}
.card-text {
  color: #908280;
}
</style>
