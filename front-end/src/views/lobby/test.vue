<template>
  <div>
    module test 중입니다.
    {{ this.userInfo[0]["name"] }}
    {{ this.num }}
    <button v-on:click="one">버튼1</button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  components: {},
  data() {
    return {};
  },
  setup() {},
  created() {},
  mounted() {},
  computed: {
    ...mapState("playGameStore", {
      userInfo: "userInfo",
      num: "num",
    }),
  },
  methods: {
    one() {
      this.$store.dispatch("playGameStore/testone");
    },
  },
};
</script>

<style scoped></style>
