package com.sqc95111.demologin.controller;

import com.sqc95111.demologin.util.RandomValidateCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: sqc
 * @Descirption:
 * @Date: Create in 15:27 2018/8/22
 * @Modified By:sqc95
 */
@Api(tags = "登录部分",description = "用户获取随机验证码")
@RestController
public class PicVerifyController {
    private static final Logger logger = LoggerFactory.getLogger(RandomValidateCodeUtil.class);

    /**
     * 生成验证码
     */
    @ApiOperation(value = "获取验证码")
        @GetMapping(value = "/getVerify",produces = MediaType.IMAGE_JPEG_VALUE)
        public void getVerify(HttpServletRequest request, HttpServletResponse response) {
            try {
                response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
                response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expire", 0);
                RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
                randomValidateCode.getRandcode(request, response);//输出验证码图片方法
            } catch (Exception e) {
                logger.error("获取验证码失败>>>>   ", e);
            }
    }



}
