package com.lv.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author LvNing
 * @since 2019-06-12
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableId(value = "id",type = IdType.INPUT)
    private String id;

    @TableId(value = "user_name", type = IdType.INPUT)
    private String userName;

    private Integer age;

    private String email;

    private String managerId;

    private LocalDateTime createTime;


}
