package com.kit.common.util.common.copy;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: kiturone
 * @date: 2018/8/20 10:05
 * @description: 基于cglib 的BeanCopier
 */
public class BeanCopierUtils {

    private static final ConcurrentMap<String, BeanCopier> cacheCopierMap = Maps.newConcurrentMap();

    public BeanCopierUtils() {
    }

    /**
     * 批量copy
     *
     * @param sourceList  源类
     * @param targetClass 目标类
     */
    public static <T> List<T> copyList(List<?> sourceList, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        } else {
            List<T> resultList = Lists.newArrayListWithCapacity(sourceList.size());
            Iterator<?> iterator = sourceList.iterator();

            while (iterator.hasNext()) {
                Object source = iterator.next();
                try {
                    T target = targetClass.newInstance();
                    copy(source, target);
                    resultList.add(target);
                } catch (Exception var6) {
                    throw new RuntimeException(var6);
                }
            }
            return resultList;
        }
    }

    /**
     * @param source      源数据类
     * @param targetClass 目标类
     */
    public static <T> T copy(Object source, Class<T> targetClass) {
        T target;
        try {
            target = targetClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }

        copy(source, target);

        return target;
    }

    /**
     * @param source 源数据类
     * @param target 目标类
     */
    public static void copy(Object source, Object target) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, (Converter) null);
    }

    private static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
        String copierKey = sourceClass.toString() + "#" + targetClass.toString();
        if (cacheCopierMap.containsKey(copierKey)) {
            return (BeanCopier) cacheCopierMap.get(copierKey);
        } else {
            BeanCopier beanCopier = BeanCopier.create(sourceClass, targetClass, false);
            cacheCopierMap.put(copierKey, beanCopier);
            return beanCopier;
        }
    }
}
