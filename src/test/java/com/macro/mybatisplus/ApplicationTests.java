package com.macro.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mybatisplus.entity.User;
import com.macro.mybatisplus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testLambda(){
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId,26L));
        System.out.println(user);
        List<User> userList= userMapper.selectList(new QueryWrapper<User>().lambda().ge(User::getId,20L));
        System.out.println(userList);
    }
    @Test
    public void testVersion(){
        User user = new User();
        user.setId(24L);
        user.setAge(19);
        user.setVersion(0);//注意，这里必须要先设置值mybatisplus才能帮我们做乐观锁
        user.setEmail("gaowenli@163.com");
        user.setName("高文丽111");
        userMapper.updateById(user);
        //user.updateById();
    }
    @Test
    public void testAR(){
        User user = new User();
        user.setId(27L);
        user.setAge(19);
        user.setEmail("gaowenli@163.com");
        user.setName("高文丽");
        user.updateById();
//        boolean r = user.insert();
//        Assert.assertTrue(r);
//        Assert.assertEquals(user,user.selectById(20));
//        Assert.assertTrue(user.deleteById());
//        Assert.assertNull(user.selectById());
    }
    @Test
    public void testWapper(){
        Page<User> list = (Page<User>) userMapper.selectPage(new Page<User>(0, 2),
                new QueryWrapper<User>()
                    .eq("name", "苏宏")
                    .between("age",26,50)
        );
        System.out.println(list.getRecords());
    }
    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        User selectJone = userMapper.selectById(1);
        User jone = new User();
        jone.setAge(18);
        jone.setEmail("test1@baomidou.com");
        jone.setId(1L);
        jone.setName("Jone");
        Assert.assertEquals(jone,selectJone);
    }
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("高文值");
        user.setEmail("gaowenzhi@163.com");
        user.setAge(22);
        int resut = userMapper.insert(user);
//        for(int i = 1;i<10;i++){
//            user.setAge(user.getAge() + i);
//            userMapper.insert(user);
//            System.out.println(user.getId());
//        }
        Assert.assertTrue(resut == 1);
    }
    @Test
    public void testDelete(){
        int r = userMapper.deleteById(10L);
    }
}
