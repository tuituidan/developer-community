/*
 * @author tuituidan
 * @date 2022/9/20
 */
export default {
    name: 'front-article',
    data() {
        return {
            activename: 'first',
            pageParam: {
                offset: 0,
                limit: 15
            },
            pageData: {
                datas: [],
                total: 0
            }
        };
    },
    mounted() {
        this.requestList();
    },
    methods: {
        requestList() {
            this.$http.get('/api/v1/article', {params: this.pageParam})
                .then(res => {
                    this.pageData.total = res.data.total;
                    this.pageData.datas = res.data.data;
                })
                .catch(err => {
                    window.console.error(err);
                });
        },
        pageSizeChange(pageSize) {
            this.pageParam.limit = pageSize;
            this.requestList();
        },
        pageIndexChange(pageIndex) {
            this.pageParam.offset = this.pageParam.limit * (pageIndex - 1);
            this.requestList();
        },
        articleItemClick(item) {
            this.$router.push(`/article/detail/${item.id}`);
        },
        tabChange(tab) {
            window.console.log(tab.name);
        }
    }
};
