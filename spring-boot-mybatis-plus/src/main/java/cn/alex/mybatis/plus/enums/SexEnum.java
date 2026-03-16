package cn.alex.mybatis.plus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Created by WCY on 2022/3/8
 */
@Getter
public enum SexEnum {
    /**
     * 男
     */
    MALE(1, "男"),

    /**
     * 女
     */
    FEMALE(2, "女");

    // 将注解所标识的属性值存储到数据库中
    @EnumValue
    private final Integer sex;

    private final String sexName;

    private SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
