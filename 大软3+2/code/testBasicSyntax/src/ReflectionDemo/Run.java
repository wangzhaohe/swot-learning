// @clean code/testBasicSyntax/src/ReflectionDemo/Run.java
package ReflectionDemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Run {
    public static void main(String[] args) {
        try {
            // ? 告诉编译器：“我要接收一个类，但我现在还不知道具体是什么类。”
            Class<?> clazz = Class.forName("ReflectionDemo.StudentCard");

            System.out.println("--- 类的基本信息 ---");
            System.out.println("全限定类名: " + clazz.getName());
            System.out.println("简单类名: " + clazz.getSimpleName());
            System.out.println("\n--- 继承与接口 ---");
            System.out.println("它的父类是: " + clazz.getSuperclass().getSimpleName());

            Class<?>[] interfaces = clazz.getInterfaces();

            for (Class<?> i : interfaces) {
                System.out.println("它实现的接口是: " + i.getSimpleName());
            }
            System.out.println("\n--- 属性探测 ---");

            Field[] selfFields = clazz.getDeclaredFields();
            System.out.println("StudentCard 自己声明的属性数量: " + selfFields.length); // 结果为0

            // 真正要看属性，得去扫描它的父类 BaseDoorCard
            Field[] superFields = clazz.getSuperclass().getDeclaredFields();

            for (Field f : superFields) {
                System.out.println("在父类中发现属性: " + f.getName() + " (类型: " + f.getType().getSimpleName() + ")");
            }
            System.out.println("\n--- 方法探测 ---");

            Method[] methods = clazz.getDeclaredMethods();

            for (Method m : methods) {
                System.out.println("发现方法: " + m.getName());
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("找不到类，请检查包名和类名是否正确！");
            e.printStackTrace();
        }
    }
}
