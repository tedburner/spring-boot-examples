package com.example.SpringBoot.model.DO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lingjun.jlj
 * @create 2017-10-11
 **/
@Data
public class GoodDO implements Serializable {

    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static final class GoodDOBuilder {
        private Long id;
        private String name;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;

        private GoodDOBuilder() {
        }

        public static GoodDOBuilder aGoodDO() {
            return new GoodDOBuilder();
        }

        public GoodDOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public GoodDOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GoodDOBuilder withCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public GoodDOBuilder withUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public GoodDO build() {
            GoodDO goodDO = new GoodDO();
            goodDO.updateTime = this.updateTime;
            goodDO.createTime = this.createTime;
            goodDO.name = this.name;
            goodDO.id = this.id;
            return goodDO;
        }
    }


}
