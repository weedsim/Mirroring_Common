<template>
  <ov-video :stream-manager="streamManager" style="margin: 0; padding: 0px" />
  <!-- <div><p>{{ clientData }}</p></div> -->
</template>

<script>
import OvVideo from "./OvVideo";

export default {
  name: "UserVideo",

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      console.log(666, this.streamManager);
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>
