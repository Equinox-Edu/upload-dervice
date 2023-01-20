package edu.equinox.upload;

import edu.equinox.storage.Response;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public interface UploadHandler<T extends FileUpload> {
    Response save(T file) throws Exception;

    boolean isValidType(T file);
}
