package cn.andylhl.crm.settings.domain;

/***
 * @Title: DicValue
 * @Description: 数据字典值表
 * @author: lhl
 * @date: 2020/10/15 21:45
 */
public class DicValue {

    private String id; //主键 主键，采用UUID
    private String value; //字典值
    private String text; //文本
    private String orderNo; //排序号
    private String typeCode; //字典类型编码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        return "DicValue{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", text='" + text + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", typeCode='" + typeCode + '\'' +
                '}';
    }
}
