package com.locotor.kanpm.model.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NonNull;

@Data
public class Role {
    @NonNull
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    
    @NonNull
    @TableField("role_name")
    private String roleName;
}