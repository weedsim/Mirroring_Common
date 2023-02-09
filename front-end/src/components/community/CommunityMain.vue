<template>
<div class="main">
  <ul>
    <li>  
      <button class="btn btn-secondary"><router-link to="detail"  style="text-decoration:none; color:white">detail 임시</router-link></button>
    </li>
    <li>[공지]</li>
    <li>[게시글]</li>
    <ul v-for="(item, index) in items" :key="index">
      <li v-on:click="goDetail(item.id)">{{ item.id }} {{ item.title }}</li>
      <li></li>

    </ul>
    <li></li>
    <li></li>
    <li></li>
  </ul>
  
</div>
</template>


<script>
import MainDetail from './MainDetail.vue';  //뒤에 변수명을 넣어서 각각의 디테일로 갈 수 있게 만들어야함
import MainInput from './MainInput.vue';
import router from "@/router";

// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";

export default {
  components: { MainDetail, MainInput },
  name: "CommunityMain",
  data() {
    return {
      items: [ {id: 1, "title":"testtitle" }, {id: 2, "title":"testtitle"} ],
      currentItem: {
        id: null,
        name: ''
      }
    };
  },
  created() {
    this.fetchItems();
    // 받아올 메서드 이름 한번은(ex)this.userinfo )
  },
  methods: {
    goDetail(idx){
      router.push({ name: "article", params: { idx } });
    },
    //내가 크롬에서 ~데이터를 찾고싶다
    
    fetchItems() {
      // Fetch items from the API
    },
    
  },
  createOrUpdateItem() {
      // Create or update an item using the API
      this.fetchItems();
    },
    editItem(item) {
      // Set the current item for editing
      this.currentItem = { ...item };
    },
    deleteItem(item) {
      // Delete an item using the API
      this.fetchItems();
    },
    cancel() {
      // Reset the current item
      this.currentItem = {
        id: null,
        name: ''
      };
    }
};
</script>

<style>
/* .main {
  background-image: url(../../assets/lobby/lobby.jpg);
  background-repeat: no-repeat;
  background-size:cover;
  color: white;
} */
</style>
