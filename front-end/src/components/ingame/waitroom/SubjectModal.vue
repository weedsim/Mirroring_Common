<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <slot name="header"> 주제어 선택</slot>
          </div>

          <div class="modal-body">
            <ul>
              <li v-for="(item, index) in subjects" v-bind:key="index">
                <input type="radio" v-bind:value="item.id" v-model="selected" />
                {{ item.name }}
              </li>
            </ul>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button v-on:click="postSubject">시작</button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import axios from "axios";
import VueCookies from "vue-cookies";

export default {
  components: {},
  data() {
    return {
      API_URL: this.$store.state.API_URL,
      subjects: [],
      selected: "",
    };
  },
  setup() {},
  created() {
    // this.getSubject();
  },
  mounted() {
    this.selected = this.subjects[0];
  },
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
    postSubject() {
      console.log("방번호" + this.$store.state.gameinfo.roomId);
      axios({
        method: "GET",
        // url: `${API_URL}/rooms`,
        url: `${this.API_URL}/subjects/${this.selected}/words`,
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
        });
    },
  },
};
</script>

<style scoped>
ul {
  list-style: none;
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
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}
</style>
