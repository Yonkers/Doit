/**
 *
 */
package com.yong.doit.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author lyjiang
 * @description 事件类型，如个人，工作，生活，学习
 */
@DatabaseTable(tableName = "event_type")
public class EventType {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

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

}
