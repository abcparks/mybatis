package cn.alex.mybatis.service.impl;

import cn.alex.mybatis.domain.ResearchTopic;
import cn.alex.mybatis.mapper.ResearchTopicMapper;
import cn.alex.mybatis.service.ResearchTopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by WCY on 2021/10/22
 */
@Service
public class ResearchTopicServiceImpl implements ResearchTopicService {

    @Resource
    private ResearchTopicMapper researchTopicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return researchTopicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ResearchTopic record) {
        return researchTopicMapper.insert(record);
    }

    @Override
    public ResearchTopic selectByPrimaryKey(Long id) {
        return researchTopicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(ResearchTopic record) {
        return researchTopicMapper.updateByPrimaryKey(record);
    }
}





