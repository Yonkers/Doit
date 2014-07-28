/**
 *
 */
package com.yong.doit.data.bean;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author lyjiang
 * @description 一个事件，分成task来完成
 */
@DatabaseTable(tableName = "event")
public class Event {

    public Event() {
    }

    public Event(String name) {
        super();
        this.name = name;
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private Date startDate;// 开始时间

    @DatabaseField
    private int peroid;//事件周期

    @DatabaseField
    private int periodType;//事件周期类型，日，周，月

    @DatabaseField
    private String name;// 事件名称

    @DatabaseField
    private String note;// 描述

    /**
     * 事件分类
     */
    @DatabaseField(canBeNull = true, columnName = "eventType_id", foreign = true, foreignColumnName = "id", columnDefinition = "integer references event_type(id) on delete cascade")
    private EventType eventType;

    /**
     * 事件模式,进度模式，记忆模式
     */
    @DatabaseField
    private int eventMode;

    /**
     * 进度跟踪方法
     */
    @DatabaseField(canBeNull = false, columnName = "progress_identify_id", foreign = true, foreignColumnName = "id", columnDefinition = "integer references progress_identify(id) on delete cascade")
    private ProgressIdentify pIdentify;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getEventMode() {
        return eventMode;
    }

    public void setEventMode(int eventMode) {
        this.eventMode = eventMode;
    }

    public ProgressIdentify getpIdentify() {
        return pIdentify;
    }

    public void setpIdentify(ProgressIdentify pIdentify) {
        this.pIdentify = pIdentify;
    }

    public int getPeroid() {
        return peroid;
    }

    public void setPeroid(int peroid) {
        this.peroid = peroid;
    }

    public int getPeriodType() {
        return periodType;
    }

    public void setPeriodType(int periodType) {
        this.periodType = periodType;
    }

    @Override
    public String toString() {

        return name;
    }

    public String print() {
        String out = "Event [id=" + id + ", startDate=" + startDate + ", peroid="
                + peroid + ", periodType=" + periodType + ", name=" + name
                + ", note=" + note + ", eventType=" + eventType
                + ", eventMode=" + eventMode + ", pIdentify=" + pIdentify + "]";
        System.out.println(out);
        return out;
    }
}
