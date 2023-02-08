<template>
  <div>
    <reply-header></reply-header>
    <reply-write></reply-write>
    <reply-list></reply-list>
  </div>
</template>

<script>
import ReplyHeader from './reply/ReplyHeader.vue';
import ReplyWrite from './reply/ReplyWrite.vue';
import ReplyList from './reply/ReplyList.vue';

export default {

  components: { ReplyHeader, ReplyList, ReplyWrite },
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