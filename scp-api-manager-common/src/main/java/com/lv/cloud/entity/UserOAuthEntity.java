package com.lv.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@TableName("user_oauth2")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserOAuthEntity implements Serializable {

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;


}
