package com.zhc.javabase.java8;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream api 使用示例
 * @author zhouhengchao
 * @date 2022-11-25 11:01:00
 * @version 1.0
 */
// 使用stream 好处：
// 1、高效率并行操作
// 2、多种功能性集合聚合操作
// 3、函数式编程，代码简洁高效
@Service
public class StreamDemo {

    private List<Integer> dataList = Arrays.asList(1,6,8,11,4,9,12,6);

    @Data
    @Builder
    static class UserInfo{
        private Integer age;
        private Integer idCard;
        private String name;
        private String memo;

    }

    public void createStream(){
        Stream<Integer> stream = dataList.stream();
        Stream<Integer> parallelStream = dataList.parallelStream();
        Stream<Integer> a = Arrays.stream(dataList.toArray(new Integer[dataList.size()]));
        Stream<Integer> b = Stream.of(dataList.toArray(new Integer[dataList.size()]));
        Stream.iterate(0 , o -> o +1).limit(5).forEach(System.out::println);
    }

    public void streamFilter(){
        List<UserInfo> userInfoList = builderData();
        userInfoList.stream().filter(o -> o.getAge() > 20).forEach(System.out::println);
    }

    public void streamMap(){
        List<UserInfo> userInfoList = builderData();
        userInfoList.stream().map(UserInfo::getAge).forEach(System.out::println);
        Stream.of(1,2).map(new Function<Integer, Integer>(){
            @Override
            public Integer apply(Integer param){
                return param + 2;
            }
        }).forEach(System.out::println);
    }

    public void streamFlatMap(){
        List<String> list1 = Arrays.asList("h,e,l,l", "1,2,3,4");
        List<String> list2 = list1.stream().flatMap(o -> {
            String[] split  = o.split(",");
            return Arrays.stream(split);
        }).collect(Collectors.toList());
        System.out.println("处理前：" + list1);
        System.out.println("处理后：" + list2);
    }

    public void streamSorted(){
        List<UserInfo> userInfoList = builderData();
        userInfoList.stream().sorted(Comparator.comparing(UserInfo::getAge).reversed()).forEach(System.out::println);
    }

    public void streamLimit(){
        List<UserInfo> userInfoList = builderData();
        userInfoList.stream().limit(3).forEach(System.out::println);
    }

    public void streamMatch(){
        List<UserInfo> userInfoList = builderData();
        boolean anyMatch = userInfoList.stream().anyMatch(o -> o.getAge() >18);
        boolean allMatch = userInfoList.stream().allMatch(o -> o.getAge() >18);
        boolean noneMatch = userInfoList.stream().noneMatch(o -> o.getAge() >18);
        System.out.println(anyMatch+","+allMatch+","+noneMatch);
        userInfoList.stream().filter(o -> o.getAge() > 35).findFirst().ifPresent(System.out::println);
        System.out.println(userInfoList.stream().filter(o -> o.getAge() > 20).findAny().get());
    }

    public void streamCollect(){
        List<UserInfo> userInfoList = builderData();
        List<UserInfo> list1 = userInfoList.stream().collect(Collectors.toList());
        Map<String,Integer> map = userInfoList.stream().collect(Collectors.toMap(UserInfo::getName, o->o.idCard,(k1, k2)->k1));
        map.forEach((k,v)->{
            System.out.println("k:"+k+",v:"+v);
        });
        System.out.println(userInfoList.stream().collect(Collectors.averagingInt(UserInfo::getAge)));

        Map<String,Double> map1 = userInfoList.stream().collect(Collectors.groupingBy(UserInfo::getName, Collectors.averagingLong(UserInfo::getAge)));

        TreeMap<String,Double> map2 = userInfoList.stream().collect(Collectors.groupingBy(UserInfo::getName,TreeMap::new ,Collectors.averagingLong(UserInfo::getAge)));

        Integer sum = userInfoList.stream().mapToInt(UserInfo::getAge).sum();
        Optional<UserInfo> optionalUserInfo = userInfoList.stream().max(Comparator.comparing(UserInfo::getAge));

        Integer maxAge = userInfoList.stream().collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(UserInfo::getAge)), Optional::get)).getAge();
        optionalUserInfo.ifPresent(o-> System.out.println("Collect maxBy is :" + o.getAge()));

        Map<Boolean, List<UserInfo>> booleanListMap = userInfoList.stream().collect(Collectors.partitioningBy(item -> item.getAge() > 27));

        userInfoList.stream().map(o -> new BigDecimal(String.valueOf(o.getAge()))).collect(Collectors.reducing(BigDecimal.ZERO,BigDecimal::add));
    }

    public void streamJoin(){
        List<UserInfo> userInfoList = builderData();
        String nameStr1 = userInfoList.stream().map(UserInfo::getName).collect(Collectors.joining("-"));
        String nameStr2 = userInfoList.stream().map(UserInfo::getName).collect(Collectors.joining("-","{","}"));
        System.out.println(nameStr1);
        System.out.println(nameStr2);
    }

    public void streamReduce(){
        List<UserInfo> userInfoList = builderData();
        Integer sum = userInfoList.stream().map(UserInfo::getAge).reduce(0, Integer::sum);
        System.out.println(sum);
    }

    /**
     * stream 多字段去重
     */
    public void streamCollectAndThen(){
        List<UserInfo> userInfoList = builderData();
        userInfoList = userInfoList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(o -> o.getName() + ";" + o.getAge()))), ArrayList::new));
        System.out.println(userInfoList);
    }

    /**
     * 构造测试数据
     * @return
     */
    private List<UserInfo> builderData(){
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        userInfoList.add(UserInfo.builder().age(18).idCard(123456).name("小红").memo("备注").build());
        userInfoList.add(UserInfo.builder().age(16).idCard(13456).name("小军").memo("备注1").build());
        userInfoList.add(UserInfo.builder().age(20).idCard(123456).name("小花").memo("备注2").build());
        userInfoList.add(UserInfo.builder().age(18).idCard(123456).name("小容").memo("备注3").build());
        userInfoList.add(UserInfo.builder().age(18).idCard(123456).name("小容").memo("备注6").build());
        userInfoList.add(UserInfo.builder().age(26).idCard(12346).name("小海").memo("备注4").build());
        userInfoList.add(UserInfo.builder().age(26).idCard(12346).name("小海").memo("备注5").build());
        return userInfoList;
    }



}
