package com.locotor.kanpm.model.payloads;

import com.locotor.kanpm.model.entities.Task;

import lombok.Data;

@Data
public class MoveTaskRequest {
    
    private Task oldPrevious;
    
    private Task newPrevious;
    
    private String newNextId;
    
    private Task movedTask;

}
