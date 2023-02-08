<template>
    <navbar />
    <div class="maincommunity" style="width: 100%; height: 95vh">
      <h1>커뮤니티</h1>
      <table>
        <thead>
          <tr>
            
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in items" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.content }}</td>
            <td>
              <button @click="editItem(item)">Edit</button>
              <button @click="deleteItem(item)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
      <form v-if="isEditFormVisible" @submit.prevent="updateItem">
        <input type="text" v-model="selectedItem.name" />
        <input type="text" v-model="selectedItem.content" />
        <button type="submit">Update</button>
        <button type="button" @click="closeForm">Cancel</button>
      </form>
      <form v-if="!isEditFormVisible" @submit.prevent="createItem">
        <input type="text" v-model="newItem.name" placeholder="제목" />
        <input type="text" v-model="newItem.content" placeholder="내용" />
        <button type="submit">Create</button>
      </form>
    </div>
    
  </template>
  
  <script>
  import navbar from "@/components/NavView.vue";
  import { BIconBellFill } from "bootstrap-icons-vue";
  
  export default {
    name: "Community",
    components: { navbar },
    data() {
      return {
        items: [
          { id: 1, name: "Item 1" }
        ],
        isEditFormVisible: false,
        selectedItem: {},
        newItem: { name: "" }
      }
    },
    setup() {},
    created() {},
    mounted() {},
    methods: {
      createItem() {
        this.items.push({
          id: this.items.length + 1,
          name: this.newItem.name
        });
        this.newItem.name = "";
      },
      editItem(item) {
        this.selectedItem = item;
        this.isEditFormVisible = true;
      },
      updateItem() {
        this.isEditFormVisible = false;
      },
      deleteItem(item) {
        this.items = this.items.filter(i => i.id !== item.id);
      },
      closeForm() {
        this.isEditFormVisible = false;
      }
    },
  };
  </script>
  
  <style scoped>
  .maincommunity {
    background-image: url(@/assets/lobby/lobby.jpg);
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
  
  