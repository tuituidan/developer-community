/*
 * @author tuituidan
 * @date 2022/9/25
 */
const routerConfig = [
    {
        path: '/',
        name: 'article-list',
        component: () => import('@/views/article/list/index.vue')
    },
    {
        path: '/article/create',
        name: 'article-create',
        component: () => import('@/views/article/edit/index.vue')
    },
    {
        path: '/article/edit/:id',
        name: 'article-edit',
        component: () => import('@/views/article/edit/index.vue')
    },
    {
        path: '/article/detail/:id',
        name: 'article-detail',
        component: () => import('@/views/article/detail/index.vue')
    },
    {
        path: '/user/:id',
        name: 'user-index',
        component: () => import('@/views/user/index.vue')
    },
    {
        path: '/faq',
        name: 'faq',
        component: () => import('@/views/faq/index.vue')
    }
];

export default routerConfig;
