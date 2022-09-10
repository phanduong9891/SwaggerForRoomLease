package com.axonactive.roomLeaseManagement.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message){super(message);}
}
