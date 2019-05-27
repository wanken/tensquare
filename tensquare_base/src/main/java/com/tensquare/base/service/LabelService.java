package com.tensquare.base.service;

import cn.hutool.core.util.StrUtil;
import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author waver
 * @date 2019.5.27 10:23
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     *
     * @return List<Label>
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据Id获取Label
     *
     * @param id label id
     * @return Label
     */
    public Label findById(String id) {
        return labelDao.findById(id).orElse(null);
    }

    /**
     * 添加标签
     *
     * @param label 标签
     * @return boolean
     */
    public boolean add(Label label) {
        label.setId(idWorker.nextId() + "");
        Label save = labelDao.save(label);
        return save != null;
    }


    /**
     * 根据Id删除标签
     *
     * @param id 标签Id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public boolean update(Label label) {
        Label update = labelDao.save(label);
        return update == null;
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll((Specification<Label>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(label.getLabelName())) {
                predicates.add(criteriaBuilder.like(root.get("labelName").as(String.class), "%" + label.getLabelName() + "%"));
            }
            if (StringUtils.hasLength(label.getState())) {
                predicates.add(criteriaBuilder.equal(root.get("state").as(String.class), label.getState()));
            }
            Predicate[] predicatesArr = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicatesArr);
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return labelDao.findAll((Specification<Label>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(label.getLabelName())) {
                predicates.add(criteriaBuilder.like(root.get("labelName").as(String.class), "%" + label.getLabelName() + "%"));
            }
            if (StringUtils.hasLength(label.getState())) {
                predicates.add(criteriaBuilder.equal(root.get("state").as(String.class), label.getState()));
            }
            Predicate[] predicatesArr = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicatesArr);
        }, pageable);
    }
}
