package com.appstacks.indiannaukribazaar.connection.response;

import com.appstacks.indiannaukribazaar.model.User;

import java.io.Serializable;

public class ResponseCode implements Serializable {
    public String code = "";
    public User user = new User();
}
