package com.example.demo.transform;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class Contact2Json {



    public static String Contact2Json(String className) {
        Object message = null;

        try {
            Class mClass = Class.forName(className);
            Field[] fields = mClass.getFields();
            try {
                message = mClass.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }

            for (Field field :
                    fields) {

                traverse(message, field);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("=======================" + e);

        }
        String gson = new Gson().toJson(message);
        System.out.println("gson=======================");

        System.out.println(gson);
        return gson;
    }

    private static Object traverse(Object o2, Field fieldR) {
        Object o1 = null;
        String type = fieldR.getType().getName();
        if (type.equals("java.util.Comparator")) {
            return o1;
        }
        try {

            if (type.equals("java.lang.String")) {
                fieldR.set(o2, fieldR.getName());
            } else if (type.equals("java.lang.Integer")) {
                fieldR.set(o2, Integer.MIN_VALUE);
            } else if (type.equals("java.lang.Boolean")) {
                fieldR.set(o2, Boolean.TRUE);
            } else if (type.equals("java.lang.Long")) {
                fieldR.set(o2, Long.MIN_VALUE);
            } else if (type.equals("java.lang.Double")) {
                fieldR.set(o2, Double.MIN_VALUE);
            } else if (type.equals("java.lang.Short")) {
                fieldR.set(o2, Short.MIN_VALUE);
            } else if (type.equals("java.lang.Float")) {
                fieldR.set(o2, Float.MIN_VALUE);
            } else if (type.equals("java.util.Calendar")) {
                fieldR.set(o2, Calendar.DATE);

            } else if (type.equals("java.util.List")) {
                ParameterizedType genericType = (ParameterizedType) fieldR.getGenericType();
                Class<?> aClass = (Class<?>) genericType.getActualTypeArguments()[0];
                String bclass = aClass.getName();
                o1 = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    Class mClass = Class.forName(bclass);
                    Object o = null;
                    try {
                         o = mClass.newInstance();

                    }catch (Exception e){
                        o = objectInstance(bclass);
                    }
                    Object[] objects = {o};
                    boolean isReturn = objectBindData(objects);
                    if (!isReturn) {
                        for (Field field :
                                mClass.getFields()) {
                            traverse(o, field);
                        }

                    }

                    ((ArrayList) o1).add(objects[0]);
                }
                fieldR.set(o2, o1);

            } else if (type.equals("java.util.Map")) {
                ParameterizedType genericType = (ParameterizedType) fieldR.getGenericType();
                Class<?> aClass = (Class<?>) genericType.getActualTypeArguments()[1];
                String bclass = aClass.getName();
                o1 = new HashMap<>();
                for(int i = 0 ; i <3 ;i++){
                    Class mClass = Class.forName(bclass);
                    Object o = null;
                    try {
                        o = mClass.newInstance();

                    }catch (Exception e){
                        o = objectInstance(bclass);
                    }
                    Object[] objects = {o};
                    boolean isReturn = objectBindData(objects);
                    if (!isReturn) {
                        for (Field field :
                                mClass.getFields()) {
                            traverse(o, field);
                        }

                    }

                    ((Map) o1).put(""+i,objects[0]);
                }
                fieldR.set(o2, o1);

            } else {
                Class mClass = Class.forName(type);
                o1 = mClass.newInstance();
                for (Field field :
                        mClass.getFields()) {
                    traverse(o1, field);
                }
                fieldR.set(o2, o1);

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("haha======" + e);

        }

        return o1;
    }

    private static Object objectInstance(String type){
        Object o = null;
        if (type.equals("java.lang.String")) {
            o = String.valueOf("string");
        } else if (type.equals("java.lang.Integer")) {
            o = Integer.MIN_VALUE;
        } else if (type.equals("java.lang.Boolean")) {
            o = Boolean.TRUE;
        } else if (type.equals("java.lang.Long")) {
            o = Long.MIN_VALUE;
        } else if (type.equals("java.lang.Double")) {
            o = Long.MIN_VALUE;
        } else if (type.equals("java.lang.Short")) {
            o = Long.MIN_VALUE;
        } else if (type.equals("java.lang.Float")) {
            o = Long.MIN_VALUE;
        } else if (type.equals("java.util.Calendar")) {
           o = Calendar.DATE;

        }
        return o;
    }

    private static boolean objectBindData(Object[] list) {
        Object o = list[0];
        if (o instanceof String) {
            list[0] = "string";
            return true;
        } else if (o instanceof Boolean) {
            list[0] = Boolean.TRUE;
            return true;

        } else if (o instanceof Integer) {
            list[0] = Integer.MIN_VALUE;
            return true;

        } else if (o instanceof Long) {
            list[0] = Long.MIN_VALUE;
            return true;

        } else if (o instanceof Float) {
            list[0] = Float.MIN_VALUE;
            return true;

        } else if (o instanceof Double) {
            list[0] = Double.MIN_VALUE;
            return true;

        } else if (o instanceof Calendar) {
            list[0] = Calendar.DATE;
            return true;

        } else if (o instanceof Short) {
            list[0] = Short.MIN_VALUE;
            return true;

        } else {
            return false;
        }


    }


}
