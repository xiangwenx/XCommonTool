package cn.xtool.service.impl;

import cn.xtool.model.entity.TenantInfo;
import cn.xtool.model.dao.TenantInfoMapper;
import cn.xtool.service.ITenantInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwxu
 * @since 2022-08-28
 */
@Service
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements ITenantInfoService {

}
