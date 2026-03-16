package cn.alex.mybatis.mapper;

import cn.alex.mybatis.domain.ResearchTopic;

/**
 * Created by WCY on 2021/10/25
 */
public interface ResearchTopicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResearchTopic record);

    ResearchTopic selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ResearchTopic record);

}