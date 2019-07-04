package com.lv.cloud.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lv.cloud.entity.UserOAuthEntity;


public interface IUserOAuth2Service extends IService<UserOAuthEntity> {

    UserOAuthEntity getUserOAuth2(String userName);
}
