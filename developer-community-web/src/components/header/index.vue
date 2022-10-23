<template>
    <el-container>
        <el-header height="auto">
            <div class="header-first">
                <div class="header-center">
                    <div class="header-left">
                        <div class="header-left-logo">
                            <img src="@/assets/logo.png" alt="logo"/>
                            <a href="/">开发者社区</a>
                        </div>
                        <div class="header-menu">
                            <ul>
                                <router-link tag="li" to="/" exact>文章</router-link>
                                <router-link tag="li" to="/faq" exact>问题</router-link>
                                <router-link tag="li" to="/faq" exact>学堂</router-link>
                                <router-link tag="li" to="/faq" exact>资源</router-link>
                            </ul>
                        </div>
                        <div class="header-search">
                            <el-input placeholder="请输入关键字"
                                      size="small"
                                      suffix-icon="el-icon-search"></el-input>
                        </div>
                    </div>
                    <div class="header-right">
                        <el-button type="primary"
                                   size="small"
                                   @click="$router.push('/article/create')"
                                   icon="el-icon-edit-outline">写文章
                        </el-button>
                        <el-button type="primary"
                                   icon="el-icon-edit-outline"
                                   @click="$router.push('/faq')"
                                   plain
                                   size="small">提问题
                        </el-button>
                        <el-button v-if="false" size="small" class="btn-login">登录</el-button>
                        <el-dropdown class="header-avatar">
                            <el-avatar
                                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item icon="el-icon-coffee-cup">我的主页</el-dropdown-item>
                                <el-dropdown-item icon="el-icon-document">我的文章</el-dropdown-item>
                                <el-dropdown-item icon="el-icon-warning-outline">我的问题</el-dropdown-item>
                                <el-dropdown-item icon="el-icon-star-off">我的收藏</el-dropdown-item>
                                <el-dropdown-item icon="el-icon-switch-button">退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </div>
            </div>
            <el-button v-if="topicShow"
                       :icon="topicOpen?'el-icon-arrow-up':'el-icon-arrow-down'"
                       class="btn-collapse"
                       @click="topicOpen=!topicOpen">
            </el-button>
            <el-collapse-transition v-if="topicShow">
                <div class="transition-box header-second" v-if="topicOpen">
                    <div class="header-topic">
                        <ul>
                            <li v-for="topic in topics" :key="topic.id">
                                <el-button :type="topic.selected?'primary':'text'"
                                           @click="changeTopic(topic)"
                                           v-text="topic.name"></el-button>
                            </li>
                        </ul>
                    </div>
                    <div class="header-tag" v-if="tags.length">
                        <ul>
                            <li v-for="tag in tags" :key="tag.id">
                                <el-button :type="tag.selected?'primary':'default'"
                                           round
                                           :plain="tag.selected"
                                           v-text="tag.name"></el-button>
                            </li>
                        </ul>
                    </div>
                </div>
            </el-collapse-transition>
        </el-header>
        <el-main>
            <router-view @frontHeaderMsg="frontHeaderMsg"/>
            <div class="main-footer">
                developer community is open source and released under the MIT License.<br/>
                Copyright © 2022 tuituidan.
            </div>
            <el-backtop target=".el-main"></el-backtop>
        </el-main>
    </el-container>
</template>

<script src="./index.js"></script>

<style scoped lang="less">
@import "src/components/header/index.less";
</style>
