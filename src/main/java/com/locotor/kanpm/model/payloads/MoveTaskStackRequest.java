package com.locotor.kanpm.model.payloads;

import com.locotor.kanpm.model.entities.TaskStack;

import lombok.Data;

@Data
public class MoveTaskStackRequest {
    
    private TaskStack oldPrevious;
    
    private TaskStack newPrevious;
    
    private String newNextId;
    
    private TaskStack movedStack;

}
