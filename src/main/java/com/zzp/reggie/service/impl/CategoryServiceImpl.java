package com.zzp.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzp.reggie.common.CustomException;
import com.zzp.reggie.entity.Category;
import com.zzp.reggie.entity.Dish;
import com.zzp.reggie.entity.Setmeal;
import com.zzp.reggie.mapper.CategoryMapper;
import com.zzp.reggie.service.CategoryService;
import com.zzp.reggie.service.DishService;
import com.zzp.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zzp
 * @Date 2023/2/18 11:18
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除前要进行判断
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了菜品，如果已关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //根据分类id,查询
        queryWrapper.eq(Dish::getCategoryId, id);
        int count = dishService.count(queryWrapper);

        if (count > 0) {
            //已关联菜品
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        //查询当前分类是否关联了套餐，如果已关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> queryWrapper2 = new LambdaQueryWrapper<>();
        //根据分类id,查询
        queryWrapper2.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(queryWrapper2);

        if (count2 > 0) {
            //已关联套餐
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        //正常删除分类
        super.removeById(id);
    }
}
