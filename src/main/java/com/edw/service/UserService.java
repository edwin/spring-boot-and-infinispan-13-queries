package com.edw.service;

import com.edw.bean.User;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *     com.edw.service.UserService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 12 Des 2023 14:32
 */
@Service
public class UserService {

    private RemoteCacheManager remoteCacheManager;

    @Autowired
    public UserService(RemoteCacheManager remoteCacheManager) {
        this.remoteCacheManager = remoteCacheManager;
    }

    public User getUser(String name) {
        return (User) remoteCacheManager
                        .getCache("user-cache")
                        .getOrDefault(name, new User());
    }

    public User addUser(String name, Integer age, String address) {
        return (User) remoteCacheManager
                .getCache("user-cache")
                .put(name, new User(name, age, address));
    }
}
