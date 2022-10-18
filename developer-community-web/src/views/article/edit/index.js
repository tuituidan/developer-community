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
            rules: {
                tags: [
                    {required: true, message: '请选择标签', trigger: 'change'},
                    {
                        validator: (rule, value, callback) => {
                            if (Array.isEmpty(value)) {
                                callback(new Error('请选择标签'));
                                return;
                            }
                            if (value.length > 5) {
                                callback(new Error('最多选择五个标签'));
                                return;
                            }
                            callback();
                        },
                        trigger: 'change'
                    }
                ]
            },
            fileList: [],
            cascader: {
                options: [],
                props: {
                    // 支持多选
                    multiple: true,
                    // 多级只显示最后一级文字
                    emitPath: false,
                    // 父子关联
                    checkStrictly: true,
                    // 次级菜单展开方式
                    expandTrigger: 'hover',
                    value: 'id',
                    label: 'name'
                }
            },
            drawerOpen: false
        };
    },
    watch: {
        // eslint-disable-next-line
        $route(to, from) {
            // 新增和编辑公用页面，检测路由来控制数据
            this.init();
        }
    },
    mounted() {
        this.$emit('frontHeaderMsg', {hideTopic: true});
        this.requestTags();
        this.init();
    },
    methods: {
        init() {
            if (this.$route.params.id) {
                this.requestItem();
                this.requestAttachFiles();
            } else {
                this.editItem = {};
                this.fileList = [];
            }
        },
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
            this.$http.get(`/api/v1/article/${this.$route.params.id}`)
                .then(res => {
                    this.editItem = res.data;
                })
                .catch(err => {
                    window.console.error(err);
                });
        },
        requestAttachFiles() {
            this.$http.get(`/api/v1/${this.$route.params.id}/attach/file`)
                .then(res => {
                    if (Array.isEmpty(res.data)) {
                        this.fileList = [];
                        return;
                    }
                    this.fileList = res.data.map(item => (
                        {
                            name: item.name,
                            response: item.id,
                            url: item.path
                        }));
                })
                .catch(err => {
                    window.console.error(err);
                });
        },
        saveArticle(publish) {
            if (!this.editItem.title) {
                this.$notify.warning('请填写文章标题');
                return;
            }
            if (!this.editItem.contentMarkdown) {
                this.$notify.warning('请填写文章内容');
                return;
            }
            this.$refs.editForm.validate(valid => {
                if (!valid) {
                    return false;
                }
                this.editItem.publish = publish;
                this.requestSave();
            });
        },
        requestSave() {
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
        }
    }
};
