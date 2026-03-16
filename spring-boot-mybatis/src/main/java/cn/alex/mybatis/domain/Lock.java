package cn.alex.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by WCY on 2021/8/3
 */

@Getter
@Setter
@ToString
public class Lock {
    /**
     * 锁编号
     */
    private Integer id;

    /**
     * 锁名
     */
    private String lockName;

    private List<Key> keyList;
}
