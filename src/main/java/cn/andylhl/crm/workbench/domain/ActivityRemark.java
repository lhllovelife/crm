package cn.andylhl.crm.workbench.domain;

/***
 * @Title: ActivityRemark
 * @Description: 市场活动备注bean
 * @author: lhl
 * @date: 2020/10/9 15:03
 */
public class ActivityRemark {

    private String id; //主键
    private String noteContent; //备注内容
    private String createTime; //创建时间 19位 年月日时分秒
    private String createBy; //创建人 varchar(255)
    private String editTime; //修改时间 19位
    private String editBy; //修改人 varchar(255)
    private String editFlag; //修改标记 cahr(1)
    private String activityId; //市场活动id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "ActivityRemark{" +
                "id='" + id + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editFlag='" + editFlag + '\'' +
                ", activityId='" + activityId + '\'' +
                '}';
    }
}
