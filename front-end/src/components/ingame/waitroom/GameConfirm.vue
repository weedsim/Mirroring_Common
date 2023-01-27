<template>
  <div class="m-0 p-0 confirm">
    <div class="m-0 p-0 row" style="height:100%">
        <div class="m-0 p-0 col-2"><button type="button" @click="toggle()" class="btn btn-success" id="btn_ready">Ready</button></div>
        
        <div class="m-0 p-0 col-5"></div>
        <div class="m-0 p-0 col-3"></div>
        <div class="m-0 p-0 col-2"><router-link :to="{path:'InLobby'}"><button type="button" class="btn btn-danger" id="btn_quit">Quit</button></router-link></div>
    </div>
  </div>
</template>

<script>
// import store from '@/store';
// import { useStore } from "vuex";

export default {
  // setup() {
  //   const store = useStore();
  //   // const toggle = store.commit("isShow", store.state.isShow = !store.state.isShow)
  // },
  data () {
    return {
      isShow: false
    }
  },

  name: "GameConfirm",
  created() {
    // this.toggle = store.commit("isShow", store.state.isShow = !store.state.isShow)
  },
  methods:{
    toggle(){
      this.$store.state.isShow = !this.$store.state.isShow
     }
    }
  }

</script>

<style>
#btn_ready{
  width: fit-content;
  margin: auto;
}
#btn_quit{
  width: fit-content;
  margin: auto;
}
</style>