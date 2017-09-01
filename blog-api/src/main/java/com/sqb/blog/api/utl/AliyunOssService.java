package com.sqb.blog.api.utl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.sqb.blog.util.AppContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

/**
 * Created by vic
 * Create time : 2017/8/22 下午3:50
 */
public class AliyunOssService {


    private static AppContext appContext;

    private static String endpoint = appContext.getProperty("aliyun.oss.endpoint");
    private static String accessKeyId = appContext.getProperty("aliyun.oss.accessKeyId");
    private static String accessKeySecret = appContext.getProperty("aliyun.oss.accessKeySecret");
    private static String bucketName = appContext.getProperty("aliyun.oss.bucketName");


    public static String upload(String filePath, File file) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // bucketName 不存在，自动创建
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.Private);//  私有
            ossClient.createBucket(createBucketRequest);
        }

        PutObjectResult putObjectResult = ossClient.putObject(bucketName, filePath, file);
        if (null != putObjectResult && StringUtils.isNotBlank(putObjectResult.getETag())) {
            return putObjectResult.getETag().toLowerCase();
        }

        return StringUtils.EMPTY;
    }

    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".txt");
        file.deleteOnExit();
        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvswxyz\n");
        writer.write("0123456789011234567890\n");
        writer.close();
        return file;
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            System.out.println("    " + line);
        }
        System.out.println();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:config/applicationContext*.xml"});

        AppContext appContext = context.getBean(AppContext.class);
//        String upload = upload("1.txt", createSampleFile());
//        System.out.println(upload);
    }
}
