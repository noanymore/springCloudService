package com.sqc95111.demologin.model.BO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/2/26 15:32
 * @Version 1.0
 */
@Data
public class RegisterUserBO {

    @Email
    @NotBlank
    @ApiModelProperty(value = "邮箱",example = "sqc95111@163.com")
    private String mail;
    @NotBlank
    @ApiModelProperty(value = "密码",example = "XXXXXXXXX")
    private String password;
    @NotBlank
    @ApiModelProperty(value = "验证码",example = "pcg7")
    private String securityCode;
}
