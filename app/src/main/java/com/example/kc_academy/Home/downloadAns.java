package com.example.kc_academy.Home;

class downloadAns {

    String Title,Url;

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

    public downloadAns(String Title, String Url){
        this.Url=Url;
        this.Title=Title;
    }

}
