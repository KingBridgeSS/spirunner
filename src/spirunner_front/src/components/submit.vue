<template>
  <div id="submit">
    <h2>Input python3 code:</h2>
    <el-input
        type="textarea"
        :autosize="{ minRows: 10, maxRows: 20}"
        placeholder=""
        v-model="code">
    </el-input>
    <h2>Input requirements.txt content:</h2>
    <el-input
        type="textarea"
        :autosize="{ minRows: 10, maxRows: 20}"
        placeholder="If it's not necessary for you, feel free to skip it."
        v-model="requirements">
    </el-input>
    <br>    <br>

    <el-button @click="submit" :disabled="disabled">submit</el-button>
    <h2>Results:</h2>
    <el-input
        type="textarea"
        :autosize="{ minRows: 10, maxRows: 20}"
        placeholder=""
        v-model="results">
    </el-input>
    <h2>System logs:</h2>
    <el-input
        type="textarea"
        :autosize="{ minRows: 10, maxRows: 20}"
        placeholder=""
        v-model="logs">
    </el-input>
  </div>
</template>

<script>
import hr from "../HttpRequest"
export default {
  name: "submit",
  data(){
    return {
      code:'',
      requirements:'',
      disabled:false,
      results:'',
      logs:''
    }
  },
  methods:{
    submit(){
      let vm=this
      vm.disabled=true
      hr.post('/submit',{"code":vm.code,"requirements":vm.requirements}).then(function(res){
        vm.results=res.data.dockerLogs
        vm.logs=res.data.systemLogs
        vm.disabled=false
      })
    }
  }
}
</script>

<style scoped>

</style>