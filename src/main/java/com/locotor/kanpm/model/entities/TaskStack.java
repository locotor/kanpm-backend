package com.locotor.kanpm.model.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class TaskStack {

    @NonNull
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("stack_name")
    private String stackName;

    @TableField("creator_id")
    private String creatorId;

    @TableField("project_id")
    private String projectId;

    @TableField("create_time")
    private Date createTime;

    @TableField("sort_by")
    private Integer sortBy;

    private Integer order;

    public TaskStack(String projectId,String stackName){
        this.projectId = projectId;
        this.stackName = stackName;
    }

}
