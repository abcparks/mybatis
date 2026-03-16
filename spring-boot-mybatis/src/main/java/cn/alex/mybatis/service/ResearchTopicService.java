package cn.alex.mybatis.service;

import cn.alex.mybatis.domain.ResearchTopic;

/**
 * Created by WCY on 2021/10/22
 */
public interface ResearchTopicService {


    int deleteByPrimaryKey(Long id);

    int insert(ResearchTopic record);

    ResearchTopic selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ResearchTopic record);

}

