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
        /* webpackChunkName: "about" */ "@/components/community/MainCommunity.vue"
      ),
  },
  {
    path: "/maindetail",
    name: "maindetail",
    component: () =>
      import(
        /* webpackChunkName: "about" */ "@/components/community/MainDetail.vue"
      ),
  },
  {
    path: "/titlemodify",
    name: "titlemodify",
    component: () =>
      import(
        /* webpackChunkName: "about" */ "@/components/community/TitleModify.vue"
      ),
  },
  {
    path: "/maininput",
    name: "maininput",
    component: () =>
      import(
        /* webpackChunkName: "about" */ "@/components/community/MainInput.vue"
      ),
  },
  // {
  //   path: "/CreateArticle",
  //   name: "CreateArticle",
  //   component: () =>
  //     import(
  //       /* webpackChunkName: "about" */ "../views/community/CreateArticle.vue"
  //     ),
  // },
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
    path: "/game/room/:roomId",
    name: "room",
    component: () =>
      // import(/* webpackChunkName: "about" */ "../views/room/Room.vue"),
      import(/* webpackChunkName: "about" */ "../views/InGame.vue"),
    // axios로 방에대한 토큰값을 받아온다. 유저가 들고있는 토큰값이 일치하면 그방에대한 입장 ok
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
  {
    path: "/mypage",
    name: "mypage",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/user/Mypage.vue"),
  },
  {
    path: "/test/module",
    name: "testmodule",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/lobby/test.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
