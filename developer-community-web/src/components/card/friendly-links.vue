<template>
    <ttd-card title="友情链接">
        <div class="link-content">
            <el-link :underline="false"
                     v-for="item in datas" :key="item.title"
                     :href="item.path"
                     target="_blank"
                     v-text="item.title">
            </el-link>
        </div>
    </ttd-card>
</template>

<script>
export default {
    name: 'friendly-links',
    data() {
        return {
            datas: [
                {
                    title: 'ElementUI',
                    path: 'https://element.eleme.cn/'
                },
                {
                    title: '百度一下',
                    path: 'https://www.baidu.com'
                },
                {
                    title: 'google',
                    path: 'https://google.com'
                },
                {
                    title: 'gitee',
                    path: 'https://gitee.com'
                },
                {
                    title: '开源中国社区',
                    path: 'https://www.oschina.net/'
                }
            ]
        };
    }
};
</script>

<style scoped lang="less">
.link-content {
    display: flex;
    flex-wrap: wrap;

    .el-link {
        margin-right: 10px;
        line-height: 30px;
        font-size: 14px;
    }
}
</style>
