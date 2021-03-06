package com.sqc95111.demologin.model.DTO;

import lombok.Data;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/24 19:45
 * @Version 1.0
 */
@Data
public class Authority {

    private Integer authId;

    private String authName;

    private String authCode;

    private String authDescript;

    private Long createTime;
}
