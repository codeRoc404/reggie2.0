package com.zzp.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzp.reggie.dto.SetmealDto;
import com.zzp.reggie.entity.Setmeal;

import java.util.List;

/**
 * @Author zzp
 * @Date 2023/2/18 15:00
 * @Description: TODO
 * @Version 1.0
 */
public interface SetmealService extends IService<Setmeal> {



    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
