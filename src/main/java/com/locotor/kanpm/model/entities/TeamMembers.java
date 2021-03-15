package com.locotor.kanpm.model.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.locotor.kanpm.model.handler.StringArrayJoinTypeHandler;

import lombok.Data;
import lombok.NonNull;

@Data
public class TeamMembers {

    @NonNull
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("team_id")
    private String teamId;

    @TableField(value = "member_id", typeHandler = StringArrayJoinTypeHandler.class)
    private String[] memberId;

    public TeamMembers(String teamId, String[] memberId) {
        this.teamId = teamId;
        this.memberId = memberId;
    }

}
