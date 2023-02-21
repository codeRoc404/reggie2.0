package com.zzp.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzp.reggie.entity.Employee;
import com.zzp.reggie.mapper.EmployeeMapper;
import com.zzp.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Author zzp
 * @Date 2023/2/16 15:34
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{
}
