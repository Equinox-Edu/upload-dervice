package edu.equinox;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class MyLivenessCheck implements HealthCheck {


    @Inject
    MinioClient minioClient;

    @Override
    public HealthCheckResponse call() {
        try {
            boolean pdf = minioClient.bucketExists(BucketExistsArgs.builder().bucket("pdf").build());
            if (pdf){
                return HealthCheckResponse.up("alive");
            }else {
                return HealthCheckResponse.up("not alive");
            }
        } catch (Exception e) {
            Log.warnf(e.getMessage(),e);
            return HealthCheckResponse.up("not alive");
        }

    }
}