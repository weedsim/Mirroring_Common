<template>
  <div class="mb-3">
    <label for="exampleFormControlInput1" class="form-label">제목</label>
    <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="제목">
  </div>
  <div class="mb-3">
    <label for="exampleFormControlTextarea1" class="form-label">내용</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
  </div>
</template>

<script>
import DetailTitle from './DetailTitle.vue';
import DetailContent from './DetailContent.vue';
import DetailReply from './DetailReply.vue';
export default {
  components: { DetailTitle, DetailContent, DetailReply },
  name: "MainDetail",
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