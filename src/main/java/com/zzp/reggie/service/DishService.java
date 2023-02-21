package com.zzp.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzp.reggie.dto.DishDto;
import com.zzp.reggie.entity.Dish;

/**
 * @Author zzp
 * @Date 2023/2/18 15:00
 * @Description: TODO
 * @Version 1.0
 */
public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
    void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    DishDto getByIdWithFlavor(Long id);

    //更新菜品，同时跟新菜品对应的口味数据
    void updateWithFlavor(DishDto dishDto);
}
