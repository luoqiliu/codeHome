package com.holydota.bean.user;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class BaseFreezeRule implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final int   USE              = 1; // 启用
    public static final int   NOT_USE          = 0; // 禁用

    public static final byte  TYPE_LIMIT       = 1; // 限制抢/发单
    public static final byte  TYPE_WARN        = 2; // 警告
    public static final byte  TYPE_FREEZE      = 3; // 冻结

    private int               ruleId;
    private int               timeRange;
    private String            timeRangeUnit;
    private int               complaintCount;
    private int               freezeTime;
    private String            freezeTimeUnit;

    private long              oprId;
    private String            oprName;
    private Date              createTime;
    private int               priority;
    private Integer           isUse;                // 是否启用
    private Byte              type;                 // 处罚类型
    private Integer           cityId;
    private String            cityName;

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public int getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(int timeRange) {
        this.timeRange = timeRange;
    }

    public String getTimeRangeUnit() {
        return timeRangeUnit;
    }

    public void setTimeRangeUnit(String timeRangeUnit) {
        this.timeRangeUnit = timeRangeUnit;
    }

    public int getComplaintCount() {
        return complaintCount;
    }

    public void setComplaintCount(int complaintCount) {
        this.complaintCount = complaintCount;
    }

    public int getFreezeTime() {
        return freezeTime;
    }

    public void setFreezeTime(int freezeTime) {
        this.freezeTime = freezeTime;
    }

    public String getFreezeTimeUnit() {
        return freezeTimeUnit;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        if (StringUtils.isBlank(cityName)) {
            return "默认";
        }

        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setFreezeTimeUnit(String freezeTimeUnit) {
        this.freezeTimeUnit = freezeTimeUnit;
    }

    public long getOprId() {
        return oprId;
    }

    public void setOprId(long oprId) {
        this.oprId = oprId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOprName() {
        return oprName;
    }

    public void setOprName(String oprName) {
        this.oprName = oprName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getInfo() {
        String who = "乘客";
        String action = "封停";
        String why = "评价违约";
        if (this instanceof DriverFreezeRule) {
            who = "司机";
            action = "限制抢单";
            why = "评价违约";
        }
        String rst = "";
        String t = "次(或以上)， ";
        if (type == null || type == TYPE_LIMIT) {
            rst = "将被" + action + freezeTime + getTimeUnit(freezeTimeUnit);
        } else if (type == TYPE_WARN) {
            rst = "将被警告!";
            t = "次，";
        } else if (type == TYPE_FREEZE) {
            rst = "账号将被冻结！";
        }
        return who + "在" + timeRange + getTimeUnit(timeRangeUnit) + "内被" + why + complaintCount + t + rst;
    }

    private String getTimeUnit(String unit) {
        if (StringUtils.isBlank(unit)) {
            return "";
        }
        return "";
    }

    public String getTypeName() {
        String name = "";
        if (type == null || type == TYPE_LIMIT) {
            name = "限制发/抢单";
        } else if (type == TYPE_WARN) {
            name = "警告";
        } else if (type == TYPE_FREEZE) {
            name = "冻结";
        }

        return name;
    }
}
