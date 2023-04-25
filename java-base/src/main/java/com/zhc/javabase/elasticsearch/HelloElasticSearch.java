package com.zhc.javabase.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @author zhouhengchao
 * @date 2023-02-28 20:48:00
 * @version 1.0
 */
public class HelloElasticSearch {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        System.out.println(client);

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user2");

        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        // 响应状态
        System.out.println("操作状态 = " + acknowledged);


        // 关闭客户端连接
        client.close();

    }

}
