package com.example.demo.entity;

import java.io.Serializable;

import com.example.demo.util.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 薛之谦
 * @since 2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher  implements  Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tid;

    /**
     *姓名
     */
    private String tname;

    private String tsex;

    private Integer tage;

    private String tlvl;


}
