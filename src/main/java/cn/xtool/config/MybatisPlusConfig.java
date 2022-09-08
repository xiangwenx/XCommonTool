package cn.xtool.config;

import cn.xtool.config.dynamicDataSource.DynamicDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MybatisPlusConfig {

    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", master());
        // 将 master 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultDataSource(master());
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        /*
          重点，使分页插件生效
         */
        Interceptor[] plugins = new Interceptor[1];
        plugins[0] = paginationInterceptor();
        sessionFactory.setPlugins(plugins);
        //配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("cn.xtool.model.entity");    // 扫描Model
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:sqlmap/*/*Mapper.xml"));    // 扫描映射文件
        return sessionFactory;
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource());
    }
    /**
     * 加载分页插件
     * @return
     */
    //@Bean
    //public PaginationInterceptor paginationInterceptor() {
    //    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    //
    //    List<ISqlParser> sqlParserList = new ArrayList<>();
    //    // 攻击 SQL 阻断解析器、加入解析链
    //    sqlParserList.add(new BlockAttackSqlParser());
    //    paginationInterceptor.setSqlParserList(sqlParserList);
    //    return paginationInterceptor;
    //}

    @Bean
    public MybatisPlusInterceptor paginationInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
