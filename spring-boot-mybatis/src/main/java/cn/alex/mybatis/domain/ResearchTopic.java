package cn.alex.mybatis.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by WCY on 2021/10/25
 */
@Getter
@Setter
@ToString
public class ResearchTopic {
    /**
     * 主键
     */
    private Long id;

    /**
     * pdf文件流
     */
    private byte[] pdfcontent;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 是否有效, 0 无效 1 有效
     */
    private Integer isvalid;
}
