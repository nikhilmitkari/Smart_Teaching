package com.example.kc_academy.Home;

class DataModel {
    String Title,Url,VLink;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }



    public DataModel(String Title, String Url){
        this.Url=Url;
        this.Title=Title;
    }
}
