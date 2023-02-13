const playGameStore = {
  namespaced: true,
  state: {
    userInfo: [
      { name: "감자", sex: "남" },
      { name: "고구마", sex: "여" },
    ],
    num: 1,
  },
  getters: {},
  mutations: {
    TEST_ONE(state) {
      state.num += 1;
    },
  },
  actions: {
    testone(context) {
      context.commit("TEST_ONE");
    },
  },
};

export default playGameStore;
