<template>
  <navbar />
  <div class="m-0 p-0">
    <div class="m-0 p-0 row">
      <div class="m-0 p-0 col-2">
        <h1>dddd</h1>
      </div>
      <div class="m-0 p-0 col-8">
        <!-- 메인커뮤니티 -->
        <div style="width: 100%; height: 95vh">
          <!-- 헤드 -->
          <div class="m-0 p-0 row" style="height: 20%">
            <div class="m-0 p-0 row" style="height: 60%">11</div>
            <div class="m-0 p-0 row" style="height: 40%">
              <div class="input-group">
                <div class="form-outline">
                  <input type="search" id="form1" class="form-control" />
                  <label class="form-label" for="form1">Search</label>
                </div>
                <button type="button" class="btn btn-primary">
                  <i class="fas fa-search"></i>
                </button>
              </div>
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
      <div class="m-0 p-0 col-2">

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
