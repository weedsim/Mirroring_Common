<template>
  <div class="layout">
    <div id="pagination" class="layout">
      <nav>
        <button @click="prevPage" :disabled="down">prev</button>
        <span v-for="page in pages" :key="page">
          <router-link :to="{ name: 'lobby', query: { page: page } }">
            {{ page }}
          </router-link>
          <!-- <a href="/lobby?page={{ page }}">{{ page }}</a> -->
        </span>
        <button @click="nextPage" :disabled="up">next</button>
      </nav>
    </div>
    <div class="layout">
      <ul>
        <button v-on:click="onRoom">방생성</button>
      </ul>
    </div>
  </div>
  <CreateRoomModal v-if="showRoom" v-on:close="offRoom" />
</template>

<script>
import CreateRoomModal from "@/components/lobby/CreateRoomModal.vue";
import axios from "axios";
import VueCookies from "vue-cookies";

export default {
  name: "LobbyFooter",
  components: { CreateRoomModal },
  data() {
    return {
      pageSize: 5,
      currentPage: this.$route.query.page,
      totalPages: 0,
      paginationMinNum: 1,
      // pages: [],
      showRoom: false,
    };
  },
  created() {
    this.init();
  },
  computed: {
    pages() {
      const start = (this.paginationMinNum - 1) * this.pageSize + 1;
      const end = start + this.pageSize - 1;
      const pages = [];

      for (let i = start; i <= end && i <= this.totalPages; i++) {
        pages.push(i);
      }

      return pages;
    },
    down() {
      if (this.paginationMinNum == 1) {
        return true;
      } else {
        return false;
      }
    },
    up() {
      if (
        this.paginationMinNum == parseInt(this.totalPages / this.pageSize + 1)
      ) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    init() {
      axios({
        method: "GET",
        url: `${this.$store.state.API_URL}/rooms/last`,
        headers: {
          Authorization: `Bearer ${VueCookies.get("accessToken")}`,
        },
      })
        .then((res) => {
          this.totalPages = Number(res.data);
          // const start = (this.currentPage - 1) * this.pageSize + 1;
          // const end = start + this.pageSize - 1;
          // const pages = [];

          // for (let i = start; i <= end && i <= this.totalPages; i++) {
          //   pages.push(i);
          // }

          // this.pages = pages;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    prevPage() {
      if (this.paginationMinNum > 1) {
        this.paginationMinNum--;
      }
    },
    nextPage() {
      if (this.paginationMinNum < this.totalPages) {
        this.paginationMinNum++;
      }
    },
    onRoom() {
      this.showRoom = true;
    },
    offRoom() {
      this.showRoom = false;
    },
    chgNowPage() {
      this.$store.commit("SET_PAGENUM");
    },
  },
};
</script>

<style>
.layout {
  border: 1px solid red;
}

template > div {
  display: inline;
}

input {
  width: 70%;
}
#pagination {
  display: flex;
  justify-content: center;
  text-align: center;
}

span {
  background-color: green;
  margin-left: 2px;
  margin-right: 2px;
}
</style>
