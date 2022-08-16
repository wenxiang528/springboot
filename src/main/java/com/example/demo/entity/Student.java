package com.example.demo.entity;

import java.io.Serializable;
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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sid;

    private String sname;

    private Integer sage;

    private String ssex;

    private String snativeplace;

    private String smajor;

    private String sclass;

    private String snative;


}
