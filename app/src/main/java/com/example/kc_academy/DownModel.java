package com.example.kc_academy;

public class DownModel {

    String Name,Link,VLink;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }



    public DownModel(String Name, String Link){
        this.Link=Link;
        this.Name=Name;
    }

}