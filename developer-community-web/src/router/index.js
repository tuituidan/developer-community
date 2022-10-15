import routerConfig from '@/router/router-config';

import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);
// 解决导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push;

VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err);
};
const routes = [];
routes.push(...routerConfig);
const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
});
export default router;
