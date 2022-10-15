<template>
    <div style="text-align: left;background-color: white;">
        <div style="display: flex;padding: 20px;">
            <div style="flex: 1">
                <el-input placeholder="请输入文章标题"
                          v-model="editItem.title"></el-input>
            </div>
            <div style="width: 100px;text-align: right">
                <el-button type="primary" @click="openDrawer">保存</el-button>
            </div>
        </div>
        <div style="padding: 0 20px;">
            <mavon-editor ref="refMd"
                          :external-link="false"
                          v-model="editItem.contentMarkdown"></mavon-editor>
        </div>
        <el-drawer
            title="发布文章"
            :visible.sync="drawerOpen"
            :size="460"
            direction="rtl">
            <div>
                <el-form size="medium"
                         label-width="60px"
                         style="background-color: white;padding: 0 20px;"
                         @submit.native.prevent>
                    <el-form-item label="标签" required>
                        <el-cascader :options="cascader.options"
                                     v-model="editItem.tags"
                                     placeholder="请选择文章标签"
                                     :show-all-levels="false"
                                     style="width: 100%"
                                     filterable
                                     clearable
                                     popper-class="fd-cascader-popper"
                                     :props="cascader.props"></el-cascader>
                    </el-form-item>
                    <el-form-item label="封面">
                        <el-upload
                            class="avatar-uploader"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            :on-change="handlePicturePreview"
                            :auto-upload="false"
                            :show-file-list="false">
                            <img v-if="editItem.firstImage"
                                 :src="editItem.firstImage"
                                 class="avatar" alt="1"/>
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                        <el-alert
                            class="first-image-tip"
                            :closable="false"
                            description="不上传将选择内容中第一张大小合适图片">
                        </el-alert>
                    </el-form-item>
                    <el-form-item label="附件">
                        <el-upload
                            class="upload-demo"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            multiple
                            :limit="3"
                            :file-list="fileList">
                            <el-button size="small" type="primary">点击上传附件</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                        </el-upload>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="saveArticle">保存</el-button>
                        <el-button type="primary" @click="saveArticle">确认发布</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-drawer>
    </div>
</template>

<script src="./index.js"></script>

<style scoped lang="less">
@import "index.less";
</style>
<style lang="less">
.fd-cascader-popper{
    .el-cascader-panel {
        .el-cascader-menu:first-child{
            .el-checkbox{
                display: none;
            }
            .el-cascader-node__label{
                padding: 0;
            }
        }
    }
}
</style>
