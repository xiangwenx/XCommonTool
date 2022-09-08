package cn.xtool.controller;

import cn.xtool.service.ParkDeptService;
import cn.xtool.model.entity.ParkDept;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xwxu
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/park-dept")
public class ParkDeptController {

    @Resource
    private ParkDeptService parkDeptService;

    @GetMapping("/{id}")
    public ParkDept get(@PathVariable("id") Integer id) {
        return parkDeptService.getById(id);
    }

    @PostMapping
    public Boolean save(@RequestBody ParkDept parkDept) {
        parkDeptService.save(parkDept);
        return Boolean.TRUE;
    }

    @GetMapping("/list")
    public Page<ParkDept> list(int pageSize, int pageNum, Long parkId) {
        return parkDeptService.page(new Page<>(pageNum, pageSize), Wrappers.<ParkDept>lambdaQuery().eq(Objects.nonNull(parkId), ParkDept::getParkId, parkId));
    }
}
