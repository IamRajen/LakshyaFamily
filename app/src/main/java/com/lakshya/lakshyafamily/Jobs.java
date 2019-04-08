package com.lakshya.lakshyafamily;

public class Jobs {

    String title,link,details;

    public Jobs(String title, String link, String details) {
        this.title = title;
        this.link = link;
        this.details = details;
    }

    public Jobs() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
