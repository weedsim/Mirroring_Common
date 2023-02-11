<template>
  <video
    autoplay
    style="
      width: 100%;
      height: 100%;
      border-radius: 10px;
      margin: 0;
      padding: 0;
    "
  ></video>
</template>

<script>
export default {
  name: "OvVideo",

  props: {
    streamManager: Object,
    myIdx: Object,
    index: Object,
  },

  mounted() {
    console.log("여기에요!!!!");
    console.log("this.$el", this.$el);
    console.log(this.streamManager);
    console.log(this.myIdx);
    console.log(this.index);
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>
