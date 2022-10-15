/*
 * @author tuituidan
 * @date 2022/9/20
 */
import '@/css/markdown/github-markdown.min.css';
import '@/css/markdown/katex.min.css';
import '@/css/markdown/github.min.css';

export default {
    name: 'front-article-detail',
    data() {
        return {
            item: {}
        };
    },
    mounted() {
        this.requestItem();
    },
    methods: {
        requestItem() {
            this.$http.get(`/api/v1/article/${this.$route.params.id}`)
                .then(res => {
                    this.item = res.data;
                })
                .catch(err => {
                    window.console.error(err);
                });
        },
        editHandler() {
            this.$router.push(`/article/edit/${this.$route.params.id}`);
        },
        setTopOrValuable(type, value) {
            this.$http.patch(`/api/v1/article/${this.$route.params.id}/${type}/${value}`)
                .then(() => {
                    this.item[type] = value;
                    this.$notify.success('修改成功');
                })
                .catch(err => {
                    window.console.error(err);
                });
        }
    }
};
