<template>
    <div class="person-card">
        <div class="person-header">
            <el-avatar
                shape="square"
                :size="55"
                class="person-header-avatar"
                src="https://wx.qlogo.cn/mmhead/BO1qQiajiacVkg0BGLGFsdcTMvLpRKZYPhgSkIzxCwf4qoggibcWM3NyA/0">
                <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png" alt="1"/>
            </el-avatar>
            <div class="userinfo">
                <div class="user-nick" v-text="userName"></div>
                <div class="user-intro" :title="introduction" v-text="introduction"></div>
            </div>
        </div>
        <div class="person-body">
            <a href="#">
                <p>发布文章</p>
                <p class="num">15</p>
            </a>
            <a href="#">
                <p>评论数</p>
                <p class="num">150</p>
            </a>
            <a href="#">
                <p>收藏数</p>
                <p class="num">25</p>
            </a>
        </div>
    </div>
</template>

<script>
export default {
    name: 'person-card',
    data() {
        return {
            userName: '推推蛋',
            introduction: '暂无个人介绍暂无个人介绍暂无个人介绍暂无个人介绍暂无个人介绍暂无个人介绍暂无个人介绍'
        };
    }
};
</script>

<style scoped lang="less">
.person-card {
    background-color: white;
    margin-bottom: 15px;

    .person-header {
        display: flex;
        padding: 20px;

        .userinfo {
            width: 200px;
            text-align: left;
            margin-left: 10px;

            .user-nick {
                font-size: 14px;
                line-height: 22px;
            }

            .user-intro {
                font-size: 13px;
                line-height: 18px;
                color: #606a78;
                word-break: break-all;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 2;
                overflow: hidden;
            }
        }
    }

    .person-body {
        border-top: 1px solid #EBEEF5;
        display: flex;
        justify-content: space-around;

        a {
            p {
                font-size: 14px;
            }

            .num {
                color: #2c3e50;
                font-size: 18px;
            }
        }
    }
}
</style>
