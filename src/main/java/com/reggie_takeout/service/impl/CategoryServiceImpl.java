package com.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_takeout.common.CustomException;
import com.reggie_takeout.entity.Category;
import com.reggie_takeout.entity.Dish;
import com.reggie_takeout.entity.SetMeal;
import com.reggie_takeout.mapper.CategoryMapper;
import com.reggie_takeout.service.CategoryService;
import com.reggie_takeout.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetMealServiceImpl setMealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int cnt1 = (int)dishService.count(dishLambdaQueryWrapper);

        if(cnt1 > 0) {
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        LambdaQueryWrapper<SetMeal> setMealLambdaQueryWrapper = new LambdaQueryWrapper<>();

        setMealLambdaQueryWrapper.eq(SetMeal::getCategoryId, id);

        int cnt2 = (int) setMealService.count(setMealLambdaQueryWrapper);

        if(cnt2 > 0) {
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        super.removeById(id);
    }
}
