// @clean code/testBasicSyntax/src/ReflectionDemo/DoorException.java
package ReflectionDemo;

/**
 * 自定义异常：专门处理门禁系统的异常情况
 */
public class DoorException extends Exception {
    public DoorException(String message) {
        super(message);
    }
}

