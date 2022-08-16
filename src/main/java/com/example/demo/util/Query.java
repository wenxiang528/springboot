package com.example.demo.util;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分页工具
 *
 * @author MCS
 */
@Data
@Accessors(chain = true)
public class Query {

    /**
     * 当前页
     */
    private int pageIndex;

    /**
     * 每页的数量
     */
    private int pageSize;


}
