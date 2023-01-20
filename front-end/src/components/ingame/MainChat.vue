<template>
  <div class="m-0 p-0 chat">
    <div class="m-0 p-0 row" style="height:90%">b_chatlog</div>
    <div class="m-0 p-0 row" style="height:5%">b_blank</div>
    <div class="m-0 p-0 row" style="height:5%">
        <div class="m-0 p-0 col-9">
            b_chat_input
        </div>
        <div class="m-0 p-0 col-3">
            b_chat_btn
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
}
</style>