package com.example.m240range;

public class MachineGunner {

    private String lastName;
    private String firstName;
    private String company;
    private String battalion;
    private String rank;
    private String weaponSystem;
    private int score;

    public MachineGunner(String lastName, String firstName,
                         String company, String battalion,
                         String rank, String weaponSystem, int score) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.company = company;
        this.battalion = battalion;
        this.rank = rank;
        this.weaponSystem = weaponSystem;
        this.score = score;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBattalion() {
        return battalion;
    }

    public void setBattalion(String battalion) {
        this.battalion = battalion;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getWeaponSystem() {
        return weaponSystem;
    }

    public void setWeaponSystem(String weaponSystem) {
        this.weaponSystem = weaponSystem;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
