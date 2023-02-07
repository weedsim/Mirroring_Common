<template>
  <div class="m-0 p-0 footer">
    <div class="m-0 p-0 row" style="height: 100%">
      <div class="m-0 p-0 col-4">
        <!-- <button
          class="btncss"
          data-bs-toggle="modal"
          data-bs-target="#createRoomModal"
        >
          방 생성
        </button> -->
      </div>
      <div class="m-0 p-0 col-4 navwrapper">
        <footer-pagination></footer-pagination>
        <!-- <nav aria-label="Page navigation example">
          <ul class="pagination">
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav> -->
      </div>
      <div class="m-0 p-0 col-4">
        <button>글쓰기</button>
        <!-- <ul class="findroom" style="list-style: none">
          <li style="display: flex; justify-content: space-between">
            <input
              type="text"
              class="form-control"
              placeholder="방번호 입력"
              style="width: 70%"
            />
            <button class="btncss">입장</button>
          </li>
        </ul> -->
      </div>
    </div>
  </div>
  <CreateRoomModal />
</template>

<script>
import CreateRoomModal from "@/components/lobby/CreateRoomModal.vue";
import FooterPagination from "@/components/FooterPagination.vue";

export default {
  name: "CommunityFooter",
  components: { CreateRoomModal, FooterPagination }, 
  created() {},
};
</script>

<style>
.btncss {
  background-color: #b4b4b4;
  border-color: #5b3700;
  border-radius: 4px;
}

.btncss:hover {
  color: white;
}
.findroom {
  display: table;
  margin-right: auto;
  margin-left: auto;
  width: 80%;
  padding: 0px;
}
.navwrapper {
  display: flex;
  justify-content: center;
}
/* .page-link {
  color: #000;
  background-color: #fff;
  border: 1px solid #ccc;
}

.page-item.active .page-link {
  z-index: 1;
  color: #555;
  font-weight: bold;
  background-color: #f1f1f1;
  border-color: #ccc;
}

.page-link:focus,
.page-link:hover {
  color: #000;
  background-color: #fafafa;
  border-color: #ccc;
} */
</style>
