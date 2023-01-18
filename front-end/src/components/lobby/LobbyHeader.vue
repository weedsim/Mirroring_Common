<template>
  <div class="header" style="height:100%">
    <div class="row" style="height:10%">
    </div>
    <div class="row" style="height:80%">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="rounded-3">
                title
            </div>
        </div>
        <div class="col-3">
            <div class="rounded-3">
                <div class="row">
                    <div class="col-9">
                    </div>
                    <div class="col-3">
                        refresh
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="height:10%">
    </div>
  </div>
</template>

<script>
import RoomComp from "@/components/lobby/RoomComp.vue"

export default {
  name: "LobbyHeader",
  created() {
    
  },
}
</script>

<style></style>