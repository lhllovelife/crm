package cn.andylhl.crm.settings;

import cn.andylhl.crm.exception.ClueExecption;
import cn.andylhl.crm.exception.CustomerExecption;
import cn.andylhl.crm.utils.UUIDUtil;
import cn.andylhl.crm.workbench.dao.ClueActivityRelationDao;
import cn.andylhl.crm.workbench.dao.ClueRemarkDao;
import cn.andylhl.crm.workbench.domain.Clue;
import cn.andylhl.crm.workbench.domain.Tran;
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
        ClueService service = (ClueService) ac.getBean("clueServiceImpl");
        String[] ids = {"A0006", "A0007"};
        try {
            service.deleteByIds(ids);
        } catch (Exception e) {
            System.out.println("------有异常出现-------");
            e.printStackTrace();
        }
    }

    @Test
    public void Test02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClueService service = (ClueService) ac.getBean("clueServiceImpl");
        Tran tran = new Tran();
        tran.setId(UUIDUtil.getUUID());
        tran.setMoney("1800");
        tran.setName("服务器租赁");
        tran.setExpectedDate("2020-10-26");
        tran.setStage("01-成交");
        tran.setActivityId("868ea1af2de24d9490ae3827751ec198");
        try {
            service.convert("27447cada64a4a449375226e6bfa8631", tran, "张三");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
