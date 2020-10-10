package cn.andylhl.crm.vo;

import java.util.List;

/***
 * @Title: Pagination
 * @Description: 返回的分页对象
 * @author: lhl
 * @date: 2020/10/10 19:19
 */
public class PaginationVO<T> {

    private Integer total;
    private List<T> dataList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
