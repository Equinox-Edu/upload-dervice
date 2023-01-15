package edu.equinox.upload;

import edu.equinox.UploadException;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class UploadService<T extends FileUpload> implements UploadHandler<T> {
    @Override
    public int save(T file) {
        if (!isValidType(file)){
            throw new UploadException();
        }

        return 0;
    }

    @Override
    public boolean isValidType(T file) {
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
}
