import { createStore } from "vuex";

// 오픈비두 넣기(x)
const session = {
  isJoin: false,
  isReady: false,
  ovSession: {},
};

const sessions = [];

for (let i = 0; i < 10; i++) {
  let temp = Object.assign({}, session);
  sessions.push(temp);
}

// idx test
let bits = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
for (let i = 0; i < 10; i++) {
  if (bits[i]) {
    // 접속테스트
    sessions[i].isJoin = true;
    // 레디 테스트
    // sessions[i].isReady = false;
  }
}

export default createStore({
  state: {
    // 세션 구조(깊은 복사해서 사용)
    session: session,
    // idx위치의 세션의 상태
    sessions: sessions,
    // Object.assign(dest, this.state.session)

    myIdx: -1,
  },
  getters: {},
  mutations: {},
  actions: {},
  modules: {},
});
