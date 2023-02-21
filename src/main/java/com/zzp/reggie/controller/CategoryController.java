package com.zzp.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzp.reggie.common.R;
import com.zzp.reggie.entity.Category;
import com.zzp.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author zzp
 * @Date 2023/2/18 11:20
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        //分页构造器
        Page pageInfo = new Page(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByDesc(Category::getUpdateTime);
        //执行查询
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable("id") Long id){
        categoryService.remove(id);
        return R.success("分类信息删除成功");
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Category category){
        Long empId = (Long) request.getSession().getAttribute("employee");
        category.setUpdateUser(empId);
        categoryService.updateById(category);
        return R.success("修改分类信息成功");
    }

    /**
     * 根据条件查询分类条件
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        //排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}
