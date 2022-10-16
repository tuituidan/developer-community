package com.tuituidan.openhub.service;

import com.tuituidan.openhub.config.MinioConfig;
import com.tuituidan.openhub.consts.Consts;
import com.tuituidan.openhub.exception.MinioException;
import com.tuituidan.openhub.mapper.AttachFileMapper;
import com.tuituidan.openhub.pojo.entity.AttachFile;
import com.tuituidan.tresdin.util.StringExtUtils;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.SetBucketPolicyArgs;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * MinioService.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/15
 */
@Service
@Slf4j
public class MinioService {

    @Resource
    private MinioConfig minioConfig;

    @Resource
    private MinioClient minioClient;

    @Resource
    private AttachFileMapper attachFileMapper;

    /**
     * 配置桶只读的是个比较长的json字符，放在配置文件中.
     */
    private static final String MINIO_CONFIG = "config/minio-policy.json";

    @PostConstruct
    private void init() {
        try {
            if (minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfig.getBucket()).build())) {
                return;
            }
            log.warn("【{}】的桶不存在，将会创建一个。", minioConfig.getBucket());
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioConfig.getBucket()).build());

            // 设置桶为只读权限
            String policy = StreamUtils.copyToString(new ClassPathResource(MINIO_CONFIG).getInputStream(),
                    StandardCharsets.UTF_8);
            policy = policy.replace("bucket-name", minioConfig.getBucket());
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .config(policy)
                    .build());
            log.info("【{}】的桶已经创建成功", minioConfig.getBucket());
        } catch (Exception ex) {
            throw new MinioException("初始化创建 MinioClient 出错，请检查！", ex);
        }
    }

    /**
     * 文件上传
     *
     * @param file 上传文件
     * @param type 文件类型
     * @return 文件ID
     */
    public String uploadFile(MultipartFile file, String type) {
        LocalDate now = LocalDate.now();
        String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
        String objectName = StringExtUtils.format("{}/{}/{}/{}.{}",
                now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                StringExtUtils.getUuid(), fileExt);
        putObject(objectName, file);
        String storePath = StringExtUtils.format("/{}/{}", minioConfig.getBucket(), objectName);
        if (!Consts.ATTACH_FILE.equals(type)) {
            return storePath;
        }
        String id = StringExtUtils.getUuid();
        attachFileMapper.insert(new AttachFile().setId(id)
                .setName(file.getOriginalFilename())
                .setPath(storePath)
                .setSize(file.getSize()));
        return id;
    }

    /**
     * 上传文件到 Minio 中.
     *
     * @param objectName 对象名
     * @param file 文件
     */
    private void putObject(String objectName, MultipartFile file) {
        try {
            minioClient.putObject(PutObjectArgs.builder().bucket(minioConfig.getBucket())
                    .contentType(file.getContentType())
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1).build());
        } catch (Exception ex) {
            throw new MinioException("向 Minio 中上传文件出错，文件名称-【{}】", objectName, ex);
        }
    }

}
