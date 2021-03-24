package com.example.why_w9;

public class food_item{
    String name;
    String imageUrl;
    public food_item()
    {}
    public food_item(String name, String imageUrl)
    {
        this.name=name;
        this.imageUrl=imageUrl;

    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
