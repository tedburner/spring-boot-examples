package com.example.SpringBoot.dto.DO;

import java.time.LocalDateTime;

/**
 * @author lingjun.jlj
 * @date 2017/12/2
 */
public class ProvinceDO {
    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ProvinceDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

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
