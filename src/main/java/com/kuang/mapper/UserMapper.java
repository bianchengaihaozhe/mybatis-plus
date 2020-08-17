package com.kuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//代表是持久层
public interface UserMapper extends BaseMapper<User> {


}
