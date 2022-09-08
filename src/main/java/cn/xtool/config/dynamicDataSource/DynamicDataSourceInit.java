package cn.xtool.config.dynamicDataSource;

import cn.xtool.config.SpringUtils;
import cn.xtool.model.entity.TenantInfo;
import cn.xtool.service.ITenantInfoService;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化动态数据源
 *
 * @author guomh
 * @date 2019/11/06
 */
@Slf4j
@Configuration
public class DynamicDataSourceInit {

    @Autowired
    private ITenantInfoService tenantInfoService;

    @PostConstruct
    public void InitDataSource() {
        log.info("=====初始化动态数据源=====");
        DynamicDataSource dynamicDataSource = SpringUtils.getBean("dynamicDataSource");
        HikariDataSource master = SpringUtils.getBean("master");
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", master);

        List<TenantInfo> tenantList = tenantInfoService.list();
        for (TenantInfo tenantInfo : tenantList) {
            log.info(tenantInfo.toString());
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(tenantInfo.getDatasourceDriver());
            dataSource.setJdbcUrl(tenantInfo.getDatasourceUrl());
            dataSource.setUsername(tenantInfo.getDatasourceUsername());
            dataSource.setPassword(tenantInfo.getDatasourcePassword());
            dataSource.setDataSourceProperties(master.getDataSourceProperties());
            dataSourceMap.put(tenantInfo.getTenantId(), dataSource);
        }
        //设置数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        /**
         * 必须执行此操作，才会重新初始化AbstractRoutingDataSource 中的 resolvedDataSources，也只有这样，动态切换才会起效
         */
        dynamicDataSource.afterPropertiesSet();
    }

}