<template>
  <navbar />
  <div class="community row" style="table-layout:fixed;">
  <!-- <div class="m-0 p-0 row" style="height: 20%; background-color: red"> -->
    <div class="m-0 p-0 col-2"></div>
    <div class="m-0 p-0 col-8">
      <!-- 반복문을 통해 컨테이너 추가 가능 -->
      <component :is="comp" style="width: 100%; height: 95vh"></component>
    
    
      <div class="m-0 p-0 row" style="height: 10vh">
        <community-header></community-header>
      </div>
      <!-- <div class="m-0 p-0 row" style="height: 80%; background-color: blue"> -->
      <div class="m-0 p-0 row" style="height: 72vh">
        <community-main></community-main>

      </div>
      <!-- <div class="m-0 p-0 row" style="height: 20%; background-color: yellow"> -->
      <div class="m-0 p-0 row" style="height: 10vh">
        <community-footer></community-footer>
      </div>
    </div>
    <div class="m-0 p-0 col-2"></div>
  </div>
</template>

<script>

import navbar from "@/components/NavView.vue";
// import MainCommunity from "@/components/community/MainCommunity.vue";
// import { BIconBellFill } from "bootstrap-icons-vue";

import CommunityHeader from "@/components/community/CommunityHeader.vue";
import CommunityMain from "@/components/community/CommunityMain.vue";
import CommunityFooter from "@/components/community/CommunityFooter.vue";


export default {
  name: "community",
  components: { navbar,CommunityHeader,
    CommunityMain,
    CommunityFooter, },
  data() {
    
    return {
      
    }
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {
    
  },
};
</script>

<style scoped>
.community {
  background-image: url(../../assets/lobby/lobby.jpg);
  background-repeat: no-repeat;
  background-size:cover;  
  color: white;
}
.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 3px solid #00B4CC;
  border-right: none;
  /* padding: 5px; */
  /* height: 20px;  */
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #9DBFAF;
}

.searchTerm:focus{
  color: #00B4CC;
}

.searchButton {
  width: 40px;
  height: 36px;
  border: 1px solid #00B4CC;
  background: #00B4CC;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
}

/*Resize the wrap to see the search bar change!*/

</style>

