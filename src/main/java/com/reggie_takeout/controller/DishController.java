package com.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie_takeout.common.R;
import com.reggie_takeout.entity.Dish;
import com.reggie_takeout.entity.Employee;
import com.reggie_takeout.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public R<Page<Dish>> pageR(int page, int pageSize, String name) {

        Page<Dish> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        dishService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    @PostMapping
    public R<String> save(@RequestBody Dish dish) {
        log.info(dish.toString());
        dishService.save(dish);

        return R.success("添加菜品成功");
    }
}
