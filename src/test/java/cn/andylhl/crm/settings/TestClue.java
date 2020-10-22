package cn.andylhl.crm.settings;

import cn.andylhl.crm.workbench.dao.ClueRemarkDao;
import cn.andylhl.crm.workbench.service.ClueService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.jsp.JspEngineInfo;

/***
 * @Title: TestClue
 * @Description: 测试线索模块
 * @author: lhl
 * @date: 2020/10/22 22:25
 */
public class TestClue {

    @Test
    public void Test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClueRemarkDao clueRemarkDao = (ClueRemarkDao) ac.getBean("clueRemarkDao");
        String[] ids = {"A0004"};
        int count1 = clueRemarkDao.getClueRemarkSizeByIds(ids);
        int count2 = clueRemarkDao.deleteClueRemarkByIds(ids);
        System.out.println("查询数目: " + count1);
        System.out.println("删除数目: " + count2);
    }
}
