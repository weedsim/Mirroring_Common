<template>
  <div>
    <h1>운영자님 부탁드립니다</h1>
    <div class="m-0 p-0 row " style="width:100%">
      <div class="m-0 p-0 col-9">
        <small>2023.01.12</small>
      </div>
      <div class="m-0 p-0 col-1">
        조회수
      </div>
      <div class="m-0 p-0 col-1">
        <button class="btn btn-secondary btn-sm"><router-link :to="{name:'titlemodify'}"  style="text-decoration:none; color:white">수정</router-link></button>
      </div>
      <div class="m-0 p-0 col-1">
        <button class="btn btn-secondary btn-sm" style="text-decoration:none; color:white">삭제</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {  },
  name: "DetailTitle",
  data() {
    return {
      
    };
  },
  created() {
    // 받아올 메서드 이름 한번은(ex)this.userinfo )
  },
  methods: {
    
    
  },
}
</script>

<style>

</style>