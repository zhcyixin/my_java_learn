package com.zhc.javabase.java8;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * java8 日期使用
 * @author zhouhengchao
 * @date 2022-12-20 20:12:00
 * @version 1.0
 */
@Service
public class LocalDateDemo {

    public void localDateTimeUse(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("本地当前日期时间："+localDateTime);
        System.out.println("当前时间年:"+localDateTime.getYear());
        System.out.println("当前时间月:"+localDateTime.getMonth().getValue());
        System.out.println("当前时间周:"+localDateTime.getDayOfWeek());

        System.out.println("当前时间加一天"+localDateTime.plusDays(1));
        System.out.println("当前时间加一小时"+localDateTime.plusHours(1));

        System.out.println("当前时间减一小时"+localDateTime.minusHours(1));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("日期格式化:"+dateTimeFormatter.format(localDateTime));

        Date localDateTime2Date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
