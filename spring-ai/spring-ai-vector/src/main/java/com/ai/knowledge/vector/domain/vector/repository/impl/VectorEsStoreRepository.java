package com.ai.knowledge.vector.domain.vector.repository.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.*;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import com.ai.knowledge.vector.domain.vector.repository.VectorStoreRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.elasticsearch.ElasticsearchVectorStore;
import org.springframework.ai.vectorstore.elasticsearch.autoconfigure.ElasticsearchVectorStoreProperties;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于 es 的向量存储
 *
 * @author lingjun.jlj
 * @date 2025/5/11 10:36
 */
@Repository
public class VectorEsStoreRepository implements VectorStoreRepository {

    private static Logger LOGGER = LoggerFactory.getLogger(VectorEsStoreRepository.class);
    private static final String textField = "content";

    private static final String vectorField = "embedding";

    private final ElasticsearchClient elasticsearchClient;
    private final ElasticsearchVectorStore elasticsearchVectorStore;
    private final ElasticsearchVectorStoreProperties options;

    public VectorEsStoreRepository(ElasticsearchClient elasticsearchClient,
                                   ElasticsearchVectorStore elasticsearchVectorStore,
                                   ElasticsearchVectorStoreProperties options) {
        this.elasticsearchClient = elasticsearchClient;
        this.elasticsearchVectorStore = elasticsearchVectorStore;
        this.options = options;
    }

    @Override
    public void store(String text) {
        // 判断构建索引
        createIndexIfNotExists();
        // 构建向量存储对象
        Document document = new Document(text);
        elasticsearchVectorStore.add(List.of(document));
    }

    @Override
    public void store(String text, float[] embedding) {
        // todo 先构建数据格式，然后自己执行 elasticsearch 进行文本存储
    }

    /**
     * 判断索引是否存在，如果不存在则创建索引
     */
    private void createIndexIfNotExists() {
        try {
            // 从配置中获取索引名称和维度
            final String indexName = options.getIndexName();
            final Integer dimsLength = options.getDimensions();

            if (StringUtils.isBlank(indexName)) {
                throw new IllegalArgumentException("Elastic search index name must be provided");
            }

            boolean exists = elasticsearchClient.indices().exists(idx -> idx.index(indexName)).value();
            if (exists) {
                LOGGER.info("Index {} already exists. Skipping creation.", indexName);
                return;
            }

            final String similarityAlgo = options.getSimilarity().name();
            IndexSettings indexSettings = IndexSettings
                    .of(settings -> settings.numberOfShards(String.valueOf(1)).numberOfReplicas(String.valueOf(1)));

            // Maybe using json directly?
            Map<String, Property> properties = new HashMap<>();
            properties.put(vectorField, Property.of(property -> property.denseVector(
                    DenseVectorProperty.of(dense -> dense.index(true).dims(dimsLength).similarity(similarityAlgo)))));
            properties.put(textField, Property.of(property -> property.text(TextProperty.of(t -> t))));

            Map<String, Property> metadata = new HashMap<>();
            metadata.put("ref_doc_id", Property.of(property -> property.keyword(KeywordProperty.of(k -> k))));

            properties.put("metadata",
                    Property.of(property -> property.object(ObjectProperty.of(op -> op.properties(metadata)))));

            CreateIndexResponse indexResponse = elasticsearchClient.indices()
                    .create(createIndexBuilder -> createIndexBuilder.index(indexName)
                            .settings(indexSettings)
                            .mappings(TypeMapping.of(mappings -> mappings.properties(properties))));

            if (!indexResponse.acknowledged()) {
                throw new RuntimeException("failed to create index");
            }

            LOGGER.info("create elasticsearch index {} successfully", indexName);
        } catch (IOException e) {
            LOGGER.error("failed to create index", e);
            throw new RuntimeException(e);
        }
    }
}
