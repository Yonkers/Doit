/**
 *
 */
package com.yong.doit.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author lyjiang
 * @dscription 事件进度跟踪依据，比如看书按页码，听力按时间等
 */
@DatabaseTable(tableName = "progress_identify")
public class ProgressIdentify {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    public ProgressIdentify() {
        super();
    }

    public ProgressIdentify(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return name;
    }

}
