package com.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_takeout.dto.DishDto;
import com.reggie_takeout.entity.Dish;
import com.reggie_takeout.entity.DishFlavor;
import com.reggie_takeout.mapper.DishMapper;
import com.reggie_takeout.service.DishFlavorService;
import com.reggie_takeout.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);

        Long dishId = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().peek((item) -> item.setDishId(dishId)).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }
}
