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
public class Menu {

    private Integer menuId;

    private String menuName;

    private String menuCode;

    private String menuUrl;

    private String menuDescript;

    private Long createTime;
}
