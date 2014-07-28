/**
 *
 */
package com.yong.doit.data.bean;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author lyjiang
 * @description 每天任务
 */
@DatabaseTable(tableName = "task")
public class Task {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String note;//说明

    @DatabaseField
    private Date date;//执行日期

    @DatabaseField(canBeNull = false, columnName = "event_id", foreign = true, foreignColumnName = "id", columnDefinition = "integer references event(id) on delete cascade")
    private Event event;//所属事件

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String print() {
        return "Task [id=" + id + ", name=" + name + ", note=" + note
                + ", date=" + date + ", event=" + event + "]";
    }

}
