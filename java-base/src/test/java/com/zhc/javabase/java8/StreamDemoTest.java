package com.zhc.javabase.java8;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StreamDemoTest {

    @Resource
    private StreamDemo streamDemo;

    @Test
    public void testStreamSpeed(){
        List<Integer> dataList = new ArrayList<Integer>(5000000);
        for(int i = 0;i < 500000; i++){
            dataList.add((int)Math.random()*10000);
        }

        dataList.stream().filter(o -> o > 100).count();
    }

    @Test
    public void testStreamCreate(){
        streamDemo.createStream();
    }

    @Test
    public void testStreamFilter(){
        streamDemo.streamFilter();
    }

    @Test
    public void testStreamMap(){
        streamDemo.streamMap();
    }

    @Test
    public void testStreamFlatMap(){
        streamDemo.streamFlatMap();
    }

    @Test
    public void testStreamSorted(){
        streamDemo.streamSorted();
    }

    @Test
    public void testStreamMatch(){
        streamDemo.streamMatch();
    }

    @Test
    public void testStreamCollect(){
        streamDemo.streamCollect();
    }

    @Test
    public void testStreamJoin(){
        streamDemo.streamJoin();
    }

    @Test
    public void testStreamReduce(){
        streamDemo.streamReduce();
    }

    @Test
    public void testStreamCollectAndThen(){
        streamDemo.streamCollectAndThen();
    }
}
