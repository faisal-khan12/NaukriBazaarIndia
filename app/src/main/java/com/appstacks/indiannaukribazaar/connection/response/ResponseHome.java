package com.appstacks.indiannaukribazaar.connection.response;


import com.appstacks.indiannaukribazaar.model.News;
import com.appstacks.indiannaukribazaar.model.Topic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseHome implements Serializable {

    public String status = "";
    public List<News> featured = new ArrayList<>();
    public List<Topic> topic = new ArrayList<>();

}
