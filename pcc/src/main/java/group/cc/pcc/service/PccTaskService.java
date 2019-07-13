/*
 * Copyright (c) 2019 YuanLi. All rights reserved.
 */

package group.cc.pcc.service;
import group.cc.pcc.model.PccTask;
import group.cc.core.Service;

import java.util.List;
import java.util.Map;


/**
 * @author yuanli
 * @date 2019/05/31
 */
public interface PccTaskService extends Service<PccTask> {

    List<PccTask> listDay(String day);

    List<Map<String, Object>> willDeadList();

    List<PccTask> listDayUser(String day, Integer pccUserId);

    void deleteImitate(Integer id);

    Map<String, Long> counts(Integer pccUserId, String startDay, String endDay);

    void isRemind(Integer id);
}
