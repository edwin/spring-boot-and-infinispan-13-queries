package com.edw.config;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *     com.edw.config.InfinispanConfiguration
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 12 Des 2023 14:22
 */
@Configuration
public class InfinispanConfiguration {

    @Value("${my.infinispan.endpoint}")
    private String infinispanEndpoint;

    @Value("${my.infinispan.username}")
    private String infinispanUsername;

    @Value("${my.infinispan.password}")
    private String infinispanPassword;

    @Bean
    public RemoteCacheManager remoteCacheManager() {
        return new RemoteCacheManager(
                new org.infinispan.client.hotrod.configuration.ConfigurationBuilder()
                        .addServers(infinispanEndpoint)
                        .security().authentication()
                                .username(infinispanUsername)
                                .password(infinispanPassword)
                        .clientIntelligence(ClientIntelligence.HASH_DISTRIBUTION_AWARE)
                        .marshaller(ProtoStreamMarshaller.class)
                        .build());
    }
}
