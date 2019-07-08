package com.sqc.gre.english;

import lombok.Data;

/**
 * @author < a href="sqc95111@163.com">sqc</ a>
 * @version 1.0
 * @ClassName YouDaoResult
 * @Description TODO
 * @date 2019/4/28 7:50
 **/
@Data
public class YouDaoResult {
    private String tSpeakUrl;
    private Object returnPhrase;
    private Object web;
    private Object query;
    private Object trabslation;
    private String errorCode;
    private Object dict;
    private Object webdict;
    private Basic basic;

    @Data
    public class Basic {
        private Object exam_type;
        private Object explains;
    }
}
