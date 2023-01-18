<template>
<div class="outer">
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col-6">
            <!-- 반복문을 통해 컨테이너 추가 가능 -->
            <component :is="comp" style="width:100%;height:100vh;"></component>
        </div>
        <div class="col-3">
        </div>
    </div>
</div>
</template>

<script>
import MainLobby from "@/components/lobby/MainLobby.vue";

export default {
    name:"OuterContainer",
    components:{
        MainLobby
    },
    data() {
        return {
            comp: 'MainLobby',
            comps: ['MainLobby']
        }
  }
}
</script>