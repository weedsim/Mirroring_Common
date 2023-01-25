<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <router-link to="/main" class="navbar-brand nav-link"
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
            <router-link to="/lobby" class="nav-link">커뮤니티</router-link>
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
</template>

<script>
export default {
  name: "navbar",
};
</script>
<style scoped>
#logo {
  height: 30px;
  margin: 0px;
  padding: 0px;
  border: 1px red solid;
}
.navbar {
  padding: 0px;
}
</style>
