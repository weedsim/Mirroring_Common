<template>
  <div class="m-0 p-0 userSpeak">
    <!-- onspeak시 border-style변환 필요-->
  </div>
</template>

<script>
export default {
  name: "RoomCompSpeak",
  created() {
    this.btn1 = false;
  },
  data() {
    return {
        btn1:false
    };
  },
  methods:{
    btnClick(){
        this.btn1 = true;
    }
  }
}
</script>

<style></style>