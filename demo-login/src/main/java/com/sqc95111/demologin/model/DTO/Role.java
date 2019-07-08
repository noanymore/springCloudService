package com.sqc95111.demologin.model.DTO;

import lombok.Data;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/24 19:46
 * @Version 1.0
 */
@Data
public class Role {

    private Integer roleId;

    private String roleName;

    private String roleCode;

    private String roleDescript;

    private Long createTime;
}
