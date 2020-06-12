package com.ucu.edu.bigdata.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.config.ProgrammaticDriverConfigLoaderBuilder;
import com.datastax.oss.driver.internal.core.loadbalancing.DcInferringLoadBalancingPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.session.DefaultSessionFactory;

import java.net.InetSocketAddress;

@Configuration
public class CassandraConfig {

    @Value("${spring.data.cassandra.port}")
    private Integer port;
    @Value("${spring.data.cassandra.contact-points}")
    private String host;
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace;

    @Bean
    public CqlSession session() {

        ProgrammaticDriverConfigLoaderBuilder configBuilder =
                DriverConfigLoader.programmaticBuilder();
        configBuilder.withClass(DefaultDriverOption.LOAD_BALANCING_POLICY_CLASS,
                DcInferringLoadBalancingPolicy.class);
        DriverConfigLoader loader = configBuilder.endProfile().build();
        CqlSessionBuilder clusterBuilder = CqlSession.builder()
                .addContactPoint(new InetSocketAddress(host, port));

        return clusterBuilder.withConfigLoader(loader).withKeyspace(keyspace).build();
    }

    @Bean
    public CqlTemplate cqlTemplate(CqlSession session) {
        CqlTemplate cqlTemplate = new CqlTemplate();
        cqlTemplate.setSessionFactory(new DefaultSessionFactory(session));
        return cqlTemplate;
    }
}
