/*
 * @author tuituidan
 * @date 2022/9/21
 */
import '@/css/markdown/github-markdown.min.css';
import '@/css/markdown/katex.min.css';
import '@/css/markdown/github.min.css';

export default {
    name: 'front-article-edit',
    data() {
        return {
            editItem: {
                title: '',
                firstImage: '',
                tags: [],
                contentMarkdown: '',
                contentHtml: '',
                filesIds: []
            },
            cascader: {
                options: [],
                props: {
                    multiple: true,
                    emitPath:false,
                    checkStrictly: true,
                    value: 'id',
                    label: 'name'
                }
            },
            drawerOpen: false,
            fileList: [
                {
                    name: 'food.jpeg',
                    url: 'https://fuss10.elemecdn.com/'
                },
                {
                    name: 'food2.jpeg',
                    url: 'https://fuss10.elemecdn.com'
                }]
        };
    },
    mounted() {
        this.$emit('frontHeaderMsg', {hideTopic: true});
        this.requestTags();
        this.requestItem();
    },
    methods: {
        requestTags() {
            this.$http.get('/api/v1/data-dict/type/2100000000/tree')
                .then(res => {
                    this.cascader.options = res.data;
                })
                .catch(err => {
                    window.console.error(err);
                });
        },
        requestItem() {
            if (this.$route.params.id) {
                this.$http.get(`/api/v1/article/${this.$route.params.id}`)
                    .then(res => {
                        this.editItem = res.data;
                    })
                    .catch(err => {
                        window.console.error(err);
                    });
            }
        },
        saveArticle() {
            if (!this.editItem.title) {
                this.$notify.warning('请填写文章标题');
                return;
            }
            if (!this.editItem.contentMarkdown) {
                this.$notify.warning('请填写文章内容');
                return;
            }
            this.editItem.contentHtml = this.$refs.refMd.d_render;
            const ajax = this.$route.params.id
                ? this.$http.patch(`/api/v1/article/${this.$route.params.id}`, this.editItem)
                : this.$http.post('/api/v1/article', this.editItem);
            ajax
                .then(res => {
                    this.$notify.success('修改成功');
                    setTimeout(() => {
                        this.$router.push(`/article/edit/${res.data || this.$route.params.id}`);
                    }, 2000);
                })
                .catch(err => {
                    window.console.error(err);
                });
        },
        openDrawer() {
            this.drawerOpen = true;
        },
        handlePicturePreview(file) {
            const reader = new FileReader();
            reader.onload = () => {
                this.editItem.firstImage = reader.result;
            };
            reader.readAsDataURL(file.raw);
        }
    }
};
