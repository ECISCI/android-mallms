package com.mincat.sample.custom.progress;

/**
 * @author Gin
 */

public interface ProgressListener {
    void onProgress(long soFarBytes, long totalBytes);

    void onError(Throwable throwable);
}
