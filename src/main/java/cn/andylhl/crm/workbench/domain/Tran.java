package cn.andylhl.crm.workbench.domain;

/***
 * @Title: Tran
 * @Description: 交易实体类
 * @author: lhl
 * @date: 2020/10/25 21:20
 */
public class Tran {

    private String id; //主键id
    private String owner; //所有者 32位
    private String money; //金额
    private String name;//交易名称
    private String expectedDate; //预计成交日期
    private String customerId; //客户id
    private String stage; //阶段
    private String type; //类型
    private String source; //来源
    private String activityId; //市场活动id
    private String contactsId; //联系人id
    private String createBy; //创建人
    private String createTime; //创建时间
    private String editBy; //修改人
    private String editTime; //修改时间
    private String description; //描述
    private String contactSummary; //联系纪要
    private String nextContactTime; //下次联系时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getContactsId() {
        return contactsId;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactSummary() {
        return contactSummary;
    }

    public void setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary;
    }

    public String getNextContactTime() {
        return nextContactTime;
    }

    public void setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime;
    }

    @Override
    public String toString() {
        return "Tran{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", money='" + money + '\'' +
                ", name='" + name + '\'' +
                ", expectedDate='" + expectedDate + '\'' +
                ", customerId='" + customerId + '\'' +
                ", stage='" + stage + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", activityId='" + activityId + '\'' +
                ", contactsId='" + contactsId + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", description='" + description + '\'' +
                ", contactSummary='" + contactSummary + '\'' +
                ", nextContactTime='" + nextContactTime + '\'' +
                '}';
    }
}
