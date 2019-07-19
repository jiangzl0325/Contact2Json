package com.example.demo.transform;

import com.google.gson.Gson;

import java.lang.reflect.Field;

public class Contact2Json {



    public static String Contact2Json(String className){
        Object message = null;
        
        try {
            Class mClass = Class.forName(className);
            Field[] fields = mClass.getFields();
            try {
                message =  mClass.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }

            for (Field field :
                    fields) {

                traverse(message,field);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("=======================" + e);

        }
        String gson = new Gson().toJson(message);
        System.out.print( "=======================" + gson);
        return gson;
    }

    private static Object traverse(Object o2 , Field fieldR) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object o1 = null;

        try {
            if (fieldR.getType().getName().equals("java.lang.String")) {
                fieldR.set(o2, fieldR.getName());
            } else if (fieldR.getType().getName().equals("java.lang.Integer")) {
                fieldR.set(o2, 1);
            } else {
                Class mClass = Class.forName(fieldR.getType().getName());
                o1 =  mClass.newInstance();
                for (Field field :
                        mClass.getFields()) {
                    traverse(o1,field);
                }
                fieldR.set(o2, o1);

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("haha======" + e);

        }

        return o1;
    }
}
