package cn.andylhl.crm.settings.service;

import cn.andylhl.crm.settings.domain.DicValue;

import java.util.List;
import java.util.Map;

/***
 * @Title: DicService
 * @Description: 数据字典业务
 * @author: lhl
 * @date: 2020/10/15 21:56
 */
public interface DicService {

    /**
     * 读取数据字典
     * @return
     */
    Map<String, List<DicValue>> getAll();
}
