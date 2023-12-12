package com.edw.service;

import com.edw.bean.User;
import org.infinispan.Cache;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> getUsersFromCity(String address) {
        RemoteCache remoteCache = remoteCacheManager.getCache("user-cache");
        QueryFactory queryFactory = Search.getQueryFactory(remoteCache);

        Query<User> query = queryFactory.create("FROM user.User WHERE address like :address ORDER BY name ASC, age DESC");
        query.setParameter("address", address);

        return query.execute().list();
    }
}
