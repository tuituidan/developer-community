<template>
    <ttd-card title="作者榜">
        <a v-for="item in datas" :key="item.title"
           :href="item.path"
           target="_blank"
           class="bulletin">
            <span class="title" :class="{'hot': item.hot}" :title="item.title" v-text="item.title"></span>
            <span v-text="item.date"></span>
        </a>
    </ttd-card>
</template>

<script>
export default {
    name: 'ttd-card-bulletin',
    data() {
        return {
            datas: [
                {
                    title: '百度一些',
                    path: 'https://www.baidu.com',
                    date: '09-25'
                },
                {
                    title: '科学提问题',
                    path: '/faq',
                    date: '08-22',
                    hot: true
                },
                {
                    title: '写文章写文章写文章写文章写文章写文章写文章',
                    path: '/article/edit',
                    date: '2021-01-28'
                }
            ]
        };
    }
};
</script>

<style scoped lang="less">
.bulletin {
    font-size: 14px;
    line-height: 25px;
    display: flex;
    height: 25px;
    align-items: center;
    justify-content: space-between;

    &:hover {
        color: #409EFF;
    }

    .title {
        display: inline-block;
        text-align: left;
        max-width: 190px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .hot{
        color: red;
    }
}
</style>
