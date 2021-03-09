package com.locotor.kanpm.model.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NonNull;

import java.sql.Date;

@Data
public class TeamMember {

    @NonNull
    @TableId(type = IdType.ASSIGN_ID)
    private  String id;

    @TableField("team_id")
    private Date teamId;

    @TableField("member_id")
    private Date memberId;

}
