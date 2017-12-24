package com.example.SpringBoot.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lingjun.jlj
 * @date 2017-12-24
 **/
@Component
@Scope("singleton")
@DisconfFile(filename = "database.properties")
@DisconfUpdateService(classes = {DataBaseConfig.class})
public class DataBaseConfig implements IDisconfUpdate {

    private String qiniuBucket;
    private String qiniuDomain;

    @Override
    public void reload() throws Exception {

    }
    @DisconfFileItem(name = "qiniu.public.bucket", associateField = "qiniuBucket")
    public String getQiniuBucket() {
        return qiniuBucket;
    }
    @DisconfFileItem(name = "qiniu.public.domain", associateField = "qiniuDomain")
    public String getQiniuDomain() {
        return qiniuDomain;
    }

    public void setQiniuBucket(String qiniuBucket) {
        this.qiniuBucket = qiniuBucket;
    }

    public void setQiniuDomain(String qiniuDomain) {
        this.qiniuDomain = qiniuDomain;
    }
}
