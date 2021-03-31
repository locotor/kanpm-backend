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

    private String description;

    private Integer priority;

    @TableField("is_complete")
    private Boolean isComplete;

    @TableField("next_id")
    private String nextId;

    @TableField("stack_id")
    private String stackId;

    @TableField("creator_id")
    private String creatorId;

    @TableField("principal_user_id")
    private String principalUserId;

    @TableField("create_time")
    private Date createTime;

    @TableField("start_time")
    private Date startTime;

    @TableField("end_time")
    private Date endTime;

    @TableField("completed_time")
    private Date completedTime;

    public Task(String stackId, String description) {
        this.description = description;
        this.stackId = stackId;
    }

}
