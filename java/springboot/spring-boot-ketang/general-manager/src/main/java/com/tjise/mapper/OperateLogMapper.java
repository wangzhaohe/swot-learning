//@+leo-ver=5-thin
//@+node:swot.20250826211301.1: * @file src/main/java/com/tjise/mapper/OperateLogMapper.java
//@+doc
// .定义操作日志表对应的 insert mapper
// [source,java]
// ----
//@@c
//@@language java
package com.tjise.mapper;
 
import com.tjise.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
 
@Mapper
public interface OperateLogMapper {
    //插入日志数据
    @Insert("insert into operate_log (operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateUser}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    public void insert(OperateLog log);  // 从 service 传入实体类对象
}
//@+doc
// ----
//@-leo
