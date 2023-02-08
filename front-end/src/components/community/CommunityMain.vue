<template>
<div class="main">
  
  <button class="btn btn-secondary"><router-link to="detail"  style="text-decoration:none; color:white">detail 임시</router-link></button>
  <h1>1111</h1>
  <h1>1111</h1>

</div>
</template>


<script>
import MainDetail from './MainDetail.vue';  //뒤에 변수명을 넣어서 각각의 디테일로 갈 수 있게 만들어야함
import MainInput from './MainInput.vue';




// const API_URL = "http://127.0.0.1:8080";
// const API_URL = "http://i8a706.p.ssafy.io:8080";

export default {
  components: { MainDetail, MainInput },
  name: "CommunityMain",
  data() {
    return {
      items: [],
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
