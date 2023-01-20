package edu.equinox.upload;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.equinox.UploadException;
import edu.equinox.storage.Response;
import edu.equinox.storage.StorageService;
import io.minio.UploadObjectArgs;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@ApplicationScoped
public class UploadService implements UploadHandler<FileUpload> {

    @ConfigProperty(name = "minio.bucket.name")
    String bucketName;

    @Inject
    @Named("minio_upload")
    StorageService<UploadObjectArgs> minioService;

    @Override
    public Response save(FileUpload file) throws Exception {
        if (file == null || !isValidType(file)) {
            throw new UploadException();
        }
        UploadObjectArgs uploadObject = toBucketFile(file);
        return minioService.uploadObject(uploadObject);
    }

    @Override
    public boolean isValidType(FileUpload file) {
        String contentType = file.contentType();
        String charSet = file.charSet();

        return isValidContentType(contentType) && isValidCharset(charSet);
    }

    private static boolean isValidCharset(String charSet) {
        //Todo
        return true;
    }

    private static boolean isValidContentType(String contentType) {
        //Todo
        return true;
    }

    private UploadObjectArgs toBucketFile(FileUpload file) throws IOException {
        return UploadObjectArgs.builder()
            .object(UUID.randomUUID().toString())
            .userMetadata(Map.of("name", file.fileName()))
            .bucket(bucketName)
            .filename(file.filePath().toAbsolutePath().toString())
            .contentType(file.contentType())
            .build();
    }
}
