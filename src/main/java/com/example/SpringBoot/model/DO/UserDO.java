package com.example.SpringBoot.model.DO;

import lombok.Data;

/**
 * @author lingjun.jlj
 * @data 2018/4/2
 */
@Data
public class UserDO {
    private Integer id;
    private String name;
    private Integer age;


    public static final class UserDOBuilder {
        private Integer id;
        private String name;
        private Integer age;

        private UserDOBuilder() {
        }

        public static UserDOBuilder anUserDO() {
            return new UserDOBuilder();
        }

        public UserDOBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public UserDOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserDOBuilder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public UserDO build() {
            UserDO userDO = new UserDO();
            userDO.name = this.name;
            userDO.id = this.id;
            userDO.age = this.age;
            return userDO;
        }
    }

}
