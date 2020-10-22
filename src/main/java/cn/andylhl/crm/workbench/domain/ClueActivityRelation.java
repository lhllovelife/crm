package cn.andylhl.crm.workbench.domain;

/***
 * @Title: ClueActivityRelation
 * @Description: 线索市场活动关系bean
 * @author: lhl
 * @date: 2020/10/22 21:56
 */
public class ClueActivityRelation {

    private String id;
    private String clueId;
    private String activityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClueId() {
        return clueId;
    }

    public void setClueId(String clueId) {
        this.clueId = clueId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "ClueActivityRelation{" +
                "id='" + id + '\'' +
                ", clueId='" + clueId + '\'' +
                ", activityId='" + activityId + '\'' +
                '}';
    }
}
