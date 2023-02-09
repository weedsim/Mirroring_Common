<template>
  <div class="m-0 p-0 footer">
    <div class="m-0 p-0 row" style="height: 100%">
      <div class="m-0 p-0 col-4">
        <button
          class="btncss"
          data-bs-toggle="modal"
          data-bs-target="#createRoomModal"
        >
          방 생성
        </button>
      </div>
      <div>
        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <li class="page-item" :class="{ disabled: currentPage === 1 }">
              <a class="page-link" href="#" @click.prevent="previousPage">Previous</a>
            </li>
            <li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: currentPage === page }">
              <a class="page-link" href="#" @click.prevent="goToPage(page)">{{ page }}</a>
            </li>
            <li class="page-item" :class="{ disabled: currentPage === totalPages }">
              <a class="page-link" href="#" @click.prevent="nextPage">Next</a>
            </li>
          </ul>
        </nav>
      </div>
      <div class="m-0 p-0 col-4">
        <ul class="findroom" style="list-style: none">
          <li style="display: flex; justify-content: space-between">
            <input
              type="text"
              class="form-control"
              placeholder="방번호 입력"
              style="width: 70%"
            />
            <button class="btncss">입장</button>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <CreateRoomModal />
</template>

<script>
import CreateRoomModal from "@/components/lobby/CreateRoomModal.vue";

export default {
  name: "LobbyFooter",
  components: { CreateRoomModal },
  created() {},data() {
    return {
      items: [
        { id: 1, name: 'Item 1' },
        { id: 2, name: 'Item 2' },
        { id: 3, name: 'Item 3' },
        { id: 4, name: 'Item 3' },
        { id: 5, name: 'Item 3' },
        { id: 6, name: 'Item 3' },
        { id: 7, name: 'Item 3' },
        { id: 8, name: 'Item 3' },
        { id: 9, name: 'Item 3' },
        { id: 10, name: 'Item 3' },
        { id: 11, name: 'Item 3' },
        // ... more items
      ],
      itemsPerPage: 2,
      currentPage: 1
    }
  },
  computed: {
    itemsToShow() {
      const start = (this.currentPage - 1) * this.itemsPerPage
      const end = start + this.itemsPerPage
      return this.items.slice(start, end)
    },
    totalPages() {
      return Math.ceil(this.items.length / this.itemsPerPage)
    }
  },
  methods: {
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++
      }
    },
    goToPage(page) {
      this.currentPage = page
    }
  },
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
