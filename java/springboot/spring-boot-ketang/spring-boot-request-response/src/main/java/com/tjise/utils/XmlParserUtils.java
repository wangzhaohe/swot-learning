//@+leo-ver=5-thin
//@+node:swot.20241017141815.7: * @file spring-boot-ketang/spring-boot-request-response/src/main/java/com/tjise/utils/XmlParserUtils.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
package com.tjise.utils;

import com.tjise.pojo.Address;
import com.tjise.pojo.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParserUtils {
    public static List<User> parse(String file)  {
        ArrayList<User> list = new ArrayList<>(); //封装解析出来的数据

        try {
            //1.获取一个解析器对象
            SAXReader saxReader = new SAXReader();
            //2.利用解析器把xml文件加载到内存中,并返回一个文档对象
            Document document = saxReader.read(new File(file));
            //3.获取到根标签
            Element rootElement = document.getRootElement();
            //4.通过根标签来获取 user 标签
            List<Element> userElements = rootElement.elements("user");

            //5.遍历集合,得到每一个 user 标签
            for (Element element : userElements) {
                //获取 name 属性
                String name = element.element("name").getText();

                //获取 age 标签
                String age = element.element("age").getText();

                //获取 address 标签
                Element address = element.element("address");
                String province = address.element("province").getText();
                String city = address.element("city").getText();

                //组装数据
                User user = new User();
                user.setName(name);
                user.setAge(Integer.parseInt(age));

                Address addr = new Address();
                addr.setProvince(province);
                addr.setCity(city);
                user.setAddress(addr);

                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
//@+doc
// ----
//@-leo
