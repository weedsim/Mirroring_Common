<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-0">
    <div class="container-fluid m-0 p-0">
      <router-link to="/main" class="navbar-brand nav-link m-0 p-0"
        ><img src="@/assets/nav/logo.jpg" alt="logo" id="logo"
      /></router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarTogglerDemo03"
        aria-controls="navbarTogglerDemo03"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link to="/lobby" class="nav-link">게임로비</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/community" class="nav-link">커뮤니티</router-link>
          </li>
        </ul>
        <ul class="navbar-nav d-flex">
          <li class="nav-item">
            <a
              class="nav-link"
              href="#"
              data-bs-toggle="modal"
              data-bs-target="#loginModal"
              >로그인</a
            >
          </li>
          <li class="nav-item">
            <a
              class="nav-link"
              href="#"
              data-bs-toggle="modal"
              data-bs-target="#signupModal"
              >회원가입</a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" v-on:click="logout">로그아웃</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <router-link to="/main" class="nav-link"
          ><img src="@/assets/nav/logo.jpg" alt="logo" id="logo"
        /></router-link>
      </a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNavAltMarkup"
        aria-controls="navbarNavAltMarkup"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <router-link to="/lobby" class="nav-link">게임로비</router-link>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">커뮤니티</a>
          </li>
        </ul>
        <ul class="navbar-nav d-flex">
          <li class="nav-item">
            <a
              class="nav-link"
              href="#"
              data-bs-toggle="modal"
              data-bs-target="#loginModal"
              >로그인</a
            >
          </li>
          <li class="nav-item">
            <a
              class="nav-link"
              href="#"
              data-bs-toggle="modal"
              data-bs-target="#signupModal"
              >회원가입</a
            >
          </li>
        </ul>
      </div>
    </div>
  </nav> -->
  <LoginModal />
  <SignupModal />
  <PwdModal />
</template>

<script>
import LoginModal from "@/components/home/LoginModal.vue";
import SignupModal from "@/components/home/SignupModal.vue";
import PwdModal from "@/components/home/PwdModal.vue";

export default {
  components: { LoginModal, SignupModal, PwdModal },
  name: "navbar",
  methods: {
    logout() {
      const payload = {
        accessToken: this.$store.state.accessToken,
      };
      this.$store.dispatch("logOut", payload);
    },
  },
};
</script>
<style scoped>
#logo {
  height: 5vh;
  margin: 0px;
  padding: 0px;
}
img {
  margin: 0px;
  padding: 0px;
}
/* .navbar {
  padding: 0px !important;
  margin: 0px !important;
} */
li {
  padding: 0px;
  margin: 0px;
}
.nav-link {
  padding: 0px;
}
</style>
