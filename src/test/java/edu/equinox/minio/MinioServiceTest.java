package edu.equinox.minio;

import javax.inject.Inject;

import edu.equinox.storage.minio.MinioService;
import io.minio.MakeBucketArgs;
import io.minio.RemoveBucketArgs;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
class MinioServiceTest {
    public static final String TEST_BUCKET = "test";

    @Inject
    MinioService minioService;

    @BeforeEach
    void setup() throws Exception {
        minioService.getClient()
            .makeBucket(MakeBucketArgs.builder().bucket(TEST_BUCKET).build());
    }

    @AfterEach
    void destroy() throws Exception {
        minioService.getClient()
            .removeBucket(RemoveBucketArgs.builder().bucket("test").build());
    }

    @Test
    void bucketExists() throws Exception {
        boolean bucketExists = minioService.exists("test");

        Assertions.assertTrue(bucketExists);
    }
}