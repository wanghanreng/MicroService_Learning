package top.whr.userservice.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.whr.userservice.config.OssConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api/oss")
public class OssUploadController {

    @Autowired
    private OssConfig ossConfig; // 上文提到的配置类

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        // 创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); // 生成唯一文件名
        String bucketName = ossConfig.getBucketName();
        String url = ossConfig.getUrl() + "/" + fileName;

        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
            ossClient.putObject(putObjectRequest);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败：" + e.getMessage();
        } finally {
            ossClient.shutdown(); // 关闭客户端
        }

        return "上传成功，文件URL：" + url;
    }
}