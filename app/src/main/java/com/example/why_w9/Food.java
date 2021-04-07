package com.example.why_w9;

public class Food {
    private String Name, Image, Description, Price, Discount, MenuId;

    public Food() {
    }

    public Food(String Name, String Image, String Description, String Price,  String MenuId) {
        this.Name = Name;
        this.Image = Image;
        this.Description = Description;
        this.Price = Price;
        this.Discount = "0.00"; //set to default 0.00. We can include set discount option in the future.
        this.MenuId = MenuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        Description = Description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        Price = Price;
    }

    public String getDiscount() { //uncommented. Plan to use discounts in future.
        return Discount;
    }

    public void setDiscount(String discount) { //uncommented. Plan to use discounts in future.
        Discount = discount;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String MenuId) {
        MenuId = MenuId;
    }
}