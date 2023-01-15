package edu.equinox.upload;

import org.jboss.resteasy.reactive.multipart.FileUpload;

public interface UploadHandler<T extends FileUpload> {
    int save(T file);
    boolean isValidType(T file);
}
