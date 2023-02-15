<template>
  <navbar />
  <div>
    <div class="maintop">
      <div class="wrapper-start">
        <div>LIAR GAME</div>
        <router-link :to="{ name: 'lobby', query: { page: 1 } }"
          ><button class="btn-start-main">게임 시작</button></router-link
        >
      </div>
    </div>
    <div class="mainbottom">
      <div class="game-mode">
        <div>게임 모드</div>
      </div>
      <div class="card-container">
        <div class="card">
          <div class="">
            <div class="modeone">
              <div class="cardframe"></div>
            </div>
            <div class="card-text">
              <div class="card-title">일반 모드</div>
              <p class="card-context">
                시민들은 제시어를 알고, 라이어는 모른다. <br />누가 라이어인지
                대화를 통해 맞추어야만 한다.
              </p>
            </div>
          </div>
        </div>
        <div class=""></div>

        <div class="card">
          <div class="" v-on:mousemove="tmp(event)">
            <div class="modetwo">
              <div class="cardframe"></div>
            </div>
            <div class="card-text">
              <div class="card-title">스파이 모드</div>
              <p class="card-context">
                서로 정체를 알고있는 라이어와 스파이. <br />누가 스파이인지 혹은
                라이어인지 맞추어야만 한다.
              </p>
            </div>
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
    tmp(event) {
      console.log(event);
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Rye&display=swap");
/* html::-webkit-scrollbar {
  display: none;
} */
.maintop {
  background-image: url(../../assets/home/maintop.jpg);
  background-repeat: no-repeat;
  display: flex;
  justify-content: center;
  background-size: cover;
  height: 92vh;
}
.wrapper-start {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.btn-start-main {
  width: 18vw;
  height: 10vh;
  border-radius: 20px;
  border: 0px;
  background-color: rgba(217, 217, 217, 70%);
  font-size: 3.5vh;
  margin-top: 10vh;
}
.btn-start-main:hover {
  cursor: pointer;
}
.wrapper-start div {
  padding-top: 32vh;
  font-size: 10vh;
  color: #871818;
  font-family: Rye;
}
.mainbottom {
  background-image: url(../../assets/home/home-bottom.jpg);
  background-size: cover;
  height: 100vh;
  position: relative;
  background-repeat: no-repeat;
}
.game-mode {
  font-size: 6vh;
  font-weight: 600;
  text-align: center;
  padding-top: 10vh;
}
.card-container {
  padding-top: 10vh;
  display: flex;
  justify-content: space-evenly;
}
.card {
  width: 30vw;
  height: 40vh;
}
.modeone {
  background-image: url(../../assets/home/mode-one.jpg);
  background-size: cover;
  width: 30vw;
  height: 40vh;
}
.modetwo {
  background-image: url(../../assets/home/mode-two.jpg);
  background-size: cover;
  width: 30vw;
  height: 40vh;
}
.cardframe {
  background-image: url(../../assets/home/mode-frame.png);
  background-size: cover;
  background-size: 100% 212%;
  background-position: left top;
  background-repeat: no-repeat;
  width: 30vw;
  height: 40vh;
}
.cardframe:hover {
  background-image: url(../../assets/home/mode-frame.png);
  background-size: cover;
  background-size: 100% 212%;
  background-position: 0px 95%;
  background-repeat: no-repeat;
  width: 30vw;
  height: 40vh;
}
.card-text {
  background-color: rgb(51, 35, 50);
  height: 18vh;
  padding-top: 3vh;
  padding-left: 1vw;
}
.card-title {
  font-size: 30px;
  font-weight: 500;
  padding-bottom: 1vh;
  color: rgb(237, 224, 206);
  font-family: frank-new, sans-serif;
}
.card-context {
  font-size: 20px;
  font-weight: 400;
  color: rgb(144, 130, 128);
  font-family: frank-new, sans-serif;
}

.scroll::-webkit-scrollbar {
  display: none;
}
</style>
