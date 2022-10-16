const {defineConfig} = require('@vue/cli-service');

module.exports = defineConfig({
    transpileDependencies: true,
    productionSourceMap: process.env.NODE_ENV === 'production',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8081',
                changeOrigin: true
            },
            '/dcstore': {
                target: 'http://localhost:9000',
                changeOrigin: true
            }
        }
    }
});
