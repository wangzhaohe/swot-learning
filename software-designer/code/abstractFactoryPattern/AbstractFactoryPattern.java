//@+leo-ver=5-thin
//@+node:swot.20251101142408.1: * @file code/abstractFactoryPattern/AbstractFactoryPattern.java
//@@language java
//@+others
//@+node:swot.20251101142408.2: ** interface ProductA
//@+doc
// [source,java]
// ----
//@@c
//@@language java
interface ProductA {
    public void info();  // 抽象方法没有方法体
}
//@+doc
// ----
//
//@+node:swot.20251101142408.3: ** class ProductA1
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class ProductA1 implements ProductA {
    @Override
    public void info() {
        System.out.println("ProductA1");
    }
}
//@+doc
// ----
//
//@+node:swot.20251101145150.1: ** class ProductA2
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class ProductA2 implements ProductA {
    @Override
    public void info() {
        System.out.println("ProductA2");
    }
}
//@+doc
// ----
//
//@+node:swot.20251101145346.1: ** interface ProductB
//@+doc
// [source,java]
// ----
//@@c
//@@language java
interface ProductB {
    public void info();  // 抽象方法没有方法体
}
//@+doc
// ----
//
//@+node:swot.20251101142408.4: ** class ProductB1
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class ProductB1 implements ProductB {
    @Override
    public void info() {
        System.out.println("ProductB1");
    }
}
//@+doc
// ----
//
//@+node:swot.20251101145427.1: ** class ProductB2
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class ProductB2 implements ProductB {
    @Override
    public void info() {
        System.out.println("ProductB2");
    }
}
//@+doc
// ----
//
//@+node:swot.20251101142408.5: ** interface Factory
//@+doc
// [source,java]
// ----
//@@c
//@@language java
interface Factory {
    public ProductA createProductA();
    public ProductB createProductB();
}
//@+doc
// ----
//
//@+node:swot.20251101142408.6: ** class Factory1
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class Factory1 implements Factory {

    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB createProductB(){
        return new ProductB1();
    }
}
//@+doc
// ----
//
//@+node:swot.20251101150821.1: ** class Factory2
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class Factory2 implements Factory {

    @Override
    public ProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public ProductB createProductB(){
        return new ProductB2();
    }
}
//@+doc
// ----
//
//@+node:swot.20251101142408.8: ** AbstractFactoryPattern 运行主类
//@+doc
// [source,java]
// ----
//@@c
//@@language java
public class AbstractFactoryPattern{
    public static void main(String[] args) {

        Factory factory1 = new Factory1();
        ProductA productA = factory1.createProductA();
        productA.info();

        Factory factory2 = new Factory2();
        ProductB productB = factory2.createProductB();
        productB.info();
    }
}
//@+doc
// ----
//
// .run result
// ....
// ProductA1
// ProductB2
// ....
//@-others
 
//@-leo
