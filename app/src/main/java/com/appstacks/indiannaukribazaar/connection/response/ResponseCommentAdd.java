package com.appstacks.indiannaukribazaar.connection.response;

import com.appstacks.indiannaukribazaar.model.Comment;

import java.io.Serializable;

public class ResponseCommentAdd implements Serializable {
    public String code = "";
    public Comment comment = new Comment();
}
