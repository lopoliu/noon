package com.lopo;


import com.lopo.search.domain.Product;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import javax.annotation.Resource;

public class ElasticSearchTest {
    
    @Resource
    private ElasticsearchOperations elasticsearchOperations;


    @Test
    public void saveProductIndex() {
        Product product = new Product();
        product.setId(1);
        product.setName("iphone14 pro max");
        product.setPrice(69999.66);
        product.setDescription("美国有苹果，中国有菠萝");
        elasticsearchOperations.save(product);
    }
}