<template>
  <navbar />
  <div class="maindetail" style="height: 94vh;">
    <div class="m-0 p-0 outer">
      <div class="m-0 p-0 row">
        <div class="m-0 p-0 col-2"></div>
        <div class="m-0 p-0 col-8">
          <!-- 반복문을 통해 컨테이너 추가 가능 -->
          <div style="height: 10vh;">
            <InputHeader></InputHeader>
          </div>
          <div style="height: 15vh;">
            <DetailTitle></DetailTitle>
          </div>
          <div style="height: 30vh;">  
            <DetailContent style="height:25vh"></DetailContent>
          </div>
          <div style="height: 35vh;">
            <DetailReply></DetailReply>
          </div>
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
import { BIconArrowRight } from "bootstrap-icons-vue";


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
.arrow{
  size: 5px;
}
</style>