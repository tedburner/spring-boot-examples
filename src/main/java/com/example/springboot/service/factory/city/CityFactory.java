package com.example.springboot.service.factory.city;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lingjun.jlj
 * @data 2018/4/28
 * @Description:
 */
@Service
public class CityFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static Map<Integer, CityProcessor> repository = new ConcurrentHashMap<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CityFactory.applicationContext = applicationContext;
    }

    @PostConstruct
    private void registerPscPatientProcessor() {
        Map<String, CityProcessor> recommendBaseManagerBeans = applicationContext
                .getBeansOfType(CityProcessor.class);
        if (null != recommendBaseManagerBeans && !recommendBaseManagerBeans.isEmpty()) {
            for (Map.Entry<String, CityProcessor> iterator : recommendBaseManagerBeans.entrySet()) {
                register(iterator.getValue().getTag(), iterator.getValue());
            }
        }
    }

    private void register(Integer key, CityProcessor value) {
        if (null != key) {
            repository.putIfAbsent(key, value);
        }
    }

    public CityProcessor getCityProcessor(Integer tag) {
        return repository.get(tag);
    }
}
