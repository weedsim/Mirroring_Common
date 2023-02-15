<template>
  <div class="main-community">
    <div class="community-container">
      <div class="header">
        <div class="header-sub">
          <div style="color: #cfcfce; font-size: 40px">커뮤니티</div>
          <div class="search-bar">
            <div>
              <select class="select-search">
                <option>제목</option>
              </select>
            </div>
            <div style="display: flex">
              <input class="input-search" />
              <div class="btn-search"></div>
            </div>
          </div>
        </div>
        <hr style="height: 4px; border: none; background-color: white" />
      </div>
      <div class="community-body">
        <table>
          <!-- v-for문 걸어야함 -->
          <tr class="notice">
            <td style="width: 64%" class="first-left-td first-td">
              <div class="notice-td">
                <div class="notice-circle"><span>공지</span></div>
                알려진 이슈를 알려 드립니다.
              </div>
            </td>
            <td style="width: 12%"></td>
            <td style="width: 12%"></td>
            <td style="width: 12%" class="first-right-td">2023.01.12</td>
          </tr>
          <tr class="article">
            <td style="width: 64%" class="first-td-article">
              운영자님 부탁드립니다 <span style="color: #700073">(2)</span>
            </td>
            <td style="width: 12%">호에에에엥</td>
            <td style="width: 12%">
              <div class="notice-td center">
                <img
                  src="@/assets/community/viewcnt.png"
                  style="width: 24px; height: 15px; margin-right: 10px"
                />
                9
              </div>
            </td>
            <td style="width: 12%">2023.01.06</td>
          </tr>
          <tr></tr>
          <tr></tr>
          <tr></tr>
          <tr></tr>
          <tr></tr>
          <tr></tr>
          <tr></tr>
          <tr></tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "community",
  data() {
    return {};
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {},
};
</script>

<style scoped>
.community-container {
  width: 1000px;
}
.main-community {
  background-image: url(../../assets/lobby/lobby.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  height: 92vh;
  display: flex;
  justify-content: center;
}
.header {
  height: 16vh;
  padding-bottom: 2vh;
}
.header-sub {
  height: 14vh;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  margin-bottom: 2vh;
}
.select-search {
  height: 40px;
  width: 80px;
  font-size: 18px;
  background-color: #e7e7e7;
  border: #747474 1px solid;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  padding-left: 5px;
}
.search-bar {
  display: flex;
}
.input-search {
  height: 40px;
  width: 200px;
  background-color: #e7e7e7;
  border: #747474 1px solid;
  border-right: none;
}
.btn-search {
  background-color: #e7e7e7;
  border: #747474 1px solid;
  border-left: none;
  width: 30px;
  height: 40px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}
.community-body {
  height: 65vh;
  padding-top: 3vh;
  display: flex;
}
.notice {
  background-color: #dbdbdbcc;
}
.article {
  background-color: rgba(255, 255, 255, 80%);
}
.notice-td {
  display: flex;
  text-align: center;
  height: 100%;
  align-items: center;
}
.first-right-td {
  border-top-right-radius: 10px;
}
.first-left-td {
  border-top-left-radius: 10px;
}
.first-td {
  padding-left: 20px;
}
.first-td-article {
  padding-left: 40px;
}
.notice-circle {
  width: 70px;
  height: 30px;
  background-color: #fdfdfd;
  border-radius: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 15px;
}
tr {
  width: 100%;
  height: 10%;
  border: 1px #646464 solid;
}
table {
  border-collapse: separate;
  border-spacing: 0px;
  width: 100%;
  font-size: 15px;
  font-weight: 500;
}
tr > td:nth-child(n + 2) {
  text-align: center;
}
.center {
  justify-content: center;
}
</style>
