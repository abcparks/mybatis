package cn.alex.mybatis.plus.domain;

import cn.alex.mybatis.plus.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by WCY on 2021/4/5
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User {
    /*
        @TableId 对应数据库中的主键
        type 主键策略
        IdType.AUTO 自增策略, 数据库中必须选择自增
        IdType.NONE 未设置主键
        IdType.INPUT 手动输入, 一旦手动输入后必须自己配置id
        IdType.ID_WORKER 默认的全局id, 雪花算法生成
        IdType.UUID 全局唯一id, uuid
        IdType.ID_WORKER_STR ID_WORKER字符串表示
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private SexEnum sex;

    private Integer age;

    @TableField("email")
    private String email;

    @Version // 乐观锁字段
    private Integer version;

    // 字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;
}
