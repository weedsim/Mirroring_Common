<template>
  <div class="m-0 p-0" style="height: 100%; width: 100%">
    <div class="m-0 p-0 row" style="height: 10%"></div>
    <div class="m-0 p-0 row" style="height: 80%">
      <div class="m-0 p-0 col-3"></div>
      <div class="m-0 p-0 col-6" style="display: flex; justify-content: center">
        <div class="title mt-4" style="display: flex">
          <div class="room-number">123</div>
          <div class="room-title">즐거운 게임 하실분~</div>
        </div>
      </div>
      <div
        class="p-0 col-3"
        style="
          display: flex;
          flex-direction: row-reverse;
          align-items: flex-end;
        "
      >
        <div style="height: 75%"></div>
        <div
          style="
            height: 25%;
            display: flex;
            flex-direction: row-reverse;
            align-items: center;
          "
        >
          <router-link :to="{ path: 'InLobby' }">
            <button class="btn-exit mx-3">나가기</button>
          </router-link>
          <div
            class="btn-setting"
            data-bs-toggle="modal"
            data-bs-target="#staticBackdrop"
          >
            <BIconGearFill v-on:click="test" class="me-1"></BIconGearFill>
            방 설정 변경
          </div>
        </div>
      </div>
    </div>
    <div class="m-0 p-0 row" style="height: 10%"></div>
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
import UserComp from "@/components/ingame/UserComp.vue";

export default {
  name: "GameTitle",
  created() {},
  methods: {
    test() {
      console.log("test!!!~");
    },
  },
};
</script>

<style scoped>
.title {
  width: 50vh;
  height: 7vh;
}
#icon_setting {
  width: 30%;
  height: 20px;
  margin-left: 15px;
  color: white;
}
.btn-setting {
  color: white;
  background-color: none;
}
.btn-setting:hover {
  cursor: pointer;
}
.room-number {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 25%;
  background-color: #909090;
  background-color: rgba(144, 144, 144, 80%);
  border-top-left-radius: 3vh;
  border-bottom-left-radius: 3vh;
  color: white;
  font-size: 3vh;
  text-align: center;
}
.room-title {
  display: flex;
  align-items: center;
  width: 75%;
  background-color: rgba(255, 255, 255, 80%);
  border-top-right-radius: 3vh;
  border-bottom-right-radius: 3vh;
  color: black;
  font-size: 2.5vh;
  font-weight: 500;
  padding-left: 1rem;
}
.btn-exit {
  height: 3vh;
  background-color: rgba(158, 158, 158, 30%);
  border: none;
  border-radius: 10px;
  /* font-size: 1.5vw; */
  font-weight: 400;
  color: white;
}
</style>
