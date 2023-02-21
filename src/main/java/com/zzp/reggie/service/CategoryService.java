package com.zzp.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzp.reggie.entity.Category;

/**
 * @Author zzp
 * @Date 2023/2/18 11:18
 * @Description: TODO
 * @Version 1.0
 */
public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
