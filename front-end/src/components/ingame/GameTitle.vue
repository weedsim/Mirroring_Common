<template>
  <div class="m-0 p-0 title" style="height:100%">
    <div class="m-0 p-0 row" style="height:10%">
    </div>
    <div class="m-0 p-0 row" style="height:80%">
        <div class="m-0 p-0 col-3"></div>
        <div class="m-0 p-0 col-6">
            <div class="m-0 p-0 rounded-3" id="title">
                title
            </div>
        </div>
        <div class="m-0 p-0 col-3">
            <div class="m-0 p-0 rounded-3">
                <div class="m-0 p-0 row">
                    <div class="m-0 p-0 col-7">
                    </div>
                    <div class="m-10 p-10 col-5">
                        setting
                        <img src="@/assets/icon/icon_setting.png" alt="" id="icon_setting">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="m-0 p-0 row" style="height:10%">
    </div>
  </div>
</template>

<script>
import UserComp from "@/components/ingame/UserComp.vue"

export default {
  name: "GameTitle",
  created() {
    
  },
}
</script>

<style>
#icon_setting{
  width:30%;
  height:20px;
  margin-left: 15px;
}
#title{
  text-align: center;
}
</style>