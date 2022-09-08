package cn.xtool.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xwxu
 * @since 2022-08-28
 */
@Getter
@Setter
@TableName("tenant_info")
public class TenantInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 数据源url
     */
    private String datasourceUrl;

    /**
     * 数据源用户名
     */
    private String datasourceUsername;

    /**
     * 数据源密码
     */
    private String datasourcePassword;

    /**
     * 数据源驱动
     */
    private String datasourceDriver;

    /**
     * 系统账号
     */
    private String systemAccount;

    /**
     * 账号密码
     */
    private String systemPassword;

    /**
     * 系统PROJECT
     */
    private String systemProject;

    /**
     * 是否启用（1是0否）
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
