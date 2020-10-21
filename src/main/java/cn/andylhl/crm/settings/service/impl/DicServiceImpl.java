package cn.andylhl.crm.settings.service.impl;

import cn.andylhl.crm.settings.dao.DicTypeDao;
import cn.andylhl.crm.settings.dao.DicValueDao;
import cn.andylhl.crm.settings.domain.DicType;
import cn.andylhl.crm.settings.domain.DicValue;
import cn.andylhl.crm.settings.service.DicService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @Title: DicServiceImpl
 * @Description: 数据字典业务实现类
 * @author: lhl
 * @date: 2020/10/15 21:57
 */
public class DicServiceImpl implements DicService {
    private DicTypeDao dicTypeDao;
    private DicValueDao dicValueDao;

    public void setDicTypeDao(DicTypeDao dicTypeDao) {
        this.dicTypeDao = dicTypeDao;
    }

    public void setDicValueDao(DicValueDao dicValueDao) {
        this.dicValueDao = dicValueDao;
    }

    /**
     * 读取数据字典
     * @return
     */
    @Override
    public Map<String, List<DicValue>> getAll() {
        Map<String, List<DicValue>> map = new HashMap<>();
        //获取所有的类型
        List<DicType> typeList = dicTypeDao.getAll();
        //通过类型获取每个类型所对应的所有value, 将其封装到一个list中
        for(DicType dicType : typeList){
            List<DicValue> valueList = dicValueDao.getAllByCode(dicType.getCode());
            map.put(dicType.getCode() + "List", valueList);
        }
        return map;
    }
}
