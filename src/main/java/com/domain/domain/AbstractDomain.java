package main.java.com.domain.domain;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractDomain implements Serializable, Comparable<AbstractDomain> {

    private long id; // 主键
    private Date itemCreateTime;    // 数据的创建时间
    private Date lastModifyTime;    // 数据的修改时间


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getItemCreateTime() {
        return itemCreateTime;
    }

    public void setItemCreateTime(Date itemCreateTime) {
        this.itemCreateTime = itemCreateTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public int compareTo(AbstractDomain o) {
        return o != null ? Long.valueOf(id).compareTo(o.id) : 1;
    }

}
