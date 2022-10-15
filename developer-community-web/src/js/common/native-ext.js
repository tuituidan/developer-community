/*
 * 原生js对象扩展
 * @author tuituidan
 * @date 2022/9/26
 */
/* eslint-disable */
/**
 * 参数化restfull风格的url
 * @param {Object|String} params 替换参数类型
 * @returns {String|string} url的格式化
 */
window.String.prototype.formatUrl = function (params) {
    let url = this;
    // 单个参数直接替换
    if (typeof params === 'string' || typeof params === 'number') {
        return url.replace(new RegExp('\\{.*\\}', 'gm'), params);
    }
    // 对象参数使用key替换
    for (const [key, value] of Object.entries(params)) {
        url = url.replace(new RegExp(`\\{${key}\\}`, 'gm'), value);
    }
    return url;
};
