package cn.andylhl.crm.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

/***
 * @Title: WebUtil
 * @Description: 反射机制
 * @author: lhl
 * @date: 2020/9/17 17:03
 */
public class WebUtil {
    private WebUtil(){
    }

    /**
     * 将request范围中存储的表单中的数据封装到javabean对象中
     * @param request 存储表单数据的request
     * @param obj javabean
     */
    public static void makeRequestToObject(HttpServletRequest request, Object obj){
        //获取javabean的字节码文件
        Class c = obj.getClass();
        //获取表单中所有name属性的值
        Enumeration<String> fieldNames = request.getParameterNames();

        //存在正常异常：表单中数据 有不只是bean中属性，例如cheeckpswd，这时候会没有
        // 这个set方法，出现异常，不过属于正常情况。
        try {
            while (fieldNames.hasMoreElements()){
                //获取属性名
                String filedName = fieldNames.nextElement();
                //获取方法名
                String methodName = "set" + filedName.toUpperCase().charAt(0) + filedName.substring(1);
                //获取要调用的方法
                Method setMethod = c.getDeclaredMethod(methodName, String.class);
                //调用set方法
                setMethod.invoke(obj, request.getParameter(filedName));
            }
        } catch (Exception e){

        }
    }
}
