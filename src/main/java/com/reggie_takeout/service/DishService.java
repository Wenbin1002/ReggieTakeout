package com.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie_takeout.dto.DishDto;
import com.reggie_takeout.entity.Dish;

public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);
}
