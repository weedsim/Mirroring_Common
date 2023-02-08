<template>
  <navbar />
  <div class="maindetail" >
    <div class="m-0 p-0 outer">
      <div class="m-0 p-0 row">
        <div class="m-0 p-0 col-2"></div>
        <div class="m-0 p-0 col-8">
          <!-- 반복문을 통해 컨테이너 추가 가능 -->
          <InputHeader></InputHeader>
          <DetailTitle></DetailTitle>
          <DetailContent></DetailContent>
          <DetailReply></DetailReply>
          
        </div>
        <div class="m-0 p-0 col-2"></div>
      </div>
    </div>
    
   
  </div>
</template>

<script>
import DetailTitle from './DetailTitle.vue';
import DetailContent from './DetailContent.vue';
import DetailReply from './DetailReply.vue';
import navbar from '@/components/NavView.vue';
import InputHeader from './InputHeader.vue';


export default {
  components: { DetailTitle, DetailContent, DetailReply, InputHeader, navbar, },
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
.maindetail {
  background-image: url(../../assets/lobby/lobby.jpg);
  background-repeat: no-repeat;
  background-size:cover;  
  color: white;
}
</style>