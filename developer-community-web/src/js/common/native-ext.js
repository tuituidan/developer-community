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
/**
 * 判断数组是否为空，非数组或者数组长度为0都为true
 * @param {Array} arr 数组
 * @returns {boolean} 是否为空
 */
window.Array.isEmpty = function (arr) {
    return !window.Array.isArray(arr) || arr.length <= 0;
}
/**
 * 数组去重，支持对象数组根据某个属性进行去重
 * 例：
 * ['1','2','2','3'].distinct() = ['1''2','3']
 * [{name:'a',age:15},{name:'a',age:4},{name:'b',age:7}].distinct('name') = [{name:'a',age:15},{name:'b',age:7}]
 * @param {String} prop 属性
 * @returns {*}
 */
window.Array.prototype.distinct = function (prop) {
    const res = new Map();
    const func = prop ? a => a[prop] : a => a;
    return this.filter(item => !res.has(func(item)) && res.set(func(item), 1));
};
