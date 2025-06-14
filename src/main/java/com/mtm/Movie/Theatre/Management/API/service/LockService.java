package com.mtm.Movie.Theatre.Management.API.service;

import java.util.concurrent.TimeUnit;

public interface LockService {

    /**
     * Try to acquire a lock for the given key within the timeout.
     *
     * @param key      unique lock key (e.g., showtimeId)
     * @param timeout  maximum wait time
     * @param unit     time unit for timeout
     * @return true if lock acquired, false otherwise
     */
    boolean acquireLock(String key, long timeout, TimeUnit unit);

    /**
     * Release the lock held for the given key.
     *
     * @param key the lock key to release
     */
    void releaseLock(String key);
}
