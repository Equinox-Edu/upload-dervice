package edu.equinox.storage.minio;

import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.equinox.storage.Response;
import edu.equinox.storage.StorageService;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;

@ApplicationScoped
@Named("minio_upload")
public class MinioService implements StorageService<UploadObjectArgs> {

    private final MinioClient minioClient;

    @Inject
    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public MinioClient getClient(){
        return minioClient;
    }

    @Override
    public boolean exists(String path) throws Exception {
        BucketExistsArgs existsQuery = BucketExistsArgs.builder().bucket(path).build();

        return minioClient.bucketExists(existsQuery);
    }

    @Override
    public Response uploadObject(UploadObjectArgs uploadObjectArgs) throws Exception {
        ObjectWriteResponse uploadedObject = minioClient.uploadObject(uploadObjectArgs);

        return new Response(Map.of("upload_id",uploadedObject.etag()));
    }
}
