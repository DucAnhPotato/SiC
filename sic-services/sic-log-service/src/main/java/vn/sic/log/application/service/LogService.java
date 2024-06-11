package vn.sic.log.application.service;

public interface LogService<T> {
    void logRequest(T request, Class<T> clazz);
}
