package com.lv.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lv.cloud.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LvNing
 * @since 2019-06-12
 */
public interface IUserService extends IService<User> {


    /**
     * <p>
     * 根据实体列表批量更新
     * </p>
     *
     * @return Boolean
     */
    boolean updateBatch(Collection<User> entityList);

    /**
     * <p>
     * 根据实体列表批量保存
     * </p>
     *
     * @return Boolean
     */
    boolean insertBatch(Collection<User> entityList);

    /**
     * <p>
     * lambda查询
     * </p>
     *
     * @return Boolean
     */
    public List<User> lambdaQuery(String userName);

    /**
     * <p>
     * lambda更新
     * </p>
     *
     * @return Boolean
     */
    public Boolean lambdaUpdate(Integer age);

    /**
     * <p>
     * 根据Wrapper更新
     * </p>
     *
     * @return Boolean
     */

    public Boolean updateByWrapper(String userName);

    /**
     * <p>
     * 根据Wrapper构造传参数更新
     * </p>
     *
     * @return Boolean
     */

    public Boolean updateByWrapperConstructor(String userName);

    /**
     * <p>
     * 使用Wrapper更新个别字段
     * </p>
     *
     * @return Boolean
     */
    public Boolean updateByWrapperSignClu(String userName);

    /**
     * <p>
     * 使用 LambdaUpdateWrapper
     * </p>
     *
     * @return Boolean
     */
    public Boolean updateByLambdaUpdateWrapper(String userName);

    /**
     * <p>
     * 使用 LambdaUpdateChainWrapper
     * </p>
     *
     * @return Boolean
     */
    public Boolean updateByLambdaUpdateChainWrapper(String userName);

    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @return Boolean
     */
    IPage<User> queryUserByPage(int currentPage, int pageSize, String userName);


    /**
     * <p>
     * 分页查询不带count语句
     * </p>
     *
     * @return Boolean
     */
    IPage<User> queryUserByPageNotUseCount(int currentPage, int pageSize, String userName);

    List<User> queryAll();

}
