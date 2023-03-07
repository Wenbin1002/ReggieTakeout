package com.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_takeout.dto.SetmealDto;
import com.reggie_takeout.entity.Setmeal;
import com.reggie_takeout.entity.SetmealDish;
import com.reggie_takeout.mapper.SetmealDishMapper;
import com.reggie_takeout.mapper.SetmealMapper;
import com.reggie_takeout.service.SetmealDishService;
import com.reggie_takeout.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;
    @Override
    public void saveWithDishes(SetmealDto setmealDto) {
        this.save(setmealDto);

        Long setmealId = setmealDto.getId();

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().peek((item) -> item.setSetmealId(setmealId)).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }
}
