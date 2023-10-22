package com.practicaltask.employeeaccess.exceptionhandler;

public class ResourceNotFoundExpection extends RuntimeException{

    public String msg = null;
    public ResourceNotFoundExpection(String msg){
        super(msg);
        this.msg = msg;
    }
}
