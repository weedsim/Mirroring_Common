<template>
    <div class="m-0 p-0 users">
      <div class="m-0 p-0 row" style="height:40%;">
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx=0></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx=1></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
            <user-comp curIdx=2></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx=3></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx=4></user-comp>
          </div>
      </div>
      <div class="m-0 p-0 row" style="height:10%;"></div>
      <div class="m-0 p-0 row" style="height:40%;">
          
          
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx=5></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx=6></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx=7></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx=8></user-comp>
          </div>
          <div class="m-0 p-0" style="width:20%">
              <user-comp curIdx=9></user-comp>
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
          show: false,
        //   isShowList = $store.state.isShow
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