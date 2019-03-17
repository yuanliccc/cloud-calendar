<template>
  <div class="publicFrom">
    <div class="publicTitle">查看${vueModuleChineseName}</div>
    <div class="public_Display">
      <el-form label-position="left" ref="${vueModuleNameLowerCamel}"class="add_Edit" :model="${vueModuleNameLowerCamel}":inline="true"
            label-width="120px">
        <#list disFields as key>
        <el-form-item label="${key.chineseName}">
          <el-input v-model="${vueModuleNameLowerCamel}.${key.field}" disabled="disabled"></el-input>
        </el-form-item>
        </#list>
        <div class="form_Bt">
          <el-form-item>
            <el-button type="primary" @click="reBack">确定</el-button>
            <el-button @click="reBack">取消</el-button>
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script>
export default{
  name: '${vueModuleNameLowerCamel}Display',
  data(){
    return {
     ${vueModuleNameLowerCamel}: {},
    }
  },
  mounted: function(){
    this.getInfoById(this.$route.params.${vueModuleNameLowerCamel}Id);
  },
  methods:{
    reBack: function(){
      this.$router.go(-1);
    },
    getInfoById: function(${vueModuleNameLowerCamel}Id){

      this.$store.commit("showLoading");
      this.$axios.get('/occ/${vueModuleNameLowerCamel}/detail',{
        params:{id: ${vueModuleNameLowerCamel}Id}
      }).then(res =>{
        const data = res.data;
        this.${vueModuleNameLowerCamel} = data.data;
        this.$store.commit("hideLoading");

      }).catch(err => {
        this.$store.commit("hideLoading");
        this.$message({
          showClose: true,
          message: err,
          type: 'error'
        });
      })
    },
  }
}
</script>

<style scoped>

</style>
