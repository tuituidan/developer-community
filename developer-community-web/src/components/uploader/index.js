/*
 * @author tuituidan
 * @date 2022/10/15
 */
export default {
    name: 'uploader',
    props: {
        listType: {
            type: String,
            default: 'text'
        },
        // 文件数量限制
        limit: {
            type: Number,
            default: 5
        },
        fileLimitSize: {
            type: Number,
            default: 50 * 1024 * 1024
        },
        accept: {
            type: String,
            default: '.png,.jpg,.jpeg,.gif,.bmp,.rar,.zip,.tar,.gz,.7z,.doc,.docx,.xls,.xlsx,.ppt,.pptx,.pdf,.txt,.md,.xml'
        },
        tip: String,
        // 传到服务器的额外数据
        data: Object,
        // limit为1，value是String，大于1，value是Array
        value: [String, Array],
        initList: {
            type: [String, Array],
            default: () => []
        }
    },
    data() {
        return {
            hideUpload: false
        };
    },
    computed: {
        fileList: function () {
            if (!this.initList) {
                return [];
            }
            return typeof this.initList === 'string'
                ? [{url: this.initList}] : JSON.parse(JSON.stringify(this.initList));
        }
    },
    mounted() {
        this.hideUpload = this.fileList.length === this.limit;
    },
    methods: {
        onExceed() {
            this.$notify.error(`最多只能上传${this.limit}个附件`);
        },
        handlePaste(event) {
            const items = event.clipboardData && event.clipboardData.items;
            if (!(items && items.length)) {
                this.$notify.error('当前浏览器不支持粘贴上传，请点击文件选择按钮进行上传！');
                return;
            }
            for (const element of items) {
                this.$refs.uploader.handleStart(element.getAsFile());
            }
            this.$refs.uploader.submit();
        },
        onChange(file, files) {
            this.hideUpload = files.length === this.limit;
        },
        beforeUpload(file) {
            if (file.size > this.fileLimitSize) {
                this.$notify.error('最大只能上传50MB的文件');
                return false;
            }
            const suffix = file.name.substring(file.name.lastIndexOf('.'));
            const allow = this.accept.split(',').includes(suffix.toLowerCase());
            if (!allow) {
                this.$notify.error('不支持该文件格式的上传');
            }
            return allow;
        },
        onSuccess(res, file, files) {
            this.$emit('input', this.limit > 1 ? files.map(item => item.response) : res);
        }
    }
};
