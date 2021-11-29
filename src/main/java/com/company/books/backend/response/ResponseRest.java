package com.company.books.backend.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseRest {

    private List<HashMap<String,String>> metadata = new ArrayList<HashMap<String, String>>();

    public List<HashMap<String,String>> getMetadata(){
        return metadata;
    }

    public void setMetadata(String type, String code, String date){
        HashMap<String, String> map = new HashMap<String,String>();
        map.put("type",type);
        map.put("code", code);
        map.put("dato", date);

        metadata.add(map);
    }
}
