package com.springboot.sample.domain.DTO.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: kiturone
 * @data 2018/4/26
 * @Description:
 */

@Data
public class SampleMessageDTO {

    private final Integer id;

    private final String message;

    @JsonCreator
    public SampleMessageDTO(@JsonProperty("id") Integer id,
                            @JsonProperty("message") String message) {
        this.id = id;
        this.message = message;
    }


}
