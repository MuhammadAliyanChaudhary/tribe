package com.example.tribe.Model;

public class DependentModel {

    String dob, name, age, allergy, diet, dislikes, likes, pent, profileImage, shoe, top;

    public DependentModel(String dob, String name, String age, String allergy, String diet, String dislikes, String likes, String pent, String profileImage, String shoe, String top) {
        this.dob = dob;
        this.name = name;
        this.age = age;
        this.allergy = allergy;
        this.diet = diet;
        this.dislikes = dislikes;
        this.likes = likes;
        this.pent = pent;
        this.profileImage = profileImage;
        this.shoe = shoe;
        this.top = top;
    }

    public DependentModel() {
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getPent() {
        return pent;
    }

    public void setPent(String pent) {
        this.pent = pent;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getShoe() {
        return shoe;
    }

    public void setShoe(String shoe) {
        this.shoe = shoe;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }
}
