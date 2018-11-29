package com.example.springboot.domain.DO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lucifer
 * @date 2017/12/2
 */
@Data
public class ProvinceDO {
    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static final class ProvinceDOBuilder {
        private Long id;
        private String name;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;

        private ProvinceDOBuilder() {
        }

        public static ProvinceDOBuilder aProvinceDO() {
            return new ProvinceDOBuilder();
        }

        public ProvinceDOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ProvinceDOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProvinceDOBuilder withCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public ProvinceDOBuilder withUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public ProvinceDO build() {
            ProvinceDO provinceDO = new ProvinceDO();
            provinceDO.setId(id);
            provinceDO.setName(name);
            provinceDO.setCreateTime(createTime);
            provinceDO.setUpdateTime(updateTime);
            return provinceDO;
        }
    }
}
