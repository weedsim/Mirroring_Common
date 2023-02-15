<template>
  <video autoplay></video>
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
