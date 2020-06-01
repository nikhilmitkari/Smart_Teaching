package com.example.kc_academy;

public class VideoDownModel {

    String Name,VLink;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVLink() {
        return VLink;
    }

    public void setVLink(String vLink) {
        VLink = vLink;
    }

    public VideoDownModel(String Name, String VLink){
        this.VLink=VLink;
        this.Name=Name;
    }

}
