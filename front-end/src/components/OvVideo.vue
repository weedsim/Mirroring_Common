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
  data() {},

  props: {
    streamManager: Object,
  },
  computed: {},

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
  methods: {},
};
</script>
