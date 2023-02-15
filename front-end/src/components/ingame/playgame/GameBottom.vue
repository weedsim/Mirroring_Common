<template>
  <div style="display: flex">
    <div
      class="m-0 pt-5"
      style="
        width: 30%;
        display: flex;
        justify-content: flex-end;
        align-items: center;
      "
    >
      <button class="circle">
        <img src="@/assets/ingame/mic32-on.png" alt="" class="tmpImg" />
      </button>
      <button class="circle">
        <img src="@/assets/ingame/video30-on.png" alt="" class="tmpImg" />
      </button>
    </div>
    <div
      class="m-0 pt-5"
      style="
        width: 40%;
        text-align: center;
        position: relative;
        display: flex;
        align-items: center;
        flex-direction: column;
        justify-content: center;
      "
    >
      <div
        style="color: white; font-size: 25px"
        v-if="this.myRole.role === 'liar'"
      >
        <div>
          당신은 <span style="font-size: 30px; color: red">라이어</span> 입니다.
        </div>
        <div>주제 : {{ myRole.subject }}</div>
        <div>
          제시어 : <span style="font-size: 30px; color: red"> ???</span>
        </div>
      </div>
      <div
        v-if="this.myRole.role === 'spy'"
        style="color: white; font-size: 25px"
      >
        <div>
          당신은 <span style="font-size: 30px; color: red">스파이</span> 입니다.
        </div>
        <div>주제 : {{ myRole.subject }}</div>

        <div>
          제시어 :
          <span style="font-size: 30px; color: rgba(255, 247, 67, 90%)">{{
            myRole.word
          }}</span>
        </div>
      </div>
      <div
        v-if="this.myRole.role === 'citizen'"
        style="color: white; font-size: 25px"
      >
        <div>
          당신은 <span style="font-size: 30px; color: green">시민</span> 입니다.
        </div>
        <div>주제 : {{ myRole.subject }}</div>
        <div>
          제시어 :
          <span style="font-size: 30px; color: rgba(255, 247, 67, 90%)">{{
            myRole.word
          }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "GameBottom",
  components: {},
  data() {
    return {};
  },
  created() {},
  computed: {
    ...mapState(["myRole"]),
  },
  setup() {},
  mounted() {},
  methods: {},
};
</script>

<style scoped>
.circle {
  width: 50px;
  height: 50px;
  border-radius: 25px;
  margin-left: 10px;
}
.tmpImg {
  display: block;
  margin: 0px auto;
  padding: 0px;
  width: 30px;
}
</style>
