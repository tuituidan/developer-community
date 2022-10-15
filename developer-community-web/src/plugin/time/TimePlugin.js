/*
 * 时间相关
 * @author tuituidan
 * @date 2022/9/26
 */
import {format} from 'timeago.js';

const TimePlugin = {};
const timeUtils = {
    prettytime(time) {
        return format(time, 'zh_CN');
    }
};

TimePlugin.install = (Vue) => {
    Vue.prototype.$timeUtils = timeUtils;
};
export default TimePlugin;
