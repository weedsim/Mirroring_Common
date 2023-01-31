import { createStore } from "vuex";
import axios from "axios";
import router from "@/router";

const API_URL = "http://127.0.0.1:8080";

export default createStore({
  state: { isShow: false, accessToken: null, refreshToken: null },
  getters: {
    isLogin(state) {
      return state.token ? true : false;
    },
  },
  mutations: {
    // 회원가입 && 로그인
    SAVE_TOKEN(state, accessToken, refreshToken) {
      state.accessToken = accessToken;
      state.refreshToken = refreshToken;
      router.push({ name: "main" });
    },
    DELETE_TOKEN(state) {
      state.token = null;
      state.user_pk = null;
      state.username = null;
      router.push({ name: "home" }).catch(() => {});
    },
  },
  actions: {
    async signUp(context, payload) {
      try {
        await axios({
          method: "post",
          url: `${API_URL}/users/`,
          data: {
            name: payload.name,
            password: payload.password,
            email: payload.email,
          },
        }).then((res) => {
          console.log(res);
          // context.commit("SAVE_TOKEN", res.data.key);
        });
      } catch (error) {
        console.log(error);
      }
    },
    logIn(context, payload) {
      axios({
        method: "post",
        url: `${API_URL}/accounts/login/`,
        data: {
          username: payload.username,
          password: payload.password,
        },
      }).then((res) => {
        context.commit("SAVE_TOKEN", res.data.key);
      });
    },
    logInKakao(context, payload) {
      context.commit("SAVE_TOKEN", payload.token, payload.refreshToken);
    },
    logOut(context) {
      axios({
        method: "post",
        url: `${API_URL}/accounts/logout/`,
      }).then((res) => {
        if (res) {
          console.log(res);
          context.commit("DELETE_TOKEN");
        }
      });
    },
  },
  modules: {},
});
