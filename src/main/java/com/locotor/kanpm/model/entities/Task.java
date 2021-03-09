package com.locotor.kanpm.model.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Task {

    @NonNull
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("task_name")
    private String taskName;

    @TableField("task_priority")
    private Integer taskPriority;

    @TableField("stack_id")
    private String stackId;

    @TableField("creator_id")
    private String creatorId;

    @TableField("principal_user_id")
    private String principalUserId;

    @TableField("task_description")
    private String taskDescription;

    @TableField("create_time")
    private Date createTime;

    @TableField("start_time")
    private Date startTime;

    @TableField("end_time")
    private Date endTime;

    @TableField("completed_time")
    private Date completedTime;

    public Task(String taskName,String stackId){
        this.taskName = taskName;
        this.stackId = stackId;
    }

}
