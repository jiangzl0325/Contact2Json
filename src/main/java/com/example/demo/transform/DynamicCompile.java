package com.example.demo.transform;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicCompile {

    public static String eval(String sourceCode) {
        sourceCode = "package com.example.demo.bean;\nimport java.util.List;\nimport java.util.Map;\npublic "+sourceCode;
        String pattern = "public (class|enum) ([a-zA-Z0-9]*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(sourceCode);
        String name = "";
        if (m.find( )) {
            System.out.println("Found value: " + m.group(2) );
            name = m.group(2);
        }
        try {
            //将源文件写入到磁盘中
            String javaFileName = name+".java";
            //生成的Java源文件存放到<module>/build/generated/source/java目录下  (开发工具为Android Studio, java-demo是我的module名称)
            File sourceDir = new File("/Users/jiangzl/Downloads/demo/src/main/java/com/example/demo/bean");
            if (!sourceDir.exists()) {
                sourceDir.mkdirs();
            }
            File javaFile = new File(sourceDir, javaFileName);
            PrintWriter writer = new PrintWriter(new FileWriter(javaFile));
            writer.write(sourceCode.toString());
            writer.flush();
            writer.close();

            //动态编译磁盘中的代码
            //生成的字节码文件存放到<module>/build/classes/main目录下
            File distDir = new File("/Users/jiangzl/Downloads/demo/target/classes");
            if (!distDir.exists()) {
                distDir.mkdirs();
            }
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            //JavaCompiler最核心的方法是run, 通过这个方法编译java源文件, 前3个参数传null时,
            //分别使用标准输入/输出/错误流来 处理输入和编译输出. 使用编译参数-d指定字节码输出目录.
            int compileResult = javac.run(null, null, null, "-d", distDir.getAbsolutePath(), javaFile.getAbsolutePath());
            //run方法的返回值: 0-表示编译成功, 否则表示编译失败
            if(compileResult != 0) {
                System.err.println("编译失败!!");
                return null;
            }
            System.out.println("编译成功!!");
            ParentsDelegateClassLoader loader = new ParentsDelegateClassLoader();
            loader.load("/Users/jiangzl/Downloads/demo/src/main/java","com.example.demo.bean."+name);
            return "com.example.demo.bean."+name;

        }  catch (Exception e) {
            System.out.println("ParentsDelegateClassLoader==================="+e);
        }
        return null;
    }
}
