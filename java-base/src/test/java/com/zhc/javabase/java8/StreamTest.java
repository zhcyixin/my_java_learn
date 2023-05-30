package com.zhc.javabase.java8;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    private final List<User> userList = new ArrayList<>();

    /**
     * 定义一个User类，包含name、age、sex、courseList四个属性
     */
    @Data
    @Builder
    static class User{
        private String name;
        private Integer age;
        private String sex;
        private List<String> courseList;
    }

    /**
     * 构建基础数据
     */
    @BeforeEach
    void initBaseData(){
        userList.add(User.builder().name("小红").age(18).sex("女").courseList(Arrays.asList("语文","数学")).build());
        userList.add(User.builder().name("小丽").age(19).sex("女").courseList(Arrays.asList("体育","英语")).build());
        userList.add(User.builder().name("小华").age(20).sex("男").courseList(Arrays.asList("体育","化学")).build());
        userList.add(User.builder().name("小花").age(18).sex("女").courseList(Arrays.asList("美术","物理")).build());
        userList.add(User.builder().name("小军").age(23).sex("男").courseList(Arrays.asList("体育","化学")).build());
        userList.add(User.builder().name("小明").age(27).sex("男").courseList(Arrays.asList("生物","音乐")).build());
    }

    /**
     * 在使用stream api之前必须先创建stream,
     * 如下介绍三种创建stream的方法
     */
    @Test
    void testCreateStream(){
        List<String> list = Arrays.asList("a", "b", "c");
        // 1、通过java.util.Collection.stream()方法
        // 创建一个顺序流
        Stream<String> stream = list.stream();
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();

        // 2、使用java.util.Arrays.stream(T[] array)方法用数组创建流
        Stream<String> arrayStream = Arrays.stream(new String[list.size()]);

        //3、使用Stream的静态方法：of()、iterate()、generate()
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
    }

    /**
     * 从userList中找出年龄大于20岁，性别是男生的数据，并打印出来
     */
    @Test
    void testFilter(){
        // 传统方法，只有通过for循环加if判断来处理
        for(User user : userList){
            if(user.getAge() > 20 && Objects.equals(user.getSex(),"男")){
                System.out.println(user);
            }
        }
        // 通过stream api使用filter只需一行代码就可以实现
        userList.stream().filter(item -> item.getAge() > 20 && Objects.equals(item.getSex(),"男"))
                .forEach(System.out::println);
        // 通过stream api将过滤的数据生成一个新的集合
        userList.stream().filter(item -> item.getAge() > 20 && Objects.equals(item.getSex(),"男")).collect(Collectors.toList());
    }

    /**
     * 遍历/匹配数据，使用foreach、find、match
     */
    @Test
    void testForeachAndFindMatch(){
        // 传统方法，只有通过for循环逐个遍历并判断
        // 使用stream api操作就比较简单，通过foreach、find、match方法
        /**
         * 查询性别为男的数据并打印输出
         */
        userList.stream().filter(o -> Objects.equals(o.getSex(),"男")).forEach(System.out::println);
        /**
         * 匹配满足条件的第一个,findFirst
         */
        String name = userList.stream().filter(o -> o.getAge() > 18).map(User::getName).findFirst().orElse("无名氏");
        System.out.println("满足条件的第一个用户姓名为:"+name);
        /**
         * 匹配任意一个，使用findAny,常用于并行流，提高效率
         */
        Optional<String> findAny = userList.parallelStream().filter(o -> o.getAge() > 18).map(User::getName).findAny();
        System.out.println("满足条件任意一个用户姓名为:"+ findAny.get());
        findAny.ifPresent(item -> System.out.println("满足条件任意一个用户姓名为:"+ item));
        /**
         * 是否有一个满足
         */
        boolean isAnyMatch = userList.parallelStream().anyMatch(o -> o.getAge() > 18);
        System.out.println("是否有一个满足:"+isAnyMatch);
        /**
         * 是否全部满足
         */
        boolean isAllMatch = userList.parallelStream().allMatch(o -> o.getAge() > 18);
        System.out.println("是否全部满足:"+isAllMatch);
    }
    /**
     * stream 聚合操作，最大、最小、求和等操作
     * max、min、count
     */
    @Test
    void testAggregation(){
        /**
         * 找出年龄最大的用户
         */
        Optional<User> maxAge = userList.stream().max(Comparator.comparing(User::getAge));
        System.out.println("年龄最大用户为:"+maxAge.get());
        /**
         * 找出年龄最小的用户
         */
        Optional<User> minAge = userList.stream().min(Comparator.comparing(User::getAge));
        Optional<User> minAge1 = userList.stream().min(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getAge().compareTo(o1.getAge());
            }
        });
        System.out.println("年龄最小用户为:"+minAge);
        System.out.println("年龄最小用户为:"+minAge1);
        /**
         * 大于18岁的人数
         */
        Long moreThan18 = userList.stream().filter(o -> o.getAge() > 18).count();
        System.out.println("大于18岁的人数:"+moreThan18);
    }
    /**
     * stream 映射操作，map、flatMap
     */
    @Test
    void testMapAndFlatMap(){
        /**
         * 将userList中所有用户，姓名都取出来,并打印
         */
        userList.stream().map(User::getName).forEach(System.out::println);
        /**
         * 将每个用户年龄加3岁，并打印
         */
        userList.stream().map(o -> {
           o.setAge(o.getAge() + 3);
           return o;
        }).forEach(System.out::println);
        /**
         * 使用flatMap将用户课程合并
         * 如果使用map，获取的数据就是嵌套的list
         */
        userList.stream().map(User::getCourseList).forEach(System.out::println);
        userList.stream().flatMap(o -> o.getCourseList().stream()).forEach(System.out::println);
    }

    /**
     * 归约操作，
     */
    @Test
    void testReduce(){
        /**
         * 将下面的数组元素求和、求乘积、最大、最小
         * reduce
         */
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list.stream().reduce(Integer::sum);
        // 求和方式2,赋初值
        Integer sum1 = list.stream().reduce(0, Integer::sum);
        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);
        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list.stream().reduce(1, Integer::max);
        System.out.println("list求和：" + sum.get() + "," + sum1);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);
    }
    /**
     * 收集操作，collect
     */
    @Test
    void testCollect(){
        /**
         * 将userList 姓名取出来转为list
         */
        userList.stream().map(User::getName).collect(Collectors.toList()).forEach(System.out::println);
        /**
         * 将userList 的name作为key，sex作为value转为map
         */
        userList.stream().collect(Collectors.toMap(User::getName,p->p.sex))
                .forEach((k,v)-> System.out.println("key:"+k+",value:"+v));
        /**
         * 将userList的sex取出来转为set,可以达到去重效果
         */
        userList.stream().map(User::getSex).collect(Collectors.toSet()).forEach(System.out::println);
        /**
         * toCollection 转为指定类型的集合
         */
        userList.stream().collect(Collectors.toConcurrentMap(User::getName,p->p));
        /**
         * 包裹收集器，对归纳的结果进行二次处理
         * 按照性别去重，并转为List
         */
        userList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(User::getSex))),ArrayList::new));
        /**
         * 按照性别、年龄去重
         */
        userList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(o -> o.getSex()+";"+o.getAge()))),ArrayList::new));
        /**
         * 查找年龄最大的用户的姓名
         */
        userList.stream().collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(User::getAge)),
                        (Optional<User> user)-> user.map(User::getName).orElse(null)));


        /**
         * 将姓名按照逗号分隔
         */
        userList.stream().map(User::getName).collect(Collectors.joining(","));

        /**
         * userList按照性别分组，并统计数量
         */
        userList.stream().collect(Collectors.groupingBy(User::getSex,Collectors.counting()));
        /**
         * 将用户大于20岁的和小于20岁的分组
         */
        Map<Boolean, List<User>> partitionGroup = userList.stream().collect(Collectors.partitioningBy(o -> o.getAge() > 20));
        /**
         * 统计相关
         */
        // 求userList总数
        userList.stream().collect(Collectors.counting());
        // 求userList平均年龄
        userList.stream().collect(Collectors.averagingDouble(User::getAge));
        // 求年龄之和
        userList.stream().collect(Collectors.summarizingInt(User::getAge));
        // 求最大年龄
        userList.stream().map(User::getAge).collect(Collectors.maxBy(Integer::compare));
        /**
         * 归约相关,类似于stream的reduce功能
         */
        userList.stream().collect(Collectors.reducing(0,User::getAge,(x,y)->(x+y)));
    }

    /**
     * 排序功能、去重、提取
     */
    @Test
    void testSort(){
        // 按照年龄升序
        userList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
        // 按照年龄降序
        userList.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
        // 按照年龄去重
        userList.stream().map(User::getAge).distinct();
        // 取前面2行记录
        userList.stream().limit(2);
        // 忽略前面3个
        userList.stream().skip(3).collect(Collectors.toList());
    }
}
