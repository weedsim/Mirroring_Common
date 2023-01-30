<template>
  <navbar />
  <div class="m-0 p-0">
    <div class="m-0 p-0 row">
      <div class="m-0 p-0 col-3"></div>
      <div class="m-0 p-0 col-6">
        <!-- 메인커뮤니티 -->
        <div style="width: 100%; height: 95vh">
          <!-- 헤드 -->
          <div class="m-0 p-0 row" style="height: 20%">
            <div class="m-0 p-0 row" style="height: 60%">11</div>
            <div class="m-0 p-0 row" style="height: 40%">
              <h1>커뮤니티</h1>

              <div class="input-group">
                <select
                  class="form-select"
                  id="floatingSelect"
                  aria-label="Floating label select example"
                >
                  <option value="1" selected>제목</option>
                  <option value="2">작성자</option>
                </select>
                <div class="form-floating">
                  <input
                    type="text"
                    class="form-control"
                    id="floatingInputGroup1"
                  />
                </div>
                <span class="input-group-text">@</span>
              </div>
            </div>
            <hr />
          </div>
          <!-- 게시글 -->
          <div class="m-0 p-0 row" style="height: 60%"></div>
          <!-- 푸터 -->
          <div class="m-0 p-0 row" style="height: 20%"></div>
        </div>
      </div>
      <div class="m-0 p-0 col-3"></div>
    </div>
  </div>
</template>

<script>
import navbar from "@/components/NavView.vue";

export default {
  name: "Community",
  components: { navbar },
  data() {
    return {};
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {},
};
</script>

<style scoped></style>
