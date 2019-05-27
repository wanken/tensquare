package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author waver
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    /**
     * 查询热门职位
     * @param state 状态
     * @return List
     */
    List<Recruit> findTop6ByStateOrOrderByCreatetime(String state);

    /**
     * 倒序热门职位
     * @param state 状态
     * @return List
     */
    List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);
	
}
