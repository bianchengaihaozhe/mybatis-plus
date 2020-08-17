package com.kuang;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    //继承了BassMapper
    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //插入测试
    @Test
    public void testinsert() {
        User user = new User();
        user.setName("狂胜说java");
        user.setEmail("12323@163.com");

        int insert = userMapper.insert(user);

        System.out.println(insert);
        System.out.println(user);
    }

    //测试更新
    @Test
    public void testupdate() {

        User user = new User();
        user.setId(5L);
        user.setName("大家好才是真的好");
        user.setAge(66);
        int i = userMapper.updateById(user);
        System.out.println(i);

    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker() {
        //查询用户信息
        User user = userMapper.selectById(1L);
        //修改用户信息
        user.setName("狂神");
        user.setEmail("11111");
        //执行跟新操作
        userMapper.updateById(user);


    }

    //测试查询
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //测试批量查询
    @Test
    public void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1., 2, 3));
        users.forEach(System.out::println);
    }

    //条件查询一般使用map
    @Test
    public void testSelectByBatchIds() {
        HashMap<String, Object> map = new HashMap<>();
        //自定义查询
        //按照条件查询

        map.put("name", "狂神");
        map.put("age", 3);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    //测试分页查询
    public void testPage() {
        //参数一:当前页
        //参数二:页面大小
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        //获得总数
        System.out.println(page.getTotal());

    }

    @Test
    //测试删除
    public void  testDeleteById(){
        userMapper.deleteById(1L);
    }
    @Test
    //测试批量删除
    public void  testDeleteByBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1287638913040633857L,1287638913040633858L));
    }
    @Test
    //测试条件删除
    public void  testDeleteMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","狂胜说java");
        userMapper.deleteByMap(map);
    }


}
