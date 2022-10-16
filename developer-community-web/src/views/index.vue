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

<script>
export default {
    name: 'views-index',
    data() {
        return {
            topicShow: true,
            topicOpen: false,
            topics: [],
            tags: []
        };
    },
    mounted() {
        this.requestTags();
    },
    methods: {
        requestTags() {
            this.$http.get('/api/v1/data-dict/type/2100000000/tree')
                .then(res => {
                    if (Array.isArray(res.data)) {
                        res.data.unshift({
                            id: 'all',
                            name: '全部',
                            selected: true,
                            children: []
                        });
                        this.topics = res.data;
                    }
                })
                .catch(err => {
                    window.console.error(err);
                });
        },
        changeTopic(topic) {
            this.topics.forEach(item => {
                this.$set(item, 'selected', false);
            });
            this.$set(topic, 'selected', true);
            this.tags = topic.children;
        },
        frontHeaderMsg(setting) {
            this.topicShow = !setting.hideTopic;
        }
    }
};
</script>

<style scoped lang="less">
.el-container {
    height: 100%;

    .el-header {
        width: 100%;
        position: sticky;
        background-color: white;
        padding: 0;
        top: 0;
        z-index: 9;
        box-shadow: 0 15px 10px 0 rgb(0 0 0 / 5%);

        .header-first {
            border-bottom: 1px solid #f2f6fc;

            .header-center {
                width: 1200px;
                margin: 0 auto;
                display: flex;
                justify-content: space-between;
                line-height: 60px;

                .header-left {
                    width: 880px;
                    height: 60px;
                    display: flex;

                    .header-left-logo {
                        height: 60px;
                        width: 180px;
                        text-align: left;

                        img {
                            width: 40px;
                            height: 40px;
                            vertical-align: middle;
                        }

                        a {
                            margin-left: 10px;
                            font-size: 20px;
                            vertical-align: middle;
                        }
                    }

                    .header-menu {
                        width: 300px;
                        height: 60px;

                        ul {
                            display: flex;

                            li {
                                padding: 0 15px;
                                margin-left: 5px;
                                color: #606a78;
                                cursor: pointer;
                                font-size: 18px;
                            }

                            li.router-link-active {
                                color: #409EFF;
                            }
                        }
                    }

                    .header-search {
                        margin-left: auto;
                    }
                }

                .header-right {
                    height: 60px;
                    width: 305px;
                    display: flex;
                    align-items: center;

                    button {
                        font-size: 14px;
                    }

                    .btn-login {
                        margin-left: auto;
                    }

                    .header-avatar {
                        margin-left: auto;
                        display: flex;
                    }
                }
            }
        }

        .btn-collapse {
            width: 30px;
            height: 15px;
            color: #9a9a9a;
            background-color: white;
            position: absolute;
            left: 50%;
            border: none;
            padding: 0;
            border-bottom-left-radius: 8px;
            border-bottom-right-radius: 8px;
            transform: translate(-50%, -50%);
        }

        .header-second {
            .header-topic {
                width: 1200px;
                margin: 0 auto;
                height: 45px;
                line-height: 45px;

                .el-button--text {
                    color: #606a78;
                }

                ul {
                    display: flex;

                    li {
                        margin-right: 15px;

                        button {
                            padding: 5px 10px;
                            vertical-align: middle;
                            font-size: 15px;
                        }
                    }
                }
            }

            .header-tag {
                width: 1200px;
                margin: 0 auto;
                height: 36px;

                ul {
                    display: flex;

                    li {
                        padding: 0 10px;

                        button {
                            padding: 5px 12px;
                            font-size: 13px;
                        }
                    }
                }
            }
        }
    }

    .el-main {
        /* 左边留17px是因为右边有滚动条占宽度，以便和头部对齐*/
        padding: 15px 0 0 17px;

        .main-footer {
            margin-top: 30px;
            height: 40px;
            color: #9a9a9a;
            line-height: 20px;
        }
    }
}
</style>
