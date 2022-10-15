/*
 * @author tuituidan
 * @date 2022/9/26
 */
import axios from 'axios';
// eslint-disable-next-line
import QS from 'qs';

const HttpPlugin = {};
const http = axios.create({
    timeout: 800000
});

http.interceptors.request.use(config => {
    config.headers['X-Requested-With'] = 'XMLHttpRequest';
    config.paramsSerializer = param => QS.stringify(param, {indices: false});
    return config;
});
http.interceptors.response.use(res => res, err => {
    if (err.response && err.response.status === 401) {
        window.location.href = '/login';
        return Promise.reject(err);
    }
    return Promise.reject(err);
});
HttpPlugin.install = Vue => {
    Vue.prototype.$http = http;
};
export default HttpPlugin;
