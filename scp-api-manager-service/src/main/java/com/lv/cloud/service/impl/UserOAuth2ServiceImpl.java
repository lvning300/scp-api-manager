package com.lv.cloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lv.cloud.dao.OAuth2Dao;
import com.lv.cloud.entity.UserOAuthEntity;
import com.lv.cloud.service.IUserOAuth2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserOAuth2ServiceImpl extends ServiceImpl<OAuth2Dao, UserOAuthEntity> implements IUserOAuth2Service {


    @Override
    public UserOAuthEntity getUserOAuth2(String userName) {
        try {
            List<UserOAuthEntity> list = lambdaQuery()
                    .eq(UserOAuthEntity::getUsername, userName)
                    .list();
            if (list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
