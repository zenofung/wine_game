package com.zenofung.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: ________
 * ___  __/______________
 * __  /_ ___  __ \  ___/
 * _  __/ __  /_/ / /__
 * /_/    _  .___/\___/
 * /_/
 * @author: www.fpcs.top
 * @create: 2020-12-23 13:59
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private Set<Integer> set=new HashSet<Integer>();


    //初始化方法
    public void initialize(ListValue constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        for (int v:values){
            set.add(v);
        }
    }


    //判断是否校验成功
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}