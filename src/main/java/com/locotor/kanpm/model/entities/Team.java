package com.locotor.kanpm.model.entities;

import java.sql.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NonNull;

@Data
public class Team {
    
    @NonNull
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("team_name")
    private String teamName;

    @TableField("create_time")
    private Date createTime;

    @TableField("creator_id")
    private String creatorId;

    @TableField("owner_id")
    private String ownerId;

    private String description;

    public Team(String teamName, String ownerId, String description) {
        this.teamName = teamName;
        this.ownerId = ownerId;
        this.description = description;
        this.createTime = new Date(new java.util.Date().getTime());
    }

    public Team(String id, String teamName, String ownerId, String description) {
        this.id = id;
        this.teamName = teamName;
        this.ownerId = ownerId;
        this.description = description;
        this.createTime = new Date(new java.util.Date().getTime());
    }

    public Team(String id, String teamName, Date createTime, String creatorId, String ownerId, String description) {
        this.id = id;
        this.teamName = teamName;
        this.createTime = createTime;
        this.creatorId = creatorId;
        this.ownerId = ownerId;
        this.description = description;
    }

}