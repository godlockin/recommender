package com.st.recommender.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@Data
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = { "com.st.recommender.db.mapper.tb" }
        , sqlSessionTemplateRef = "tbSqlSessionTemplate"
)
public class TBMybatisConfig extends MybatisConfig {

    @Value("${DB_URL_TB:}")
    private String DB_URL;
    @Value("${DB_PORT_TB:3306}")
    private Integer DB_PORT;
    @Value("${DB_COLLECTION_TB:tb}")
    private String DB_COLLECTION;
    @Value("${DB_USER_TB:root}")
    private String DB_USER;
    @Value("${DB_PASSWORD_TB:}")
    private String DB_PWD;
    private String mapperPath = "classpath*:mapper/tb/*.xml";

    @Bean(name="tbDataSource")
    public DataSource dataSource() {
        DruidDataSource datasource = baseDataSource();
        datasource.setUrl(formJDBCUrl());
        datasource.setUsername(DB_USER);
        datasource.setPassword(DB_PWD);
        return datasource;
    }

    private String formJDBCUrl() {
        return String.format("jdbc:mysql://%s:%s/%s?useUnicode=yes&characterEncoding=UTF-8&useSSL=true"
                , DB_URL
                , DB_PORT
                , DB_COLLECTION
        ) + "&serverTimezone=GMT%2B8";
    }

    @Primary
    @Bean(name = "tbSqlSessionFactory")
    public SqlSessionFactory tbSqlSessionFactory() {
        return getSqlSessionFactory(mapperPath, dataSource());
    }

    @Primary
    @Bean(name = "tbSqlSessionTemplate")
    public SqlSessionTemplate tbSqlSessionTemplate(@Qualifier("tbSqlSessionFactory") SqlSessionFactory tbSqlSessionFactory) {
        return new SqlSessionTemplate(tbSqlSessionFactory);
    }

    @Primary
    @Bean(name = "tbTransactionManager")
    public PlatformTransactionManager tbTransactionManager(@Qualifier("tbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
