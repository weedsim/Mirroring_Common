<template>
  <!-- <nav id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/ingame">InGame</router-link> |
    <router-link to="/inlobby">InLobby</router-link>
  </nav> -->
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

/* 코딩 끝나면 지워주세요 */
div{
    border-style: solid;
    border-width: 1px;
}


</style>
