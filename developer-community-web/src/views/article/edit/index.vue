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
                         :rules="rules"
                         :model="editItem"
                         ref="editForm"
                         @submit.native.prevent>
                    <el-form-item label="标签" prop="tags">
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
                        <ttd-uploader v-model="editItem.firstImage"
                                      :limit="1"
                                      :initList="editItem.firstImage"
                                      tip="只能上传图片，支持复制/截图粘贴上传，不上传将尝试获取文章正文中第一张大小合适图片"
                                      accept=".png,.jpg,.jpeg,.gif,.bmp"
                                      list-type="picture-card"></ttd-uploader>
                    </el-form-item>
                    <el-form-item label="附件">
                        <ttd-uploader v-model="editItem.filesIds"
                                      :initList="fileList"
                                      tip="只能上传常见图片、压缩包和文档等格式，且单个文件不能超过50MB，支持复制粘贴上传"
                                      :data="{type: 'attach-file'}"></ttd-uploader>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="saveArticle()">保存</el-button>
                        <el-button type="primary" @click="saveArticle('1')">确认发布</el-button>
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
.fd-cascader-popper {
    .el-cascader-panel {
        .el-cascader-menu:first-child {
            .el-checkbox {
                display: none;
            }

            .el-cascader-node__label {
                padding: 0;
            }
        }
    }
}
</style>
