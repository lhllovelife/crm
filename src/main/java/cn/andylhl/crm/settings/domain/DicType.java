package cn.andylhl.crm.settings.domain;

/***
 * @Title: DicType
 * @Description: 数据字典类型bean
 * @author: lhl
 * @date: 2020/10/15 21:43
 */
public class DicType {

    private String code; //编码
    private String name; //名称
    private String description; //描述

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DicType{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
