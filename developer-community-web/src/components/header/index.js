/*
 * @author tuituidan
 * @date 2022/10/23
 */
import dataDict from '@/js/data-dict';

export default {
    name: 'views-index',
    data() {
        return {
            topicShow: true,
            topicOpen: false,
            topics: [],
            tags: []
        };
    },
    mounted() {
        this.requestTags();
    },
    methods: {
        requestTags() {
            this.$http.get(`/api/v1/data-dict/type/${dataDict.TYPE.TAG}/tree`)
                .then(res => {
                    if (Array.isArray(res.data)) {
                        res.data.unshift({
                            id: 'all',
                            name: '全部',
                            selected: true,
                            children: []
                        });
                        this.topics = res.data;
                    }
                })
                .catch(err => {
                    window.console.error(err);
                });
        },
        changeTopic(topic) {
            this.topics.forEach(item => {
                this.$set(item, 'selected', false);
            });
            this.$set(topic, 'selected', true);
            this.tags = topic.children;
        },
        frontHeaderMsg(setting) {
            this.topicShow = !setting.hideTopic;
        }
    }
};
