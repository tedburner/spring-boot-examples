package com.kit.common.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: Lucifer
 * @date: 2018-11-30 11:37
 * @description:
 */
public class HessianSerializer implements ISerializer<Object> {

    @Override
    public byte[] serialize(Object o) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(o);

            byte[] bytes =  baos.toByteArray();
            return bytes;

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (baos != null){
                    baos.close();
                }
                if (oos != null){
                    oos.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Object deserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try{
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }


}
