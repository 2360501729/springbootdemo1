package com.lcl.pname.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lcl.pname.entity.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Getter
@Setter
@ToString
@TableName("acl_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final String USERNAME = "username";

    /**
     * openid
     */
    @NotNull //可以在 controller 处理接口中为 类型为该类的参数前使用@Avalied开启校验.
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户头像
     */
    @TableField("salt")
    private String salt;

    /**
     * 用户签名
     */
    @TableField("token")
    private String token;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
