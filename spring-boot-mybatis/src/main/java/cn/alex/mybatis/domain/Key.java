package cn.alex.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by WCY on 2021/8/3
 */

@Getter
@Setter
@ToString
public class Key {
    /**
     * 钥匙编号
     */
    private Integer id;

    /**
     * 钥匙名
     */
    private String keyName;

    private Integer lockId;

    private Lock lock;
}
