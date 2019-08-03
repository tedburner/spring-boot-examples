package com.springboot.sample.domain.DO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lucifer
 * @data 2018/4/2
 */
@Data
public class UserDO {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String card;
    private String phone;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    public static final class UserDOBuilder {
        private Long id;
        private String name;
        private String password;
        private Integer age;
        private String card;
        private String phone;
        private Integer status;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;

        private UserDOBuilder() {
        }

        public static UserDOBuilder anUserDO() {
            return new UserDOBuilder();
        }

        public UserDOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserDOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserDOBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDOBuilder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public UserDOBuilder withCard(String card) {
            this.card = card;
            return this;
        }

        public UserDOBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserDOBuilder withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public UserDOBuilder withCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public UserDOBuilder withUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public UserDO build() {
            UserDO userDO = new UserDO();
            userDO.setId(id);
            userDO.setName(name);
            userDO.setPassword(password);
            userDO.setAge(age);
            userDO.setCard(card);
            userDO.setPhone(phone);
            userDO.setStatus(status);
            userDO.setCreateTime(createTime);
            userDO.setUpdateTime(updateTime);
            return userDO;
        }
    }
}
