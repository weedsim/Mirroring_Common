<template>
  <!-- <div class="m-0 p-0 chat">
    <div class="m-0 p-0 row" style="height:90%; color:white;">b_chatlog</div>
    <div class="m-0 p-0 row" style="height:5%; color:white;">b_blank</div>
    <div class="m-0 p-0 row" style="height:5%">
        <div class="m-0 p-0 col-9" style="color:white">
            b_chat_input
        </div>
        <div class="m-0 p-0 col-3" style="color:white">
            <button type="button" class="btn btn-info btn-sm" >b_chat_btn</button>
        </div>
        
    </div>
  </div> -->
  <div class="wrapper">
    <div class="user-container">
      <label for="nickname">채팅</label>
      <!-- <input type="text" id="nickname" /> -->
    </div>
    <div class="display-container">
      <ul class="chatting-list">
        <!-- 채팅하나씩 li로 집어 넣을 것 -->
        <li class="sent">
          <span class="profile">
            <span class="user">나</span>
          </span>
          <span class="message">안녕?</span>
          <span class="time">오후 2:10</span>
        </li>
        <li class="received">
          <span class="profile">
            <span class="user">상대</span>
          </span>
          <span class="message">안녕!</span>
          <span class="time">오후 2:10</span>
        </li>
      </ul>
    </div>
    <div class="input-container">
      <span>
        <input type="text" class="chatting-input" />
        <button class="send-button" style="color:white">전송</button>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainChat",
  created() {
    
  },
}
</script>

<style scoped>
div {
  border-style: solid;
  border-width: 1px;
  /* border-block-color: red; */
}
* {
  margin: 0;
  padding: 0;
}

html,
body {
  height: 100%;
}

.wrapper {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.user-container {
  background: gray;
  flex: 1;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 0.5rem;
}

.user-container label {
  font-size: 14px;
  margin-right: 1rem;
}

.user-container input {
  border-radius: 3px;
  border: none;
  height: 100%;
}

.display-container {
  background: rgb(123, 114, 103);
  flex: 12;
  overflow-y: scroll;
}

.input-container {
  flex: 1;
  display: flex;
  justify-content: stretch;
  align-items: center;
  padding: 3px;
}

.input-container span {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 0.3rem;
  width: 100%;
  height: 100%;
}

.chatting-input {
  font-size: 12px;
  height: 100%;
  flex: 8;
  border: none;
}

.send-button {
  flex: 1;
  background: black;
  border: none;
  height: 100%;
  border-radius: 3px;
}

.chatting-list li {
  width: 90%;
  padding: 0.3rem;
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
  margin-top: 0.5rem;
}

.profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  flex: 1;
}

.profile .user {
  font-size: 10px;
  margin-bottom: 0.2rem;
}

.profile .image {
  border-radius: 50%;
  object-fit: cover;
  width: 50px;
  height: 50px;
}

.message {
  border-radius: 5px;
  padding: 0.5rem;
  font-size: 12px;
  margin: 0 5px;
  flex: 7;
}

.time {
  font-size: 10px;
  margin: 0 5px;
}

.sent {
  flex-direction: row-reverse;
  float: right;
}

.sent .message {
  background: rgb(175, 144, 144);
}

.received .message {
  background: #fff;
}
</style>