<template>
    <div id="app">
        <view-index></view-index>
    </div>
</template>
<script>
export default {
    name: 'App',
    components: {
        'view-index': () => import('@/views/index.vue')
    }
};
</script>
<style lang="less">
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    height: 100%;
}
.el-notification__content {
    margin: 0 !important;
}
</style>
