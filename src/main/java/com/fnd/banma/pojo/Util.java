package com.fnd.banma.pojo;

/**
 * 该类为共有属性父类
 */
public class Util {

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Util{" +
                "createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
