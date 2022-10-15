<template>
    <el-card shadow="never">
        <div slot="header">
            <span v-text="title"></span>
        </div>
        <slot></slot>
    </el-card>
</template>

<script>
export default {
    name: 'card-index',
    props: {
        title: {
            type: String,
            required: true
        }
    }
};
</script>

<style scoped lang="less">
.el-card {
    border: none;
    margin-bottom: 15px;

    :deep(.el-card__header) {
        padding: 10px 15px;
        font-size: 16px;
        font-weight: bolder;
        text-align: left;
    }

    :deep(.el-card__body) {
        padding: 15px;
    }
}
</style>
