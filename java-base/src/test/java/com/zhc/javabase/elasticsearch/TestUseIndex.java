package com.zhc.javabase.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhc.javabase.entity.UserInfo;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Max;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 索引操作单元测试
 * @author zhouhengchao
 * @date 2023-03-01 09:40:00
 * @version 1.0
 */
@SpringBootTest
public class TestUseIndex {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Test
    void testQueryIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("user2");
        GetIndexResponse response = restHighLevelClient.indices().get(request, RequestOptions.DEFAULT);
        System.out.println("aliases:"+response.getAliases());
        System.out.println("setting:"+response.getSettings());
        System.out.println("mapping:"+response.getMappings());
    }

    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("user2");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println("操作结果:"+response.isAcknowledged());
    }

    @Test
    void testAddIndexSource() throws IOException {
        IndexRequest request = new IndexRequest();
        request.index("user").id("1001");
        // 创建数据对象
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("小周");
        userInfo.setAge(18);
        userInfo.setSex("男");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(userInfo);
        request.source(jsonStr, XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(request,RequestOptions.DEFAULT);
        // 打印结果信息
        System.out.println("_index:"+indexResponse.getIndex());
        System.out.println("_id:"+indexResponse.getId());
        System.out.println("_result:"+indexResponse.getResult());
    }

    @Test
    void testUpdateIndex() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("user").id("1001");
        updateRequest.doc(XContentType.JSON,"sex","女","userName","周哥");
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println("_index:"+updateResponse.getIndex());
        System.out.println("_id:"+updateResponse.getId());
        System.out.println("_result:"+updateResponse.getResult());
    }

    @Test
    void testGetData() throws IOException {
        GetRequest getRequest = new GetRequest();
        getRequest.index("user").id("1001");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("_index:"+getResponse.getIndex());
        System.out.println("_id:"+getResponse.getId());
        System.out.println("_type:"+getResponse.getType());
        System.out.println("_source:"+getResponse.getSource());
    }

    @Test
    void testDeleteData() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.index("user").id("1001");
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.toString());
    }

    @Test
    void testBatchAddData() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "zhangsan", "age", "10", "sex","女"));
        bulkRequest.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "lisi", "age", "30", "sex","女"));
        bulkRequest.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "wangwu1", "age", "40", "sex","男"));
        bulkRequest.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "wangwu2", "age", "20", "sex","女"));
        bulkRequest.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON, "name", "wangwu3", "age", "50", "sex","男"));
        bulkRequest.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON, "name", "wangwu4", "age", "20", "sex","男"));
        BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("took:"+response.getTook());
        System.out.println("items:"+response.getItems());
    }

    @Test
    void testBatchDeleteData() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new DeleteRequest().index("user").id("1001"));
        bulkRequest.add(new DeleteRequest().index("user").id("1003"));
        BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("took:"+response.getTook());
        System.out.println("items:"+response.getItems());
    }

    @Test
    void testQueryData() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = searchResponse.getHits();
        System.out.println("took:" + searchResponse.getTook());
        System.out.println("timeout:" + searchResponse.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    }

    @Test
    void testTermQueryData() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
       // searchSourceBuilder.query(QueryBuilders.termQuery("age",30));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.sort("age", SortOrder.ASC);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = searchResponse.getHits();
        System.out.println("took:" + searchResponse.getTook());
        System.out.println("timeout:" + searchResponse.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    }

    @Test
    void testBoolQuery() throws IOException {
        // 创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        // 构建查询请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.matchQuery("age",30));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name","zhangsan"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("sex","男"));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        // 查询响应结果
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = searchResponse.getHits();
        System.out.println("took:" + searchResponse.getTook());
        System.out.println("timeout:" + searchResponse.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        // 输出每条查询的结果信息
        for(SearchHit searchHit : hits){
            System.out.println(searchHit.getSourceAsString());
        }
        System.out.println("<<========");
    }

    @Test
    void testAggregationQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest().indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //sourceBuilder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));
        sourceBuilder.aggregation(AggregationBuilders.max("maxAge").field("age"));
        searchRequest.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        Max max = (Max)response.getAggregations().asMap().get("maxAge");
        System.out.println("max:"+max.getValue());
        SearchHits hits = response.getHits();
        for(SearchHit searchHit : hits){
            System.out.println(searchHit.getSourceAsString());
        }
    }


}
