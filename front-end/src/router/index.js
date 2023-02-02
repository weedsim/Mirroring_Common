import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import store from "../store/index.js";
const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/ingame",
    name: "ingame",
    component: () => import("../views/InGame.vue"),
  },
  {
    path: "/inlobby",
    name: "inlobby",
    component: () => import("../views/InLobby.vue"),
  },
  {
    path: "/main",
    name: "main",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/home/MainHome.vue"),
  },
  {
    path: "/lobby",
    name: "lobby",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/lobby/InLobby.vue"),
  },
  {
    path: "/community",
    name: "community",
    component: () =>
      import(
        /* webpackChunkName: "about" */ "../views/community/Community.vue"
      ),
  },
  {
    path: "/kakao",
    name: "kakao",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/auth/kakao.vue"),
  },
  {
    path: "/google",
    name: "google",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/auth/google.vue"),
  },
  {
    path: "/room/:roomId",
    name: "room",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/room/Room.vue"),
    beforeEnter(to, from, next) {
      try {
        if (store.state.isEnter) {
          next();
        } else {
          next("/lobby");
        }
      } catch (error) {
        console.error(error);
      }
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
