<template>
  
  <div class="m-0 p-0 community">
    <!-- <div class="m-0 p-0 row" style="height: 20%; background-color: red"> -->
    <div class="m-0 p-0 row" style="height: 10%; background-color: red">
      <community-header></community-header>
    </div>
    <!-- <div class="m-0 p-0 row" style="height: 80%; background-color: blue"> -->
    <div class="m-0 p-0 row" style="height: 80%; background-color: blue">
      <community-main></community-main>
    </div>
    <!-- <div class="m-0 p-0 row" style="height: 20%; background-color: yellow"> -->
    <div class="m-0 p-0 row" style="height: 10%; background-color: yellow">
      <community-footer></community-footer>
    </div>
  </div>
</template>

<script>
import CommunityHeader from "@/components/community/CommunityHeader.vue";
import CommunityMain from "@/components/community/CommunityMain.vue";
import CommunityFooter from "@/components/community/CommunityFooter.vue";

export default {
  components: {
    CommunityHeader,
    CommunityMain,
    CommunityFooter,
  },
  name: "MainCommunity",
  created() {},
};
</script>

<style></style>
