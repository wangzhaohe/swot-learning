//@+leo-ver=5-thin
//@+node:swot.20251031133739.1: * @file code/simpleFactoryPattern/SimpleFactoryPattern.java
//@@language java
//@+others
//@+node:swot.20251030080937.1: ** abstract class Product
//@+doc
// [source,java]
// ----
//@@c
//@@language java
abstract class Product {
    public abstract void info();  // 抽象方法没有方法体
}
//@+doc
// ----
//
//@+node:swot.20251030081135.1: ** class ProductA
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class ProductA extends Product {
    @Override
    public void info() {
        System.out.println("ProductA");
    }
}
//@+doc
// ----
//
//@+node:swot.20251030081339.1: ** class ProductB
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class ProductB extends Product {
    @Override
    public void info() {
        System.out.println("ProductB");
    }
}
//@+doc
// ----
//
//@+node:swot.20251030081409.1: ** class Factory
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class Factory {

    // 定义静态方法
    public static Product createProduct(String type) {
        switch (type) {
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                throw new IllegalArgumentException(
                          "Unknown product type:" + type);
        }
    }
}
//@+doc
// ----
//
//@+node:swot.20251031134700.1: ** SimpleFactoryPattern 运行主类
//@+doc
// [source,java]
// ----
//@@c
//@@language java
public class SimpleFactoryPattern{
    public static void main(String[] args) {

        // 使用静态工厂方法创建不同的产品
        Product productA = Factory.createProduct("A");
        productA.info();

        Product productB = Factory.createProduct("B");
        productB.info();
    }
}
//@+doc
// ----
//
//@-others
 
//@-leo
