<!-- <template>
    <BIconMicFill style="color:blue" v-if="$store.state.isShow_mic" @click="togglem()"></BIconMicFill>
    <BIconMicFill style="color:white" v-else></BIconMicFill>
    <BIconHeadset style="color:blue" v-if="$store.state.isShow_headset" @click="toggleh()"></BIconHeadset>
    <BIconHeadset style="color:white" v-else></BIconHeadset>
</template>

<script>
export default {
    data () {
    return {
      isShow_mic: false,
      isShow_headset: false
    }
  },

  name: "GameConfirm",
  created() {
    // this.toggle = store.commit("isShow", store.state.isShow = !store.state.isShow)
  },
  methods:{
    togglem(){
      this.$store.state.isShow_mic = !this.$store.state.isShow_mic
     },
     toggleh(){
      this.$store.state.isShow_headset = !this.$store.state.isShow_headset
     }
    }
  }

</script>

<style>

</style> -->