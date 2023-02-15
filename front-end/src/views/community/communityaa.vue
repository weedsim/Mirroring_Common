<template>
  <navbar />
  <MainCommunityVue />
</template>

<script>
import navbar from "@/components/NavView.vue";
import MainCommunityVue from "@/components/community/MainCommunity.vue";

export default {
  name: "Community",
  components: { navbar, MainCommunityVue },
  data() {
    return {};
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {},
};
</script>

<style scoped>
.maincommunity {
  background-image: url(../../assets/lobby/lobby.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  color: white;
}
</style>
