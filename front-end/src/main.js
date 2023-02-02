import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

// https://vueup.github.io/vue-quill/guide/usage.html
// quill for vue3 - Local Registration을 사용할 예정

import "bootstrap/dist/css/bootstrap.min.css"; // [bootstrap]
import "bootstrap"; // [bootstrap]

import { BootstrapIconsPlugin } from "bootstrap-icons-vue"; // [bootstrap vue icon]

import VueCookies from "vue-cookies"; // [vue cookies]

createApp(App)
  .use(store)
  .use(router)
  .use(BootstrapIconsPlugin)
  .use(VueCookies)
  .mount("#app");
