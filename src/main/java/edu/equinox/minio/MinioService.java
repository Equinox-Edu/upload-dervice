package edu.equinox.minio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;

@ApplicationScoped
public class MinioService {

    @Inject
    MinioClient minioClient;


  //  @ConfigProperty(name = "minio.bucket-name")
    String bucketName="pdf";

    void todo() throws Exception{
        minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }
}
