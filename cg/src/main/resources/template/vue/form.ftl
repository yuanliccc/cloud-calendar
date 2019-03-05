<template>
  <div class="publicFrom">
    <div class="publicTitle" v-if="!add">${vueModuleChineseName}编辑</div>
    <div class="publicTitle" v-if="add">新增${vueModuleChineseName}</div>
    <div>
      <el-form label-position="left" ref="${vueModuleNameLowerCamel}"class="add_Edit" :model="${vueModuleNameLowerCamel}" :rules="rules" :inline="true"
                label-width="120px">
        <#list disFields as key>
        <el-form-item label="${key.chineseName}" prop="${key.field}">
          <el-input v-model="${vueModuleNameLowerCamel}.${key.field}" placeholder="${key.chineseName}，一旦新建便无法修改"></el-input>
        </el-form-item>
        </#list>
        <div class="form_Bt">
          <el-form-item>
            <el-button v-if="add" type="primary" @click="submit">立即创建</el-button>
            <el-button v-if="add" type="primary" @click="clear">重置</el-button>
            <el-button v-if="!add" type="primary" @click="submit">立即修改</el-button>
            <el-button @click="reBack">取消</el-button>
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script>
  export default{
    name: '${vueModuleNameLowerCamel}Form',
    data(){
      return {
        add: true,
        ${vueModuleNameLowerCamel}: {
        <#list fields as key>
          ${key}: '',
        </#list>
        },
        rules:{
        <#list disFields as key>
          ${key.field}: [
            { required: true, message: '请输入${key.chineseName}，一旦新建便无法修改', trigger: 'blur' }
          ],
        </#list>
        }
      }
    },
    mounted: function(){
      this.add = (this.$route.name == '${vueModuleNameLowerCamel}Form_Add' ? true : false);
      if(!this.add){
        this.getInfoById(this.$route.params.${vueModuleNameLowerCamel}Id);
       }
    },
    methods:{
      clear:function(){
        this.${vueModuleNameLowerCamel}= {
        <#list fields as key>
          ${key}: '',
        </#list>
        }
      },
      submit: function(){
        this.$refs.${vueModuleNameLowerCamel}.validate((valid) => {
          if (valid) {
            if(this.add) {
              this.save();
            }else{
              this.edit();
            }
          } else {
            return false;
          }
        });
      },
      save: function(){
        this.$store.commit("showLoading");
        this.$axios.post('/occ/${vueModuleNameLowerCamel}/add', this.${vueModuleNameLowerCamel})
        .then(res =>{
          const data = res.data;
          if(data.code == '400'){
            this.$store.commit("hideLoading");
            this.$message({
              showClose: true,
              message: data.message,
              type: 'warning'
            });
          } else {
            this.$message({
              type: 'success',
              message: '新增成功!'
            });
            this.$store.commit("hideLoading");
            this.clear();
          }
        }).catch(err =>{
          this.$store.commit("hideLoading");
          this.$message({
            showClose: true,
            message: err,
            type: 'error'
          });
        });
      },
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
      edit: function(){
        this.$store.commit("showLoading");
        this.$axios.put('/occ/${vueModuleNameLowerCamel}/update', this.${vueModuleNameLowerCamel})
        .then(res =>{
          const data = res.data;
          if(data.code == '400'){
            this.$store.commit("hideLoading");
            this.$message({
              showClose: true,
              message: data.message,
              type: 'warning'
            });
          } else {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            this.$store.commit("hideLoading");
          }
        }).catch(err =>{
          this.$store.commit("hideLoading");
          this.$message({
            showClose: true,
            message: err,
            type: 'error'
          });
        });
      },
    }
  }
</script>

<style scoped>

</style>
