<template>
    <div class="m-0 p-0 users">
      <div class="m-0 p-0 row" style="height:40%;">
          <div class="m-0 p-0" style="width:20%">
              <user-comp></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              
              <user-comp @click="toggleShow"></user-comp>
              <div v-if="show">liar</div> 
              
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp></user-comp>
          </div>
      </div>
      <div class="m-0 p-0 row" style="height:10%;"></div>
      <div class="m-0 p-0 row" style="height:40%;">
          <div class="m-0 p-0" style="width:20%">
              <user-comp></user-comp>
          </div>
          
          <div class="m-0 p-0" style="width:20%">
              <user-comp></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp></user-comp>
          </div>
      </div>
    </div>
  </template>
  
  <script>
  import UserComp from "@/components/ingame/UserComp.vue"
  
  export default {
    components: { UserComp },
    name: "GameUser",
  //   data: {
  //     show: true
  //   },
  el:'#liar',
  data(){
      return{
          show: false
      }
  },
    created() {
      
    },
    methods:{
      toggleShow(){
          this.show = !this.show;
          
      }
    }
  }
  </script>
  
  <style></style>