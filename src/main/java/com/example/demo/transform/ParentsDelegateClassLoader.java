package com.example.demo.transform;

import java.io.*;

public class ParentsDelegateClassLoader extends ClassLoader {
    private static final String EXT = ".class";
    private String path;

    public ParentsDelegateClassLoader() {
        path = this.getResource("").getPath();
    }

    public ParentsDelegateClassLoader(String path) {
        this.path = path;
    }

    public  void load(String path,String classname) throws ClassNotFoundException {
        Class<?> clazz = loadClass(classname);
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String path = "D:\\project\\generate_code\\src\\main\\java\\com\\ctrip\\ibu\\platform\\generate\\code\\bean";
        String classname = "com.ctrip.ibu.platform.generate.code.bean.ABean";
        ParentsDelegateClassLoader classLoader = new ParentsDelegateClassLoader(path);
        Class<?> clazz = classLoader.loadClass(classname);
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = null;
        try {
            b = loadClassFile(name);
            return this.defineClass(name, b, 0, b.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] loadClassFile(String name) throws IOException {
        String classFile = getClassFile(name);
        System.out.println("即将加载class文件" + classFile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream input = null;
        try {
            input = new FileInputStream(classFile);
            int count;
            byte[] temp = new byte[1024];
            while ((count = input.read(temp)) > -1) {
                out.write(temp, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            if (input != null)
                input.close();
        }

        return out.toByteArray();
    }

    private String getClassFile(String name) {
        String pathName = name.replace(".", File.separator);
        if (path.endsWith("/") || path.endsWith("\\")) {
            return path + pathName + EXT;
        }
        return path + File.separator + pathName + EXT;
    }
}
