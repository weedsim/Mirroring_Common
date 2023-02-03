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
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                          모달내놔!
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                                ...
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                              </div>
                            </div>
                          </div>
                        </div>
                        
                        <BIconGearFill v-on:click="test">
                          
                        </BIconGearFill>
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
import { BIconGearFill } from "bootstrap-icons-vue";
import UserComp from "@/components/ingame/UserComp.vue"

export default {
  name: "GameTitle",
  created() {
    
  },
  methods:{
    test(){
      console.log("test!!!~") 
      // 모달켜기를 넣어야함
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