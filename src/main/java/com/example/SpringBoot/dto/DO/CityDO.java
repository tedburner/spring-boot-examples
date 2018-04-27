package com.example.SpringBoot.dto.DO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/
@Data
public class CityDO implements Serializable{

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


    public static final class CityBuilder {
        private Long id;
        private Long provinceId;
        private String name;
        private String description;

        private CityBuilder() {
        }

        public static CityBuilder aCity() {
            return new CityBuilder();
        }

        public CityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CityBuilder withProvinceId(Long provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public CityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CityBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CityDO build() {
            CityDO cityDO = new CityDO();
            cityDO.name = this.name;
            cityDO.provinceId = this.provinceId;
            cityDO.description = this.description;
            cityDO.id = this.id;
            return cityDO;
        }
    }
}
