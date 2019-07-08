package com.sqc95111.datasynchronize.service;

import java.sql.SQLException;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/4/4 14:14
 * @Version 1.0
 */
public interface SqlService {
    public void sqlMethod(String urlBody) throws ClassNotFoundException, SQLException;
}
