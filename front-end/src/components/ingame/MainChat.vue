<template>
  <div class="m-0 p-0 chat">
    <div class="m-0 p-0 row" style="height:90%; color:white;">b_chatlog</div>
    <div class="m-0 p-0 row" style="height:5%; color:white;">b_blank</div>
    <div class="m-0 p-0 row" style="height:5%">
        <div class="m-0 p-0 col-9" style="color:white">
            b_chat_input
        </div>
        <div class="m-0 p-0 col-3" style="color:white">
            <button type="button" class="btn btn-info btn-sm" >b_chat_btn</button>
        </div>
        
    </div>
  </div>
</template>

<script>
export default {
  name: "MainChat",
  created() {
    
  },
}
</script>

<style scoped>
div {
  border-style: solid;
  border-width: 1px;
  border-block-color: red;
}
</style>