/*
 * @author tuituidan
 * @date 2022/9/24
 */
import Vue from 'vue';
// 案由树
Vue.component('ttd-card', () => import('@/components/card/index.vue'));
Vue.component('ttd-card-bulletin', () => import('@/components/card/bulletin.vue'));
Vue.component('ttd-card-author-ranking', () => import('@/components/card/author-ranking.vue'));
Vue.component('ttd-friendly-links', () => import('@/components/card/friendly-links.vue'));
Vue.component('person-card', () => import('@/components/card/person-card.vue'));
Vue.component('ttd-uploader', () => import('@/components/uploader/index.vue'));
