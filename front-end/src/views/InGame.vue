<template>
  <div class="m-0 p-0 about"  style="width:100%;height:100vh;">
    <div class="m-0 p-0 row">
        <div class="m-0 p-0 col-1">
        </div>
        <div class="m-0 p-0 col-7">
            <main-game style="width:100%;height:100vh;"></main-game>
        </div>
        <div class="m-0 p-0 col-3">
            <main-chat style="width:100%;height:100vh;"></main-chat>
        </div>
        <div class="m-0 p-0 col-1">
        </div>
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
  <div
    class="modal fade"
    id="staticBackdrop"
    tabindex="-1"
    aria-labelledby="createRoomModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="createRoomModalLabel">게임생성</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            v-on:click="resetval"
          ></button>
        </div>
        <div class="modal-body" style="text-align: left">

         

          <ul style="list-style: none" class="m-0 p-0 gameoption">
            <li>방 제목</li>
            <li>
              <input type="text" class="form-control" v-model="roomtitle" />
            </li>
            <li>
              비밀번호
              <input type="checkbox" v-model="roomchk" v-on:click="chksecret" />
            </li>
            <li>
              <input
                type="text"
                v-bind:disabled="!roomchk"
                class="form-control"
                v-model="roompwd"
              />
            </li>
          </ul>
          <div class="optionwrapper">
            <div class="m-0 p-0" style="width: 50%">
              <ul
                style="list-style: none; text-align: center"
                class="m-0 p-0 gameoption"
              >
                <li>인원 수</li>
                <li>
                  <select name="player" id="" v-model="playercnt">
                    <option value="null">선택</option>
                    <option v-bind:value="4">4명</option>
                    <option v-bind:value="5">5명</option>
                    <option v-bind:value="6">6명</option>
                    <option v-bind:value="7">7명</option>
                    <option v-bind:value="8">8명</option>
                    <option v-bind:value="9">9명</option>
                    <option v-bind:value="10">10명</option>
                  </select>
                </li>
              </ul>
            </div>
            <div class="m-0 p-0" style="width: 50%">
              <ul
                style="list-style: none; text-align: center"
                class="m-0 p-0 gameoption"
              >
                <li>발언 시간</li>
                <li>
                  <select name="time" id="" v-model="talktime">
                    <option value="null">선택</option>
                    <option v-bind:value="10">10초</option>
                    <option v-bind:value="15">15초</option>
                    <option v-bind:value="20">20초</option>
                    <option v-bind:value="25">25초</option>
                    <option v-bind:value="30">30초</option>
                  </select>
                </li>
              </ul>
            </div>
          </div>
          <div class="btnwrapper">
            <button
              type="button"
              class="btn btn-primary btn-sm"
              data-bs-dismiss="modal"
              v-on:click="createGame"
            >
              생성
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import { mapState } from "vuex";
import MainChat from "../components/ingame/MainChat.vue";
import MainGame from "../components/ingame/MainGame.vue";

export default {
    name:"InGame",
    components: {
    MainChat, MainGame
  }
}




</script>

<style scoped>
.about{
  background-image: url(../assets/ingame/ingame_bg.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  opacity: 0.8;
}


</style>
