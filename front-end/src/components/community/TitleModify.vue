<template>
    <navbar />
    <div class="maininput" style="height: 95vh;">
      
      <div class="m-0 p-0 row">
        <div class="m-0 p-0 col-2"></div>
        <div class="m-0 p-0 col-8">  
          <InputHeader></InputHeader>
          <InputTitle></InputTitle>
          <InputContent></InputContent>
          <div class="m-0 p-0 row">
            <div class="m-0 p-0 col-3">
              
            </div>
            <div class="m-0 p-0 col-3">
              <button class="btn btn-secondary btn-lg"><router-link to="maindetail"  style="text-decoration:none; color:white">확인</router-link></button>
            </div>
            <div class="m-0 p-0 col-3">
              <button class="btn btn-secondary btn-lg"><router-link to="maindetail"  style="text-decoration:none; color:white">취소</router-link></button>
            </div>
            <div class="m-0 p-0 col-3">
          
            </div>
          </div>
        <div class="m-0 p-0 col-2"></div>
      </div>
    </div>
    </div>
  </template>
  
  <script>
  import InputTitle from './InputTitle.vue';
  import InputContent from './InputContent.vue';
  import navbar from "@/components/NavView.vue";
  import CommunityHeader from './CommunityHeader.vue';
  import InputHeader from './InputHeader.vue';
  
  export default {
  
    components: { InputTitle, InputContent, navbar, CommunityHeader, InputHeader },
    name: "titlemodify",
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
      // 받아올 메서드 이름 한번은(ex)this.userinfo )
      this.fetchItems();
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
  
  }
  </script>
  
  <style>
  .maininput {
    background-image: url(../../assets/lobby/lobby.jpg);
    background-repeat: no-repeat;
    background-size:cover;  
    color: white;
  }
  </style>