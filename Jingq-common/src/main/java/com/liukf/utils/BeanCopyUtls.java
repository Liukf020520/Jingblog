package com.liukf.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtls {

    private BeanCopyUtls(){

    }
//单个对象的拷贝
    public static <V> V copyBean(Object source,Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性的拷贝
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        //返回结果
    }
    //批量拷贝
            public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
                return list.stream()
                        .map(o -> copyBean(o, clazz))
                        .collect(Collectors.toList());
            }


    }
