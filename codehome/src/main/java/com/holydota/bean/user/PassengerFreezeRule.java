package com.holydota.bean.user;

public class PassengerFreezeRule extends BaseFreezeRule {

    private Integer           isNotVip;
    private String            level;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getIsNotVip() {
        return isNotVip;
    }

    public void setIsNotVip(Integer isNotVip) {
        this.isNotVip = isNotVip;
    }

}
