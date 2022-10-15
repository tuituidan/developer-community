<template>
    <div class="main-content">
        <div class="content-left">
            <el-tabs v-model="activename" @tab-click="tabChange">
                <el-tab-pane label="最新" name="first"></el-tab-pane>
                <el-tab-pane label="最热" name="second"></el-tab-pane>
                <el-tab-pane label="置顶" name="third"></el-tab-pane>
                <el-tab-pane label="精品" name="forth"></el-tab-pane>
            </el-tabs>
            <div class="content-list">
                <el-empty v-if="!pageData.datas.length"></el-empty>
                <div class="content-item" v-for="item in pageData.datas" :key="item.id">
                    <div class="content-header">
                        <el-avatar :size="20"
                                   :src="item.authorAvatar"></el-avatar>
                        <span class="content-header-name" v-text="item.authorName"></span>
                        <el-divider direction="vertical"></el-divider>
                        <span v-text="$timeUtils.prettytime(item.createTime)"></span>
                        <el-divider direction="vertical"></el-divider>
                        <el-tag effect="plain" v-if="item.top==='1'">顶</el-tag>
                        <el-tag type="danger" effect="plain" v-if="item.valuable==='1'">精</el-tag>
                    </div>
                    <div class="content-body" @click="articleItemClick(item)"
                         @keypress="articleItemClick(item)">
                        <div class="content-body-left">
                            <div class="content-body-title" v-text="item.title"></div>
                            <div class="content-body-article" v-text="item.introduction"></div>
                        </div>
                        <div class="content-body-right" v-if="item.firstImage">
                            <img :src="item.firstImage" :alt="item.firstImage"/>
                        </div>
                    </div>
                    <div class="content-footer">
                        <em class="el-icon-view" title="阅读" v-text="item.visits"></em>
                        <el-divider direction="vertical"></el-divider>
                        <em class="el-icon-thumb" title="点赞" v-text="item.approves"></em>
                        <el-divider direction="vertical"></el-divider>
                        <em class="el-icon-star-off" title="收藏" v-text="item.collects"></em>
                        <el-divider direction="vertical"></el-divider>
                        <em class="el-icon-chat-dot-round" title="评论" v-text="item.comments"></em>
                        <el-divider direction="vertical"></el-divider>
                        <el-tag type="success"
                                class="content-footer-tag"
                                size="mini"
                                effect="plain" v-for="tag in item.tagsTranslateText"
                                :key="tag"
                                v-text="tag">
                        </el-tag>
                    </div>
                </div>
            </div>
            <div class="page-content" v-if="pageData.datas.length">
                <el-pagination
                    :background="true"
                    :page-sizes="[15, 30, 50, 100]"
                    :page-size="15"
                    layout="total, sizes, prev, pager, next, jumper"
                    @size-change="pageSizeChange"
                    @current-change="pageIndexChange"
                    :total="pageData.total">
                </el-pagination>
            </div>
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
