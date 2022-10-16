import Vue from 'vue';
import ElementUI from 'element-ui';
import mavonEditor from 'mavon-editor';
import App from './App.vue';
import 'element-ui/lib/theme-chalk/index.css';
import 'mavon-editor/dist/css/index.css';
import '@/css/index.less';
import '@/js/common/native-ext';
import '@/components';
import '@/plugin';
import router from './router';

Vue.use(ElementUI);
Vue.use(mavonEditor);

Vue.config.productionTip = false;

new Vue({
    router,
    render: (h) => h(App)
}).$mount('#app');
