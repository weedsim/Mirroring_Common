<template>
  <div class="m-0 p-0 title" style="height:100%">
    <div class="m-0 p-0 row" style="height:10%">
    </div>
    <div class="m-0 p-0 row" style="height:80%">
        <div class="m-0 p-0 col-3"></div>
        <div class="m-0 p-0 col-6">
            <div class="m-0 p-0 rounded-3" id="title" style="color:white">
                title
            </div>
        </div>
        <div class="m-0 p-0 col-3">
            <div class="m-0 p-0 rounded-3">
                <div class="m-0 p-0 row">
                    <div class="m-0 p-0 col-7">
                    </div>
                    <div class="m-10 p-10 col-5" style="color:white">
                        setting
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                          모달내놔!
                        </button>
                        <BIconGearFill v-on:click="test"></BIconGearFill>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="m-0 p-0 row" style="height:10%">
    </div>
  </div>
                          <!-- Modal -->
                          <!-- <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                                ...
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Understood</button>
                              </div>
                            </div>
                          </div>
                        </div> -->
</template>

<script>
import { BIconGearFill } from "bootstrap-icons-vue";
import UserComp from "@/components/ingame/UserComp.vue"

export default {
  name: "GameTitle",
  created() {
    
  },
  methods:{
    test(){
      console.log("test!!!~")
    }
  }
}
</script>

<style>
#icon_setting{
  width:30%;
  height:20px;
  margin-left: 15px;
  color:white;
}
#title{
  text-align: center;
  color:white;
}

</style>