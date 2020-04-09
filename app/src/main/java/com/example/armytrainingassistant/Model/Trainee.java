package com.example.armytrainingassistant.Model;

import androidx.annotation.NonNull;

public class Trainee {

    private String lastName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MachineGunner{");
        sb.append("lastName='").append(lastName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", battalion='").append(battalion).append('\'');
        sb.append(", rank='").append(rank).append('\'');
        sb.append(", weaponSystem='").append(weaponSystem).append('\'');
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }

    private String firstName;
    private String company;
    private String battalion;
    private String rank;
    private String weaponSystem;
    private int score;

    public Trainee() {

    }


    public Trainee(String lastName, String firstName,
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
