package com.lv.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lv.cloud.dao.UserDao;
import com.lv.cloud.entity.User;
import com.lv.cloud.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LvNing
 * @since 2019-06-12
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserDao userDao;

    /**
     * <p>
     * 根据实体列表批量更新
     * </p>
     *
     * @return Boolean
     */
    public boolean updateBatch(Collection<User> entityList) {

        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int i = 0;
            for (User user : entityList) {
                UserDao userMapper = batchSqlSession.getMapper(UserDao.class);
                UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper.eq("user_name", user.getUserName());
                int rows = userMapper.update(user, userUpdateWrapper);
                log.info("### 更新记录为：{}", rows);
                int defSize = 1000;
                if (i >= 1 && i % defSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();

        }
        return true;
    }

    /**
     * <p>
     * 根据实体列表批量保存
     * </p>
     *
     * @return Boolean
     */
    public boolean insertBatch(Collection<User> entityList) {
        return saveBatch(entityList);
    }


    public List<User> lambdaQuery(String userName) {

        return lambdaQuery()
                .eq(User::getUserName, userName)
                .list();

    }

    public List<User> queryAll() {

        return list();

    }

    public Boolean lambdaUpdate(Integer age) {

        return lambdaUpdate()
                .ge(User::getAge, 12)
                .set(User::getAge, age)
                .update();

    }


    public Boolean updateByWrapper(String userName) {

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", userName);

        return update(User.builder()
                .age(29)
                .build(), updateWrapper);

    }


    public Boolean updateByWrapperConstructor(String userName) {

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>(User.builder()
                .userName(userName)
                .build());

        return update(User.builder()
                .age(29)
                .build(), updateWrapper);

    }


    public Boolean updateByWrapperSignClu(String userName) {

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", userName).set("age", 19);
        return update(null, updateWrapper);

    }


    public Boolean updateByLambdaUpdateWrapper(String userName) {

        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(User::getUserName, userName).set(User::getAge, 22);

        return update(null, lambdaUpdateWrapper);

    }

    public Boolean updateByLambdaUpdateChainWrapper(String userName) {

        LambdaUpdateChainWrapper<User> userLambdaUpdateChainWrapper = new LambdaUpdateChainWrapper<>(userDao);
        return userLambdaUpdateChainWrapper
                .eq(User::getUserName, userName)
                .set(User::getAge, 22)
                .update();

    }

    @Override
    public IPage<User> queryUserByPage(int currentPage, int pageSize, String userName) {

        Page<User> userPage = new Page<>(currentPage, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", userName);
        return page(userPage, queryWrapper);
    }

    @Override
    public IPage<User> queryUserByPageNotUseCount(int currentPage, int pageSize, String userName) {
        Page<User> userPage = new Page<>(currentPage, pageSize, false);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", userName);
        return page(userPage, queryWrapper);
    }


}
