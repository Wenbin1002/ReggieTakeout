package com.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie_takeout.dto.SetmealDto;
import com.reggie_takeout.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDishes(SetmealDto setmealDto);

    public void updateWithDishes(SetmealDto setmealDto);

    public void deleteWithDishes(Long id);

    public SetmealDto getByIdWithDishes(Long id);
}
