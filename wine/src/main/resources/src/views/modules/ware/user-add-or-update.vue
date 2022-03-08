<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="openId">
      <el-input v-model="dataForm.openId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userNikename">
      <el-input v-model="dataForm.userNikename" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userProtrait">
      <el-input v-model="dataForm.userProtrait" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userSex">
      <el-input v-model="dataForm.userSex" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userName">
      <el-input v-model="dataForm.userName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userPhone">
      <el-input v-model="dataForm.userPhone" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userAddress">
      <el-input v-model="dataForm.userAddress" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="lastIp">
      <el-input v-model="dataForm.lastIp" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="status">
      <el-input v-model="dataForm.status" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          openId: '',
          userNikename: '',
          userProtrait: '',
          userSex: '',
          userName: '',
          userPhone: '',
          userAddress: '',
          lastIp: '',
          status: '',
          updateTime: '',
          createTime: ''
        },
        dataRule: {
          openId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userNikename: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userProtrait: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userSex: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userPhone: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userAddress: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          lastIp: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/ware/user/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.openId = data.user.openId
                this.dataForm.userNikename = data.user.userNikename
                this.dataForm.userProtrait = data.user.userProtrait
                this.dataForm.userSex = data.user.userSex
                this.dataForm.userName = data.user.userName
                this.dataForm.userPhone = data.user.userPhone
                this.dataForm.userAddress = data.user.userAddress
                this.dataForm.lastIp = data.user.lastIp
                this.dataForm.status = data.user.status
                this.dataForm.updateTime = data.user.updateTime
                this.dataForm.createTime = data.user.createTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/ware/user/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'openId': this.dataForm.openId,
                'userNikename': this.dataForm.userNikename,
                'userProtrait': this.dataForm.userProtrait,
                'userSex': this.dataForm.userSex,
                'userName': this.dataForm.userName,
                'userPhone': this.dataForm.userPhone,
                'userAddress': this.dataForm.userAddress,
                'lastIp': this.dataForm.lastIp,
                'status': this.dataForm.status,
                'updateTime': this.dataForm.updateTime,
                'createTime': this.dataForm.createTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
