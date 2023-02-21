package com.zzp.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzp.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zzp
 * @Date 2023/2/16 15:32
 * @Description: TODO
 * @Version 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
