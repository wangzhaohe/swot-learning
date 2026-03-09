// @clean code/testBasicSyntax/src/ArrayListExample/AccessControlSystem.java
package ArrayListExample;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class AccessControlSystem {
    // 知识点：ArrayList 动态管理名单
    private ArrayList<BaseDoorCard> whitelist = new ArrayList<>(); 
    
    // 知识点：封装门禁硬件（读卡器）
    private CardReader reader = new CardReader();

    public void registerCard(BaseDoorCard card) {
        whitelist.add(card); // add 操作 <1>
        System.out.println("系统：已录入卡片 " + card.getOwnerName());
    }

    public void executeBatchCheck() {
        System.out.println("\n--- 启动批量校验 (当前人数: " + whitelist.size() + ") ---");  // 取数量 <2>
        for (BaseDoorCard card : whitelist) {  // 循环遍历 <3>
            reader.readCard(card); // 调用读卡器处理单次刷卡
        }
    }
}
/**
 * 读卡器服务类：系统核心调度员
 * 多态设计：它可以接收 StudentCard，也可以接收 AdminCard，甚至未来的 TeacherCard
 * 职责：记录时间、触发物理校验、识别特殊身份、展示多态权限
 * 异常：捕获 validate() 异常并处理
 */
class CardReader {
    // 这里传入的是 BaseDoorCard，展示了多态的兼容性
    public void readCard(BaseDoorCard card) {
        // 1. 【时间记录】记录刷卡的“那一瞬间”
        // yyyy-MM-dd HH:mm:ss 是固定写法，将 Date 转换为人能看懂的文字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeLog = sdf.format(new Date());
        System.out.println("\n[" + timeLog + "] 读卡器感应中...");

        try {
            card.validate();
        } catch(DoorException e) {
            // 捕获业务异常
            System.out.println("【安全拦截】门禁逻辑错误：" + e.getMessage());
        } catch(Exception e) {
            // 捕获其他意料之外的异常
            System.out.println("【致命故障】系统崩溃：" + e.getMessage());
        } finally {
            // 无论成功还是失败，都要重置读卡器状态
            System.out.println(">> [状态流] 读卡器指示灯已重置为绿色闲置状态。");
        }
    }
}
