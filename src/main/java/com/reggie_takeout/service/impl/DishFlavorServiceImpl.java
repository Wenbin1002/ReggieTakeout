package com.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_takeout.entity.DishFlavor;
import com.reggie_takeout.mapper.DishFlavorMapper;
import com.reggie_takeout.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
