package com.example.SpringBoot.utils.common;

import com.example.SpringBoot.model.DO.UserDO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lingjun.jlj
 * @data 2018/5/2
 * @Description:
 */
public class BeanCopierUtils {

    public static Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();


    public static void copyProperties(Object source,
                                        Object target){
         String beanKey = generateKey(source.getClass(),target.getClass());
         BeanCopier copier = null;
         if (!beanCopierMap.containsKey(beanKey)) {
             copier = BeanCopier.create(source.getClass(), target.getClass(), false);
             beanCopierMap.put(beanKey, copier);
         }else {//判断之前是否已经存在了
             copier = beanCopierMap.get(beanKey);
         }
         copier.copy(source, target, null);
     }

    private static String generateKey(Class<?>class1,Class<?>class2){
        return class1.toString() + class2.toString();
    }

    public static void main(String[] args) {
        UserDO object = UserDO.UserDOBuilder
                .anUserDO()
                .withId(1)
                .withAge(20)
                .withName("蒋")
                .build();
        UserDO userDO = new UserDO();
        copyProperties(object,userDO);
        System.out.println(userDO.getAge());
    }
}
