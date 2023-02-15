<template>
  <div class="layout">
    <div id="pagination" class="layout">
      <nav>
        <button @click="prevPage" :disabled="down" class="btn-page">
          &#60;
        </button>
        <span v-for="page in pages" :key="page">
          <router-link
            v-if="page === this.current"
            class="now page"
            :to="{ name: 'lobby', query: { page: page } }"
          >
            {{ page }}
          </router-link>
          <router-link
            v-else
            class="page"
            :to="{ name: 'lobby', query: { page: page } }"
          >
            {{ page }}
          </router-link>
          <!-- <a href="/lobby?page={{ page }}">{{ page }}</a> -->
        </span>
        <button @click="nextPage" :disabled="up" class="btn-page">&#62;</button>
      </nav>
    </div>
    <div class="layout">
      <ul>
        <button class="card btn-create" v-on:click="onRoom">방 생성</button>
      </ul>
    </div>
  </div>
  <CreateRoomModal v-if="showRoom" v-on:close="offRoom" />
</template>

<script>
import CreateRoomModal from "@/components/lobby/CreateRoomModal.vue";
import axios from "axios";
import VueCookies from "vue-cookies";

import { mapState } from "vuex";
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
    ...mapState["API_URL"],
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
        url: `${this.API_URL}/rooms/last`,
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

<style scoped>
template > div {
  display: inline;
}

input {
  width: 70%;
}
.now {
  color: rgb(255, 193, 158);
  font-size: 28px;
}
.page {
  color: white;
  font-size: 20px;
}
#pagination {
  display: flex;
  justify-content: center;
  text-align: center;
}
.card {
  border: 2px solid #5b3700;
  background-color: #949494;
  font-size: 18px;
  width: 80px;
  height: 40px;
}
.btn-create {
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.btn-page {
  color: white;
  background-color: transparent;
  border: none;
  font-size: 25px;
  margin: 10px;
}
</style>
