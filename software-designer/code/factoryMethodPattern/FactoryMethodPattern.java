//@+leo-ver=5-thin
//@+node:swot.20251101001909.2: * @file code/factoryMethodPattern/FactoryMethodPattern.java
//@@language java
//@+others
//@+node:swot.20251101001909.4: ** interface Product
//@+doc
// [source,java]
// ----
//@@c
//@@language java
interface Product {
    public void info();  // 抽象方法没有方法体
}
//@+doc
// ----
//
//@+node:swot.20251101001909.5: ** class ProductA
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class ProductA implements Product {
    @Override
    public void info() {
        System.out.println("ProductA");
    }
}
//@+doc
// ----
//
//@+node:swot.20251101001909.6: ** class ProductB
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class ProductB implements Product {
    @Override
    public void info() {
        System.out.println("ProductB");
    }
}
//@+doc
// ----
//
//@+node:swot.20251101001909.7: ** interface Factory
//@+doc
// [source,java]
// ----
//@@c
//@@language java
interface Factory {
    public Product createProduct();
}
//@+doc
// ----
//
//@+node:swot.20251101010811.1: ** class FactoryA
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class FactoryA implements Factory {
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}
//@+doc
// ----
//
//@+node:swot.20251101011100.1: ** class FactoryB
//@+doc
// [source,java]
// ----
//@@c
//@@language java
class FactoryB implements Factory {
    @Override
    public Product createProduct() {
        return new ProductB();
    }
}
//@+doc
// ----
//
//@+node:swot.20251101001909.3: ** FactoryMethodPattern 运行主类
//@+doc
// [source,java]
// ----
//@@c
//@@language java
public class FactoryMethodPattern{
    public static void main(String[] args) {
        Factory factoryA = new FactoryA();
        Product productA = factoryA.createProduct();
        productA.info();

        Factory factoryB = new FactoryB();
        Product productB = factoryB.createProduct();
        productB.info();
    }
}
//@+doc
// ----
//
//@-others

//@-leo
