<template>
  <navbar></navbar>
  <div class="m-0 p-0 main">
    <h1>dd</h1>
    <h1>dd</h1>
    <h1>dd</h1>
    <h1>dd</h1>
    <h1>dd</h1>
    <h1>dd</h1>
    <h1>dd</h1>
    <h1>dd</h1>
    
  </div>
</template>

<script>
import MainDetail from './MainDetail.vue';  //뒤에 변수명을 넣어서 각각의 디테일로 갈 수 있게 만들어야함
import MainInput from './MainInput.vue';
import axios from 'axios';
import navbar from '@/components/NavView.vue'


// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";

export default {
  components: { MainDetail, MainInput },
  name: "CommunityMain",
  data() {
    return {
      
    };
  },
  created() {
    // 받아올 메서드 이름 한번은(ex)this.userinfo )
  },
  methods: {
    
    
  },
};
</script>

<style>

</style>
