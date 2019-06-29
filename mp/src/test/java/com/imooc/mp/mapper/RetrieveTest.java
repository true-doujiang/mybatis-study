package com.imooc.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.imooc.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: -小野猪-
 * @Date: 2019-06-29 11:43
 * @Version: 1.0
 * @Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetrieveTest {


    @Autowired
    private UserMapper userMapper;


    @Test
    public void selectById() {
        User user = userMapper.selectById(1087982257332887553L);
        System.out.println("影响记录数：" + user);
    }

    @Test
    public void selectBatchIds() {
        // 查不到 返回一个空的list  不用担心空指针
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1087982257332887553L, 1088250446457389058L));
        users.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "张无忌");
        columnMap.put("age", 31);
        List<User> userList = userMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }

    /**
     * 条件构造器 AbstractWrapper
     */

    /**
     * 姓名中含雨并且年龄小于40
     * name like '%雨%' and age < 40
     */
    @Test
    public void selectByWarpper1() {
        // 条件构造器
        //QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper = Wrappers.query();
        // 默认就是 and 连接
        queryWrapper.like("name", "雨").lt("age", 40);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 姓名中含雨并且年龄大于等于20且小于等于40并且email不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void selectByWarpper2() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.like("name", "雨").between("age", 20, 40).isNotNull("email");

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 姓名为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * name like '王%' or age >= 25 order by age desc,id asc
     */
    @Test
    public void selectByWarpper3() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        // 或者 or()
        userQuery.likeRight("name", "王").or().ge("age", 25).orderByDesc("age").orderByAsc("id");

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 创建时间为2019年2月14日并且直属上级名字为王姓
     * date_format(create_time,'%Y-%m-%d') and manager_id in (select id from user where name like '王%')
     */
    @Test
    public void selectByWarpper4() {
        String date = "2019-02-14";

        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        // 此处写法存在sql注入问题，不建议使用
        userQuery.apply("date_format(create_time,'%Y-%m-%d')= '" + date + "' or true or true").inSql("manager_id", "select id from user where name like '王%'");
        //userQuery.apply("date_format(create_time,'%Y-%m-%d')={0}", date).inSql("manager_id", "select id from user where name like '王%'");

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age < 40 or email is not null)
     */
    @Test
    public void selectByWarpper5() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        // and(...) 中间放入 java8 Function
        userQuery.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者（年龄小于40并且大于20并且邮箱不为空）
     * name like '王%' or (age < 40 and age > 20 and email is not null)
     */
    @Test
    public void selectByWarpper6() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        // or(...) 中间放入 java8 Function
        userQuery.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * （年龄小于40或邮箱不为空）并且名字为王姓
     * (age < 40 or email is not null) and name like '王%'
     */
    @Test
    public void selectByWarpper7() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        // nested(...)
        userQuery.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 年龄为30,31,34,35
     * age in (30,31,34,35)
     */
    @Test
    public void selectByWarpper8() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.in("age", Arrays.asList(30, 31, 34, 35));

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    public void selectByWarpper9() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");   // 慎用 有sql注入风险

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 姓名中含雨并且年龄小于40(只返回id和name两列)
     * name like '%雨%' and age < 40
     */
     @Test
    public void selectByWarpper10_1() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        // select(要查询的字段名)
        userQuery.select("id", "name").like("name", "雨").lt("age", 40);

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 姓名中含雨并且年龄小于40(只返回id、name、age、email列)
     * name like '%雨%' and age < 40
     */
    @Test
    public void selectByWarpper10_2() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        // select 写在后面也可以， 排除要的列
        userQuery.like("name", "雨").lt("age", 40)
                .select(User.class, u -> !u.getColumn().equals("create_time") && !u.getColumn().equals("manager_id"));

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }


    /**
     * 多个查询参数，其中为空则不做条件
     */
    @Test
    public void testCondition() {
        String name = "王";
        String email = "";
        condition(name, email);
    }

    private void condition(String name, String email) {
        QueryWrapper<User> userQuery = new QueryWrapper<>();

        // if (StringUtils.isNotEmpty(name)) {
        // userQuery.like("name", name);
        // }
        // if (StringUtils.isNotEmpty(email)) {
        // userQuery.like("email", email);
        // }

        userQuery.like(StringUtils.isNotEmpty(name), "name", name)
                .like(StringUtils.isNotEmpty(email), "email", email);

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }


    @Test
    public void selectByWarpperEntity() {
        //场景: controller 传过来的实体类参数直接放入Wrapper中使用， 和下面加的条件不冲突
        User userWhere = new User();
        userWhere.setName("刘红雨");
        userWhere.setAge(32);

        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>(userWhere);
        // 这里也加name条件  最终sql name会出现2次
        //userQuery.like("name", "雨").lt("age", 40);

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWarpperAllEq() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王天风");
        params.put("age", null);

        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        // 第二个参数可选 是否忽略map空value为null的值，默认值 true => age is null   false=不会添加到查询条件>
        // userQuery.allEq(params, false);
        // k v都可以过滤   第三个参数同上
        userQuery.allEq((k, v) -> !k.equals("name"), params);

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWarpperMaps() {
        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.select("name", "age").like("name", "雨").lt("age", 40);

        List<Map<String, Object>> userList = userMapper.selectMaps(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。并且只取年龄总和小于500的组
     * select avg(age) avg_age, max(age) max_age, min(age) min_age from user
     * group by manager_id having sum(age) < 500
     */
    @Test
    public void selectByWarpper11() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.select("avg(age) avg_age", "max(age) max_age", "min(age) min_age, sum(age) sum_age, manager_id")
                .groupBy("manager_id").having("sum(age) < {0}", 500);

        List<Map<String, Object>> userList = userMapper.selectMaps(userQuery);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWarpperObjs() {
        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.select("id", "name").like("name", "雨").lt("age", 40);

        // 虽然select中2个字段，最终返回还是第一个字段
        List<Object> userList = userMapper.selectObjs(userQuery);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWarpperCount() {
        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.select("id").like("name", "雨").lt("age", 40);

        // 不用设置列，或者设置一列，多余一列会报错
        Integer count = userMapper.selectCount(userQuery);
        System.out.println("总记录数：" + count);
    }

     @Test
    public void selectByWarpperOne() {
        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.like("name", "刘红雨").lt("age", 40);

        // 查询多余一条会报错
        User user = userMapper.selectOne(userQuery);
        System.out.println(user);
    }

    /**
     *  Lambda 查询构造器
     */

    @Test
    public void selectLambda1() {
        // Lambda 条件构造器  3 种创建方式
        //LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper().lambda();
        //LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        // 可以防止写错字段
        lambdaQueryWrapper.like(User::getName, "雨").lt(User::getAge, 40);

        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age < 40 or email is not null)
     */
    @Test
    public void selectLambda2() {
        // 条件构造器
        LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.likeRight(User::getName, "王").and(age -> age.lt(User::getAge, 40).or().isNotNull(User::getEmail));

        List<User> userList = userMapper.selectList(lambdaQuery);
        userList.forEach(System.out::println);
    }

    /**
     * LambdaQueryChainWrapper
     */
    @Test
    public void selectLambda3() {
        // 条件构造器
        List<User> userList = new LambdaQueryChainWrapper<User>(userMapper).like(User::getName, "雨").ge(User::getAge, 20).list();
        userList.forEach(System.out::println);
    }

    /**
     * 自定义sql
     * mp版本大于3.0.7
     */
     @Test
    public void selectMy() {
        LambdaQueryWrapper<User> lambda = new QueryWrapper().lambda();
        lambda.likeRight(User::getName, "王").and(age -> age.lt(User::getAge, 40).or().isNotNull(User::getEmail));

        List<User> userList = userMapper.selectAll(lambda);
        userList.forEach(System.out::println);
    }

    /**
     *  分页查询 需要配置 PaginationInterceptor
     */
    @Test
    public void selectListPage() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.ge("age", 26);

        Page<User> page = new Page<>(1, 3);

        // 会发出2条sql
        IPage<User> userPage = userMapper.selectPage(page, userQuery);
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("总记录数：" + userPage.getTotal());

        List<User> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectMapsPage() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.ge("age", 26);

        Page<User> page = new Page<>(1, 3);

        IPage<Map<String, Object>> userPage = userMapper.selectMapsPage(page, userQuery);
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("总记录数：" + userPage.getTotal());

        List<Map<String, Object>> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectPageRecords() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.ge("age", 26);

        // 第三个参数 是否查询总记录数 false不查询
        Page<User> page = new Page<>(1, 3, false);

        IPage<Map<String, Object>> userPage = userMapper.selectMapsPage(page, userQuery);
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("总记录数：" + userPage.getTotal());

        List<Map<String, Object>> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

    /**
     * 自定义分页查询
     */
    @Test
    public void selecMypage() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.ge("age", 26);

        Page<User> page = new Page<>(1, 3);

        IPage<User> userPage = userMapper.selectUserPage(page, userQuery);
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("总记录数：" + userPage.getTotal());

        List<User> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }










}
