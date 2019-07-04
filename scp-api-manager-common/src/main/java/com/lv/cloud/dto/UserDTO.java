package com.lv.cloud.dto;

import lombok.*;
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
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userName;

    private Integer age;

    private String email;

    private String managerId;

    private LocalDateTime createTime;


}
