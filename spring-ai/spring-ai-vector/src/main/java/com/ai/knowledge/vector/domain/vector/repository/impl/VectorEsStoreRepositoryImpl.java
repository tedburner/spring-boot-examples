package com.ai.knowledge.vector.domain.vector.repository.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import com.ai.knowledge.vector.domain.vector.repository.VectorStoreRepository;
import com.ai.knowledge.vector.infrastructure.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.elasticsearch.ElasticsearchVectorStore;
import org.springframework.ai.vectorstore.elasticsearch.autoconfigure.ElasticsearchVectorStoreProperties;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 基于 es 的向量存储
 *
 * @author lingjun.jlj
 * @date 2025/5/11 10:36
 */
@Repository
public class VectorEsStoreRepositoryImpl implements VectorStoreRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(VectorEsStoreRepositoryImpl.class);
    private static final String textField = "content";

    private static final String vectorField = "embedding";

    private final ElasticsearchClient elasticsearchClient;
    private final ElasticsearchVectorStore elasticsearchVectorStore;
    private final ElasticsearchVectorStoreProperties options;

    public VectorEsStoreRepositoryImpl(ElasticsearchClient elasticsearchClient,
                                       ElasticsearchVectorStore elasticsearchVectorStore,
                                       ElasticsearchVectorStoreProperties options) {
        this.elasticsearchClient = elasticsearchClient;
        this.elasticsearchVectorStore = elasticsearchVectorStore;
        this.options = options;
    }

    @Override
    public void store(String text) {
        // 构建向量存储对象
        Document document = new Document(text);
        elasticsearchVectorStore.add(List.of(document));
    }

    @Override
    public void store(String text, float[] embedding) {
        Map<String, Object> data = Map.of(textField, text,
                vectorField, embedding);
        try {
            // 新增
            CreateResponse createResponse = elasticsearchClient.create(c -> c
                    .index(options.getIndexName()) // 索引名字
                    .id(IdUtil.getId()) // id
                    .document(data) // 实体类
            );
            LOGGER.info("添加成功");
        } catch (IOException e) {
            LOGGER.error("向es保存向量数据异常", e);
        }
    }

    @Override
    public List<Document> retrieval(String text, Integer topK, double threshold) {
        SearchRequest searchRequest = SearchRequest.builder()
                // 向量召回前多少
                .topK(Optional.ofNullable(topK).orElse(5))
                // 相似度阈值
                .similarityThreshold(threshold)
                .filterExpression(new FilterExpressionBuilder().eq(textField, text).build())
                .build();
        return  elasticsearchVectorStore.doSimilaritySearch(searchRequest);
    }
}
