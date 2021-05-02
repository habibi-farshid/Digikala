package com.example.farshid.digikala.Modle;

public class Model_listProduct {

    private String Name;
    private String  Cat;
    private String  image;



    public Model_listProduct(String name, String image, String cat) {
        Name = name;
        this.image = image;
        Cat = cat;
    }


    public void setCat(String cat) {
        Cat = cat;
    }

    public String getCat() {
        return Cat;
    }



    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }



    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }


}
