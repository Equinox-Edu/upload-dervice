package edu.equinox.storage;

public interface StorageService<T> {
    boolean exists(String path) throws Exception;

    Response uploadObject(T filePath) throws Exception;
}
