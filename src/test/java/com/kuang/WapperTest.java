package com.kuang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WapperTest {
    //继承了BassMapper
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询name不为空的一个用户,并且邮箱不为空的,年龄大于等于12岁的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);
        userMapper.selectList(wrapper).forEach(System.out::println);//和map对比一下

    }

    @Test
    void test2() {
        //查询名字等于狂神说的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "狂神说");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void test3() {
        //查询年龄在20-30岁之间
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);

    }

    @Test
    void test4() {
        //查询年龄在20-30岁之间
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.notLike("name", "y")
                .likeRight("email", "t");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);

    }
    @Test
    void test5() {
        //查询id<3的人
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.inSql("id", "select id from user where id<3");

        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }
    @Test
    void test6() {
        //查询年龄在20-30岁之间
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
