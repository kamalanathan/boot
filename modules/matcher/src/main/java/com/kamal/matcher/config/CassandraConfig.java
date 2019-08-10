package com.kamal.matcher.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

//@Configuration
//public class CassandraConfig extends AbstractCassandraConfiguration {
// 
//    @Override
//    protected String getKeyspaceName() {
//        return "kamals_keyspace";
//    }
// 
//    @Bean
//    public CassandraClusterFactoryBean cluster() {
//        CassandraClusterFactoryBean cluster = 
//          new CassandraClusterFactoryBean();
//        cluster.setContactPoints("192.168.1.3,192.168.1.2,192.168.1.4");
//        cluster.setPort(9042);
//        return cluster;
//    }
// 
//    @Bean
//    public CassandraMappingContext cassandraMapping() 
//      throws ClassNotFoundException {
//        return new CassandraMappingContext();
//    }
//}

//@Configuration
//@EnableCassandraRepositories(basePackages = { "com.kamal" })
public class CassandraConfig{ 
	// extends AbstractCassandraConfiguration {
	//
	// @Bean
	// public CassandraClusterFactoryBean cluster() {
	// CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
	// cluster.setContactPoints("192.168.1.3");
	// cluster.setPort(9042);
	// return cluster;
	// }
	//
	// @Override
	// protected String getKeyspaceName() {
	// return "kamals_keyspace";
	// }
	//
	// @Bean
	// public CassandraMappingContext cassandraMapping() throws
	// ClassNotFoundException {
	// return new BasicCassandraMappingContext();
	// }
}