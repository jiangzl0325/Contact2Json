package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.transform.DynamicCompile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Contact2JsonController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final String responseHead = "class ResponseHead {\n" +
            "\t\n" +
            "\tpublic String errorMessage;\n" +
            "\t\n" +
            "\tpublic String errorCode; // 错误编码\n" +
            "\n" +
            "\tpublic String showErrorMsg; // 展现给用户看的错误信息\n" +
            "\n" +
            "}";
    private static final String responseStatus = "class ResponseStatusType {\n" +
            "\tpublic String Ack;\n" +
            "}";

    private static final String ackCodeType = "enum AckCodeType  {\n" +
            "\n" +
            "    Success(\"Success\"),\n" +
            "\n" +
            "\n" +
            "    Failure(\"Failure\"),\n" +
            "\n" +
            "\n" +
            "    Warning(\"Warning\");\n" +
            "\n" +
            "    public final String value;\n" +
            "\n" +
            "    AckCodeType(String v) {\n" +
            "        value = v;\n" +
            "    }\n" +
            "\n" +
            "    public String value() {\n" +
            "        return value;\n" +
            "    }\n" +
            "}";

    @RequestMapping("/contact2json")
    public Contact2Json greeting(@RequestParam(value="contact", defaultValue="") String name) {
        String[] list = name.split("}");
        String Name = null;
        DynamicCompile.eval(responseHead);
        DynamicCompile.eval(responseStatus);
        DynamicCompile.eval(ackCodeType);
        try {
            for (int i = 0 ; i < list.length ; i++){
                String item = list[i];
                item = item.replaceAll("/.*/","");
                int index = item.indexOf("class");
                if(index == -1){
                    index = item.indexOf("enum");
                }
                item = item.substring(index,item.length());
                item+="}";
                System.out.println(item);
                item = item.replaceAll("BaijiIbuCommonTypes.ResponseHead","ResponseHead");
                item = item.replaceAll("BaijiCommonTypes.ResponseStatusType","ResponseStatusType");
                item = item.replaceAll("string","String");
                item = item.replaceAll("list<","List<");
                item = item.replaceAll("map<","Map<");
                item = item.replaceAll("bool","Boolean");
                item = item.replaceAll("long","Long");
                item = item.replaceAll("int","Integer");

                name = DynamicCompile.eval(item);
            }
        }catch (Exception e){
            System.out.println("Contact2Json============="+e);
        }


        String json = com.example.demo.transform.Contact2Json.Contact2Json(name);
        return new Contact2Json(counter.incrementAndGet(),
                json);
    }
}
