<template>
    <el-upload
        action="/api/v1/file/actions/upload"
        multiple
        with-credentials
        ref="uploader"
        :list-type="listType"
        :limit="limit"
        :data="data"
        :class="{'hide-upload-btn': hideUpload}"
        :on-change="onChange"
        :on-remove="onChange"
        :on-success="onSuccess"
        :before-upload="beforeUpload"
        :on-exceed="onExceed"
        @paste.native="handlePaste"
        :accept="accept"
        :file-list="fileList">
        <el-button v-if="listType==='text'" slot="trigger" size="small" type="primary">点击选择附件</el-button>
        <i v-else class="el-icon-plus"></i>
        <el-alert
            v-if="tip"
            class="uploader-tip"
            slot="tip"
            :closable="false"
            :description="tip">
        </el-alert>
    </el-upload>
</template>

<script src="./index.js"></script>

<style scoped lang="less">

.hide-upload-btn {
    :deep(.el-upload--picture-card) {
        display: none;
    }
}

.uploader-tip {
    margin-top: 15px;
    line-height: 18px;
    padding: 3px 2px;
}
</style>
