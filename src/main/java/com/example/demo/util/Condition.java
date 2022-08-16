package com.example.demo.util;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.lang.Nullable;

/**
 * 分页工具
 *
 * @author OfsGroup
 */
public class Condition {

    /**
     * 转化成mybatis plus中的Page
     *
     * @param query
     * @return
     */
    public static <T> IPage<T>  getPage(com.example.demo.util.Query query) {
        if(null == query) {
            query = new Query();
            query.setPageIndex(1);
            query.setPageSize(20);
        }

        return new Page<>(toInt(query.getPageIndex(), 0) , toInt(query.getPageSize(), 20));
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper<>(entity);
    }

    public static int toInt(@Nullable final Object str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(String.valueOf(str));
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

}
