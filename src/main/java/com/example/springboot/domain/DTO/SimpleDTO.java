package com.example.springboot.domain.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author Lucifer
 * @data 2018/4/14
 */
@Data
public class SimpleDTO {
    private Integer id;
    @NotNull(message = "name 不能为null")
    private String name;

    public static final class SimpleDTOBuilder {
        private Integer id;
        private String name;

        private SimpleDTOBuilder() {
        }

        public static SimpleDTOBuilder aSimpleDTO() {
            return new SimpleDTOBuilder();
        }

        public SimpleDTOBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public SimpleDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SimpleDTO build() {
            SimpleDTO simpleDTO = new SimpleDTO();
            simpleDTO.setId(id);
            simpleDTO.setName(name);
            return simpleDTO;
        }
    }
}
