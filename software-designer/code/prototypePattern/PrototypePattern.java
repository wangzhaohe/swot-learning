//@+leo-ver=5-thin
//@+node:swot.20251102182806.1: * @file code/prototypePattern/PrototypePattern.java
//@@language java
// 超简单原型模式
interface Animal {
    Animal clone();
    void speak();
}

class Dog implements Animal {
    private String name;

    public Dog(String name) { this.name = name; }

    public Animal clone() {
        return new Dog(this.name);
    }

    public void speak() {
        System.out.println(name + ": 汪汪!");
    }
}

// 使用
public class PrototypePattern {
    public static void main(String[] args) {
        Animal original = new Dog("小黑");
        Animal cloned = original.clone();

        original.speak();  // 小黑: 汪汪!
        cloned.speak();    // 小黑: 汪汪!
    }
}
//@-leo
