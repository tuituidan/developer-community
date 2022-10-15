<template>
  <div class="main-content">
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
            <el-tag type="danger" effect="plain" v-if="item.valuable==='1'">精</el-tag>
            <el-divider direction="vertical"></el-divider>
            <el-tag type="success"
                    class="content-footer-tag"
                    size="mini"
                    effect="plain" v-for="tag in item.tagsTranslateText"
                    :key="tag"
                    v-text="tag">
            </el-tag>
            <div class="article-header-right">
                <el-link v-if="item.valuable==='1'" @click="setTopOrValuable('valuable', '2')">取消精品</el-link>
                <el-link v-else @click="setTopOrValuable('valuable', '1')">设为精品</el-link>
                <el-link v-if="item.top==='1'" @click="setTopOrValuable('top', '2')">取消置顶</el-link>
                <el-link v-else @click="setTopOrValuable('top', '1')">置顶</el-link>
                <el-link @click="editHandler">编辑</el-link>
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
