package cn.xtool.controller;

import cn.xtool.service.ParkDeptService;
import cn.xtool.model.entity.ParkDept;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author xwxu
 * @description
 * @date 2022-08-26
 */
@SpringBootTest
public class ParkDeptControllerTest {
    @Resource
    ParkDeptService parkDeptService;

    @Test
    public void get() {
        ParkDept parkDept = parkDeptService.getOne(Wrappers.<ParkDept>lambdaQuery().eq(ParkDept::getId, 769882374757416960L).eq(ParkDept::getParkId, 6456419921961160888L));
        System.out.println();
    }

    @Test
    public void save() {
        ParkDept parkDept = new ParkDept();
        parkDept.setParkId(6456419921961160704L);
        parkDept.setDeptName("方法");
        parkDeptService.save(parkDept);

        parkDept.setParkId(6290020514064764928L);
        parkDept.setId(null);
        parkDeptService.save(parkDept);
        System.out.println();
    }

    @Test
    public void list() {
        Page<ParkDept> page = parkDeptService.page(new Page<>(1, 3), Wrappers.<ParkDept>lambdaQuery()
                .eq(false, ParkDept::getParkId, 6290020514064764928L));
        System.out.println();
    }
}