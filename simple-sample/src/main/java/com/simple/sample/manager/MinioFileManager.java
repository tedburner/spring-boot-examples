package com.simple.sample.manager;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: lingjun.jlj
 * @date: 2021/3/16 18:06
 * @description:
 */
public class MinioFileManager {

    public static void main(String[] args) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://127.0.0.1:9100")
                .credentials("minioadmin", "minioadmin")
                .build();

        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket("Bucket")
                .build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("Bucket").build());
        }
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket("Bucket")
                        .object("20210316.png")
                        .filename("C:\\Users\\Admin\\Pictures\\81124306_p0.png")
                        .build()
        );
        System.out.println("Upload Successfully");
    }
}
