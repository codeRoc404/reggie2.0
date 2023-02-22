package com.zzp.reggie.dto;


import com.zzp.reggie.entity.Setmeal;
import com.zzp.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
