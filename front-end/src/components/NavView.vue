<template>
  <nav class="nav-main">
    <div class="nav-container">
      <router-link to="/main" class="logo"
        ><img src="@/assets/nav/logo3.jpg" alt="logo" id="logo"
      /></router-link>
      <div class="menu-container">
        <ul class="menu">
          <li>
            <router-link
              :to="{ name: 'lobby', query: { page: 1 } }"
              class="nav-link"
              >게임로비</router-link
            >
          </li>
          <li>
            <router-link to="/community" class="nav-link">커뮤니티</router-link>
          </li>
        </ul>
        <ul class="menu" v-if="$store.state.isLogin">
          <li class="nav-link">
            <a class="" href="#" v-on:click="onModal('login')">로그인</a>
          </li>
          <li class="nav-link">
            <a class="" href="#" v-on:click="onModal('signup')">회원가입</a>
          </li>
        </ul>
        <ul class="menu" v-else>
          <li>
            <router-link to="/mypage" class="nav-link">마이페이지</router-link>
          </li>
          <li>
            <a class="nav-link" href="#" v-on:click="logout">로그아웃</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <LoginModal v-if="loginShow" v-on:close="offModal" />
  <SignupModal v-if="signShow" v-on:close="offModal" />
  <PwdModal v-if="pwdShow" v-on:close="offModal" />
</template>

<script>
import LoginModal from "@/components/home/LoginModal.vue";
import SignupModal from "@/components/home/SignupModal.vue";
import PwdModal from "@/components/home/PwdModal.vue";
import VueCookies from "vue-cookies";

export default {
  components: { LoginModal, SignupModal, PwdModal },
  name: "navbar",
  data() {
    return {
      loginShow: false,
      signShow: false,
      pwdShow: false,
      // loginstatus: this.$cookies.isKey("refreshToken"),
      // istoken: "cookieValue",
    };
  },
  // computed:{
  //   loginstatus(){
  //     return this.$store.commit()
  //   }
  // },
  methods: {
    onModal(data) {
      if (data === "login") {
        this.loginShow = true;
        this.pwdShow = false;
        this.signShow = false;
      }
      if (data === "pwd") {
        this.loginShow = false;
        this.pwdShow = true;
        this.signShow = false;
      }
      if (data === "signup") {
        this.loginShow = false;
        this.pwdShow = false;
        this.signShow = true;
      }
    },
    offModal(data) {
      if (data === "login") {
        this.loginShow = false;
      }
      if (data === "pwd") {
        this.pwdShow = false;
      }
      if (data === "signup") {
        this.signShow = false;
      }
    },
    logout() {
      const payload = {
        accessToken: this.$store.state.accessToken,
        refreshToken: VueCookies.get("refreshToken"),
      };
      this.$store.dispatch("logOut", payload);
    },
  },
};
</script>
<style scoped>
.nav-main {
  background-color: black;
  height: 8vh;
}
#logo {
  height: 7vh;
  padding-top: 0.5vh;
}
.menu {
  color: white;
  font-size: 20px;
  display: flex;
}
.nav-container {
  display: flex;
  align-items: center;
}
.menu-container {
  width: 1800px;
  padding-left: 20px;
  display: flex;
  justify-content: space-between;
}
.nav-link {
  color: white;
}
.nav-link a {
  color: white;
}
.menu li {
  padding-left: 7px;
  padding-right: 7px;
}
</style>
