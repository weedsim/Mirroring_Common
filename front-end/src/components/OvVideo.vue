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
  },

  mounted() {
    console.log("this.$el", this.$el);
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>
