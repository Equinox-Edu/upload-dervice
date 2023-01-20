package edu.equinox;

import javax.inject.Inject;
import javax.inject.Named;

import edu.equinox.storage.StorageService;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class MinioLivenessCheck implements HealthCheck {

    @Inject
    @Named("minio_upload")
    StorageService<?> minioService;

    @ConfigProperty(name = "minio.bucket.name")
    String bucketName;

    @Override
    public HealthCheckResponse call() {
        try {
            boolean bucketExists = minioService.exists((bucketName));
            if (bucketExists) {
                return HealthCheckResponse.up("alive");
            } else {
                Log.trace("bucket not exists");
                return HealthCheckResponse.up("not alive");
            }
        } catch (Exception e) {
            Log.warnf(e.getMessage(), e);
            return HealthCheckResponse.down("not alive");
        }
    }
}