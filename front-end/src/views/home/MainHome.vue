<template>
  <navbar />
  <div class="m-0 p-0 maintop">
    <div class="m-0 p-0 maintopwrapper">
      <h1>Liar Game</h1>
      <button type="button" class="btn btn-light">게임 시작</button>
    </div>
  </div>
  <div class="m-0 p-0 mainbottom">
    <div class="m-0 p-0 container">
      <div class="m-0 p-0 mainbottomtitle">
        <h1>게임 모드</h1>
      </div>
      <div class="m-0 p-0 mainbottomcardwrapper row">
        <!-- <div class="m-0 p-0 card g-col" style="width: 35vw"> -->
        <div class="m-0 p-0 card col-sm-5 firstcard">
          <div class="m-0 p-0 card-img-top modeone"><div class="m-0 p-0 cardframe"></div></div>
          <div class="m-0 p-0 card-body">
            <h5 class="card-title">일반 모드</h5>
            <p class="card-text">
              시민들은 제시어를 알고, 라이어는 모른다. <br />누가 라이어인지
              대화를 통해 맞추어야만 한다.
            </p>
          </div>
        </div>
        <!-- <div class="m-0 p-0 col-sm-3"></div> -->
        <div class="m-0 p-0 card col-sm-5">
          <div class="m-0 p-0 card-img-top modetwo"><div class="m-0 p-0 cardframe"></div></div>
          <div class="m-0 p-0 card-body">
            <h5 class="card-title">스파이 모드</h5>
            <p class="card-text">
              서로 정체를 알고있는 라이어와 스파이. <br />누가 스파이인지 혹은
              라이어인지 맞추어야만 한다.
            </p>
          </div>
        </div>
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
  created() {},
  mounted() {},
  methods: {},
};
</script>

<style scoped>
.maintop {
  background-image: url(../../assets/home/glass-top-b.png),
    url(../../assets/home/glass-bottom-a.png),
    url(../../assets/home/home-top.jpg);
  height: 100vh;
  background-position: 0 0, bottom, 0 0;
  background-repeat: no-repeat;
  background-size: 100vw auto, 100vw auto, 100vw auto;
  display: grid;
  place-items: center;
}
.maintopwrapper {
  text-align: center;
}
.maintopwrapper h1 {
  color: red;
}
.maintopwrapper button {
  opacity: 0.7;
}
.mainbottom {
  background-image: url(../../assets/home/home-bottom.jpg);
  height: 100vh;
}

.mainbottomtitle {
  padding-top: 10vh;
  padding-bottom: 5vh;
  color: #ede0ce;
}

/* .mainbottomcardwrapper {
  display: grid;
  justify-content: space-between;
} */

.card {
  background-color: #332332;
  padding: 0px;
}
.firstcard {
  margin-right: 15%;
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
