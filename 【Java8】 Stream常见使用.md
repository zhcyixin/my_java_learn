## 【Java8】 Stream常见使用 
### Stream 基本介绍 
1、Java 8中提供了一个新的附加包，名为Java.util.stream。这个包由类、接口和枚举组成，允许对元素进行函数式操作,您可以使用stream来过滤、收集、打印和从一个数据结构转换到另一个;
2、Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程效率和程序可读性。
### Stream使用好处 
1、函数式编程，提高代码开发效率和简洁性；
2、提供多种对集合数据的过滤、聚合、转换操作，比较方便；
3、将一些sql中复杂数据统计放到服务处理，提升性能

### Stream使用缺点
1、团队开发习惯如果不常用，可能会影响代码易读性；
2、使用stream编写代码，不容易排错和调试。

---
### 常见使用示例 
**1、基本数据对象定义**
```
//定义一个UserInfo类，包含userName、age、sex字段
@Data
public class UserInfo {
    private String userName;
    private Integer age;
    private String sex;
}
 /**
  * 构造测试数据
  * @return
*/
public static List<UserInfo> builderData(){
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
```
**2、Stream的创建方法：**
① 通过 java.util.Collection.stream() 方法用集合创建流

``` 
List<String> list = Arrays.asList("learn","Java8","stream"); 
// 创建顺序流
Stream<String> stream = list.stream(); 
// 创建并行流
Stream<String> parallelStream = list.parallelStream(); 
```
② 使用java.util.Arrays.stream(T[] array)方法用数组创建流

```
String[] arrays = {"a", "b", "c", "d", "e"};
Stream<String> arrayStream = Arrays.stream(arrays);
```
③ Stream的静态方法：of()、iterate()、generate()
```
Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6);
Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(3);
stream2.forEach(System.out::println);
Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
stream3.forEach(System.out::println)
```
**3、Stream数据过滤操作**
filter：筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作
```
List<UserInfo> userInfoList = builderData();
        userInfoList.stream().filter(o -> o.getAge() > 20).forEach(System.out::println);
```

**4、映射操作（map、flatMap、peek)**

① map 一个元素类型为 T 的流转换成元素类型为 R 的流，这个方法传入一个Function的函数式接口，接收一个泛型T，返回泛型R，map函数的定义，返回的流，表示的泛型是R对象；

```
userInfoList.stream().map(UserInfo::getAge).forEach(System.out::println);
```
② flatMap 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
总之：与Map功能类似，区别在于将结合A的流转换成B流；
```
List<String> list1 = Arrays.asList("h,e,l,l", "1,2,3,4");
        List<String> list2 = list1.stream().flatMap(o -> {
            String[] split  = o.split(",");
            return Arrays.stream(split);
        }).collect(Collectors.toList());
        System.out.println("处理前：" + list1);
        System.out.println("处理后：" + list2);
```
③ peek  操作 一般用于***不想改变流中元素本身***的类型或者只想元素的内部状态时；

而 map 则用于改变流中元素本身类型，即从元素中派生出另一种类型的操作。
```  
Stream<String> stream = Stream.of("hello", "felord.cn");
stream.peek(System.out::println).collect(Collectors.toList());
```
④ 另外还有mapToInt、mapToLong、mapToDouble、flatMapToDouble、flatMapToInt、flatMapToLong

以上这些操作是map和flatMap的特例版，也就是针对特定的数据类型进行映射处理
**5、去重、排序、获取指定个数操作**
① distinct：返回由该流的不同元素组成的流,
<font color = 'red'>
1、根据 Object.equals(Object)），distinct 使用hashCode（）和equals（）方法获取不同元素。<br>2、我们的类必须实现hashCode（）和equals（）方法
</font>

② sorted:返回由该流的元素组成的流，并根据自然顺序排序,该接口有两种形式：无参和有参数，如:
```
Stream<T> sorted();
Stream<T> sorted(Comparator<? super T> comparator);
```
<font color='red'>区别：传入比较器的参数，可以自定义这个比较器，即自定义比较规则。</font>
③ limit：获取流中n个元素返回的流
```
List<UserInfo> userInfoList = builderData();
        userInfoList.stream().limit(3).forEach(System.out::println);
```
**6、anyMatch:、allMatch、noneMatch、findFirst、findAny**
① anyMatch:Stream 中只要有一个元素符合传入的 predicate，返回 true;
boolean anyMatch(Predicate<? super T> predicate);
② allMatch：Stream 中全部元素符合传入的 predicate，返回 true;
boolean allMatch(Predicate<? super T> predicate);
③ noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true.
boolean noneMatch(Predicate<? super T> predicate);
④ findFirst：用于返回满足条件的第一个元素（但是该元素是封装在Optional类中）
Optional<T> findFirst();
⑤ findAny：返回流中的任意元素（但是该元素也是封装在Optional类中）
Optional<T> findAny();
<font color='red'>
findAny()操作，返回的元素是不确定的,使用findAny()是为了更高效的性能
</font>

```
// 以上操作使用示例
List<UserInfo> userInfoList = builderData();
        boolean anyMatch = userInfoList.stream().anyMatch(o -> o.getAge() >18);
        boolean allMatch = userInfoList.stream().allMatch(o -> o.getAge() >18);
        boolean noneMatch = userInfoList.stream().noneMatch(o -> o.getAge() >18);
        System.out.println(anyMatch+","+allMatch+","+noneMatch);
        userInfoList.stream().filter(o -> o.getAge() > 35).findFirst().ifPresent(System.out::println);
        System.out.println(userInfoList.stream().filter(o -> o.getAge() > 20).findAny().get());
```
**7、foreach、forEachOrdered**
注意里面的变量必须为final申明，因为lambda中，使用的外部变量必须是最终的，不可变的
① forEach：该方法接收一个Lambda表达式，然后在Stream的每一个元素上执行该表达式
```void forEach(Consumer<? super T> action); ```
② forEachOrdered：该方法接收一个Lambda表达式，然后按顺序在Stream的每一个元素上执行该表达式，并发环境下仍然不能保证顺序
```void forEachOrdered(Consumer<? super T> action);```
③ reduce：方法接收一个函数作为累加器，数组中的每个值（从左到右）开始缩减，最终计算为一个值
**8、终止操作collect：称为收集器，是一个终端操作,它接收的参数是将流中的元素累积到汇总结果的各种方式**
|方法	|含义说明|
|  ----  | :---  |
|toList	|将流中的元素收集到一个List中|
|toSet	|将流中的元素收集到一个Set中|
|toCollection	|将流中的元素收集到一个Collection中|
|toMap	|将流中的元素映射收集到一个Map中|
|counting	|统计流中的元素个数|
|summingInt	|计算流中指定int字段的累加总和。针对不同类型的数字类型，有不同的方法，比如summingDouble等|
|averagingInt	|计算流中指定int字段的平均值。针对不同类型的数字类型，有不同的方法，比如averagingLong等|
|joining	|将流中所有元素（或者元素的指定字段）字符串值进行拼接，可以指定拼接连接符，或者首尾拼接字符|
|maxBy	|根据给定的比较器，选择出值最大的元素|
|minBy	|根据给定的比较器，选择出值最小的元素|
|groupingBy	|根据给定的分组函数的值进行分组，输出一个Map对象|
|partitioningBy	|根据给定的分区函数的值进行分区，输出一个Map对象，且key始终为布尔值类型|
|collectingAndThen	|包裹另一个收集器，对其结果进行二次加工转换|
|reducing	|从给定的初始值开始，将元素进行逐个的处理，最终将所有元素计算为最终的1个值输出|

下面对几个容易混淆的进行举例使用

① partitioningBy 使用，将数据按照条件分成两组

```
Map<Boolean, List<UserInfo>> booleanListMap = userInfoList.stream().collect(Collectors.partitioningBy(item -> item.getAge() > 27));
```

② collectingAndThen 使用，包裹收集器

```
Integer maxAge = userInfoList.stream().collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(UserInfo::getAge)), Optional::get)).getAge();
```
③ reducing使用
```
userInfoList.stream().map(o -> new BigDecimal(String.valueOf(o.getAge()))).collect(Collectors.reducing(BigDecimal.ZERO,BigDecimal::add));
```