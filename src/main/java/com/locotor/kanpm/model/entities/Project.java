package com.locotor.kanpm.model.entities;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NonNull;

@Data
public class Project {

    @NonNull
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("project_name")
    private String projectName;

    private String avatar;

    @TableField("owner_id")
    private String ownerId;

    @TableField("team_id")
    private String teamId;

    @TableField("create_id")
    private String creatorId;

    @TableField("create_time")
    private Date createTime;

    @TableField("is_archived")
    private Boolean isArchived;

    private String description;

    public Project(String projectName, String teamId) {
        this.projectName = projectName;
        this.teamId = teamId;
    }

}