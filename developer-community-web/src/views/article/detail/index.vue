<template>
    <div class="main-content">
        <div class="content-operation">
            <el-badge :value="200" :max="99">
                <el-button icon="el-icon-thumb" circle></el-button>
            </el-badge>
            <el-badge :value="99" :max="99">
                <el-button icon="el-icon-star-off" circle></el-button>
            </el-badge>
            <el-badge :value="0" :hidden="true" :max="99">
                <el-button icon="el-icon-chat-dot-round" circle></el-button>
            </el-badge>
        </div>
        <div class="content-left">
            <div class="article-title" v-text="item.title"></div>
            <div class="article-header">
                <el-avatar :size="20"
                           :src="item.authorAvatar"></el-avatar>
                <span class="content-header-name" v-text="item.authorName"></span>
                <el-divider direction="vertical"></el-divider>
                <span v-text="$timeUtils.prettytime(item.createTime)"></span>
                <el-divider direction="vertical"></el-divider>
                <el-tag effect="plain" v-if="item.top==='1'">顶</el-tag>
                <el-tag type="danger" effect="plain" v-if="item.best==='1'">精</el-tag>
                <el-divider direction="vertical"></el-divider>
                <el-tag type="success"
                        class="content-footer-tag"
                        size="mini"
                        effect="plain" v-for="tag in item.tagsTranslateText"
                        :key="tag"
                        v-text="tag">
                </el-tag>
                <div class="article-header-right">
                    <el-popconfirm
                        title="文章删除后不可恢复，确定删除此文章？"
                        @confirm="deleteHandler">
                        <el-link slot="reference" icon="el-icon-close">删除</el-link>
                    </el-popconfirm>
                    <el-link icon="el-icon-edit" @click="editHandler">编辑</el-link>
                </div>
            </div>
            <div class="markdown-body" v-html="item.contentHtml"></div>
        </div>
        <div class="content-right">
            <person-card></person-card>
            <ttd-card-bulletin></ttd-card-bulletin>
            <ttd-card-author-ranking></ttd-card-author-ranking>
            <ttd-friendly-links></ttd-friendly-links>
        </div>
    </div>
</template>

<script src="./index.js"></script>
<style scoped lang="less">
@import "index.less";
</style>
