<template>
  <div class="m-0 p-0 col-6 roomwrapper">
    <div class="card m-0 p-0" style="width: 95%; height: 95%">
      <div class="row g-0">
        <div class="col-md-2 imgwrapper">
          <img
            src="@/assets/home/mode-one.jpg"
            class="img-fluid rounded-start modeimg"
            alt="..."
          />
        </div>
        <div class="col-md-10">
          <div class="card-body m-0 px-2">
            <!-- <h5 class="card-title">No.12345 &nbsp;&nbsp;&nbsp; 즐겜해요~</h5> -->
            <!-- <p class="card-title">No.12345 즐겜해요~</p> -->
            <ul class="card-title m-0 p-0">
              <li>No.12</li>
              <li class="ellipsis">즐겜해요</li>
            </ul>
            <p class="card-text">4/8</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RoomCard",

  components: {},
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
.roomwrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
.card {
  border: 2px solid #5b3700;
  background-color: #949494;
}
.imgwrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
.modeimg {
  width: 50px;
  height: 50px;
  border-radius: 10px !important;
}
.card-title {
  list-style: none;
  display: flex;
  justify-content: space-between;
}
/* .card-title li {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
} */
.card-title li:nth-child(1) {
  text-align: left;
  margin-right: 20px;
}
.card-title li:nth-child(2) {
  text-align: right;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.card-text {
  text-align: right;
}
</style>
