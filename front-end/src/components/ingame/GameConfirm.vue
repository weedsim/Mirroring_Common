<template>
  <div class="m-0 p-0 confirm">
    <div class="m-0 p-0 row" style="height:100%">
        <div class="m-0 p-0 col-2"><button type="button" class="btn btn-success" id="btn_ready">Ready</button></div>
        <div class="m-0 p-0 col-5"></div>
         <div class="m-0 p-0 col-3"></div>
        <div class="m-0 p-0 col-2"><button type="button" class="btn btn-danger" id="btn_quit">Quit</button></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "GameConfirm",
  created() {
    
  },
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