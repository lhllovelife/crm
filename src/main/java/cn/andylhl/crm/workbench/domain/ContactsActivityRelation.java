package cn.andylhl.crm.workbench.domain;

/***
 * @Title: ContactsActivityRelation
 * @Description: 联系人市场活动关系实体类
 * @author: lhl
 * @date: 2020/10/25 22:02
 */
public class ContactsActivityRelation {

    private String id; //主键id
    private String contactsId; //联系人id
    private String activityId; //市场活动id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactsId() {
        return contactsId;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "ContactsActivityRelation{" +
                "id='" + id + '\'' +
                ", contactsId='" + contactsId + '\'' +
                ", activityId='" + activityId + '\'' +
                '}';
    }
}
