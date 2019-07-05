package com.lv.cloud.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lv.cloud.UserServiceFeignClient;
import com.lv.cloud.entity.User;
import com.lv.cloud.entity.UserOAuthEntity;
import com.lv.cloud.service.IUserOAuth2Service;
import com.lv.cloud.service.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LvNing
 * @since 2019-06-12
 */
/*@RestController
@RequestMapping("/api/v1/user")*/
@RestController
@Slf4j
public class UserController implements UserServiceFeignClient {

    @Autowired
    IUserService userService;

    @Autowired
    IUserOAuth2Service userOAuth2Service;

    @HystrixCommand
    @ApiOperation(value = "查询所有用户信息", response = User.class)
    @Override
    public List<User> queryAll() {
       /* return userService.queryAll().stream()
                .map(rs -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(rs, userDTO);
                    return userDTO;
                }).collect(Collectors.toList());*/

        return userService.queryAll();

    }


    @ApiOperation(value = "查询所有用户信息", response = User.class)
    @GetMapping(value = "/list/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> lambdaQuery(@ApiParam(name = "userName", value = "用户名")
                                  @PathVariable(name = "userName") String userName) {

        return userService.lambdaQuery(userName);

    }

    @ApiOperation(value = "更新年龄", response = Boolean.class)
    @PutMapping(value = "/update/{age}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean lambdaUpdate(@ApiParam(name = "age", value = "年龄")
                                @PathVariable(name = "age") Integer age) {

        return userService.lambdaUpdate(age);

    }

    @ApiOperation(value = "批量更新", response = Boolean.class)
    @PutMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateBatch(@ApiParam(name = "userList", value = "需要更新大用户列表")
                               @RequestBody List<User> userList) {

        return userService.updateBatch(userList);

    }

    @ApiOperation(value = "使用Wrapper根据用户名修改", response = Boolean.class)
    @PutMapping(value = "/batch/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateByWrapper(@ApiParam(name = "userName", value = "用户名")
                                   @PathVariable(value = "userName") String userName) {
        return userService.updateByWrapper(userName);

    }


    @ApiOperation(value = "使用WrapperConstructor更新", response = Boolean.class)
    @PutMapping(value = "/wrapper/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateByWrapperConstructor(@ApiParam(name = "userName", value = "用户名")
                                              @PathVariable(value = "userName") String userName) {

        return userService.updateByWrapperConstructor(userName);

    }


    @ApiOperation(value = "使用Wrapper更新单个字段", response = Boolean.class)
    @PutMapping(value = "/wrapper/sign/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateByWrapperSignClu(@ApiParam(name = "userName", value = "用户名")
                                          @PathVariable(value = "userName") String userName) {

        return userService.updateByWrapperSignClu(userName);

    }

    @ApiOperation(value = "使用LambdaUpdateWrapper更新", response = Boolean.class)
    @PutMapping(value = "/lambda-wrapper/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateByLambdaUpdateWrapper(@ApiParam(name = "userName", value = "用户名")
                                               @PathVariable(value = "userName") String userName) {


        return userService.updateByLambdaUpdateWrapper(userName);

    }

    @ApiOperation(value = "使用LambdaUpdateChainWrapper更新", response = Boolean.class)
    @PutMapping(value = "/lambda-chain/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateByLambdaUpdateChainWrapper(@ApiParam(name = "userName", value = "用户名")
                                                    @PathVariable(value = "userName") String userName) {


        return userService.updateByLambdaUpdateChainWrapper(userName);

    }

    @ApiOperation(value = "分页查询用户列表", response = Boolean.class)
    @GetMapping(value = "/page/{userName}/{currentPage}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IPage<User> queryUserByPage(@ApiParam(name = "userName", value = "用户名")
                                       @PathVariable(value = "userName") String userName,
                                       @ApiParam(name = "currentPage", value = "当前页")
                                       @PathVariable(value = "currentPage") Integer currentPage,
                                       @ApiParam(name = "pageSize", value = "每页条数")
                                       @PathVariable(value = "pageSize") Integer pageSize) {


        return userService.queryUserByPage(currentPage, pageSize, userName);

    }


    @ApiOperation(value = "分页查询用户列表,没有Count查询", response = Boolean.class)
    @GetMapping(value = "/page/no-count/{userName}/{currentPage}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IPage<User> queryUserByPageNoCount(@ApiParam(name = "userName", value = "用户名")
                                              @PathVariable(value = "userName") String userName,
                                              @ApiParam(name = "currentPage", value = "当前页")
                                              @PathVariable(value = "currentPage") Integer currentPage,
                                              @ApiParam(name = "pageSize", value = "每页条数")
                                              @PathVariable(value = "pageSize") Integer pageSize) {


        return userService.queryUserByPageNotUseCount(currentPage, pageSize, userName);

    }

    @ApiOperation(value = "查询OAuth2用户列表", response = Boolean.class)
    @GetMapping(value = "/oauth2/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserOAuthEntity queryUserOAuth2(@ApiParam(name = "userName", value = "用户名")
                                           @PathVariable(value = "userName") String userName) {


        return userOAuth2Service.getUserOAuth2(userName);

    }


}
