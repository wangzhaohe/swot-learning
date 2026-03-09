// @clean code/testBasicSyntax/src/ExceptionSelfdefineExample/DoorException.java
package ArrayListExample;

/**
 * 自定义异常：专门处理门禁系统的异常情况
 */
public class DoorException extends Exception {
    public DoorException(String message) {
        super(message); // 把错误信息传给父类去处理
    }
}

