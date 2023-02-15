<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <div class="header">주제어 선택</div>
          </div>

          <div class="modal-body">
            <div
              class="btn-wrapper"
              v-for="(item, index) in subjects"
              :key="index"
            >
              <button class="btn-select" @click="select(item.id)">
                {{ item.name }}
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import axios from "axios";
import VueCookies from "vue-cookies";

import { mapState } from "vuex";
export default {
  components: {},
  data() {
    return {
      subjects: [],
    };
  },
  computed: {
    ...mapState(["API_URL"]),
  },
  setup() {},
  created() {
    this.getSubject();
  },
  mounted() {},
  methods: {
    getSubject() {
      axios({
        method: "GET",
        // url: `${API_URL}/rooms`,
        url: `${this.API_URL}/subjects`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
      })
        .then((res) => {
          this.subjects = res.data;
        })
        .catch((err) => {
          console.log("주제요청실패");
          console.log(err);
        });
    },
    select(index) {
      console.log("인덱스야", index);
      axios({
        method: "GET",
        // url: `${API_URL}/rooms`,
        url: `${this.API_URL}/subjects/${index}/words`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
        params: {
          roomId: this.$store.state.gameinfo.roomId,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.$emit("close"); // 창 닫아주세요.
        })
        .catch((err) => {
          console.log("주제전송실패");
          console.log(err);
          this.$emit("close"); // 창 닫아주세요.
        });
    },
  },
};
</script>

<style scoped>
.header {
  text-align: center;
  font-size: 20px;
  font-weight: 500;
}
.btn-wrapper {
  display: flex;
}
.btn-select {
  display: flex;
  font-size: 15px;
  margin: 10px;
  margin-bottom: 0px;
  width: 50px;
  height: 30px;
  justify-content: center;
  align-items: center;
}
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 500px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 10px;
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  display: flex;
  margin: 20px 0;
}
</style>
