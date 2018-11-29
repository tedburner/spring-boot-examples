package com.example.springboot.service.factory.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lucifer
 * @data 2018/4/28
 * @Description:
 */
@Service
public class TestFactory {

    public static Integer DEFAULT = -1;

    private final Map<Integer, TestProcessor> testProcessorMap;

    @Autowired
    public TestFactory(List<TestProcessor> testProcessorList) {
        this.testProcessorMap = testProcessorList.stream().collect(Collectors.toMap(TestProcessor::getTag, m -> m));
    }

    public void process(Integer code) throws Exception {

        testProcessorMap.getOrDefault(code
                , testProcessorMap.get(DEFAULT))
                .show();
    }
}
