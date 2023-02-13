<template>
  <div
    class="m-0 pt-5"
    style="
      width: 40%;
      display: flex;
      justify-content: flex-end;
      align-items: center;
    "
  >
    <button class="circle me-3 p-0">
      <BIconVolumeUpFill
        style="color: black; font-size: 1.5vw"
      ></BIconVolumeUpFill>
    </button>
    <button class="circle">
      <BIconCameraVideoOffFill
        style="color: black; font-size: 1.2vw"
      ></BIconCameraVideoOffFill>
    </button>
  </div>
  <div
    class="m-0 pt-5"
    style="
      width: 20%;
      text-align: center;
      position: relative;
      display: flex;
      align-items: center;
      flex-direction: column;
      justify-content: center;
    "
  >
    <div style="color: white; font-size: 2vw">제시어</div>
    <div v-if="subject !== 'liar'" style="color: #fff743; font-size: 3vw">
      {{ subject }}
    </div>
    <div v-else style="color: red; font-size: 3vw">
      {{ subject }}
    </div>
  </div>
</template>

<script>
export default {
  name: "GameBottom",
  components: {},
  data() {
    return {};
  },
  created() {},
  computed: {
    subject() {
      return this.$store.state.mysubject;
    },
  },
  setup() {},
  mounted() {},
  methods: {},
};
</script>

<style scoped>
.circle {
  width: 5vh;
  height: 5vh;
  border-radius: 2.5vh;
}
</style>
