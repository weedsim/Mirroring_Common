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
  data() {
    return {
      publisher: this.$store.state.publisher,
    };
  },
  created() {
    console.log("좀떠라...", this.publisher);
    console.log("this.$el", this.$el);
    // this.publisher.addVideoElement(this.$el);
  },
};
</script>
