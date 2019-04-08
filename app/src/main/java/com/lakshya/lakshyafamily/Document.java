package com.lakshya.lakshyafamily;

public class Document {

    String doc_name,details;

    public Document(String doc_name, String details) {
        this.doc_name = doc_name;
        this.details = details;
    }

    public Document() {
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
