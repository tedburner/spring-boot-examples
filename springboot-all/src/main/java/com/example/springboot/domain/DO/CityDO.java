package com.example.springboot.domain.DO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Lucifer
 * @create 2017-09-22
 **/
@Data
public class CityDO implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public static final class CityDOBuilder {
        private Long id;
        private Long provinceId;
        private String name;
        private String description;
        private Integer status;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;

        private CityDOBuilder() {
        }

        public static CityDOBuilder aCityDO() {
            return new CityDOBuilder();
        }

        public CityDOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CityDOBuilder withProvinceId(Long provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public CityDOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CityDOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CityDOBuilder withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public CityDOBuilder withCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public CityDOBuilder withUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public CityDO build() {
            CityDO cityDO = new CityDO();
            cityDO.setId(id);
            cityDO.setProvinceId(provinceId);
            cityDO.setName(name);
            cityDO.setDescription(description);
            cityDO.setStatus(status);
            cityDO.setCreateTime(createTime);
            cityDO.setUpdateTime(updateTime);
            return cityDO;
        }
    }
}
