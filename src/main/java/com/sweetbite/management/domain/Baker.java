package com.sweetbite.management.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "baker")

@JsonPropertyOrder({
    "id",
    "firstName",
    "lastName",
    "phone",
    "oven",
    "bakingSkills"
})

public class Baker {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "oven_id")
    private Oven oven;

    @ManyToMany
    @JoinTable(
        name = "baker_baking_skill",
        joinColumns = @JoinColumn(name = "baker_id"),
        inverseJoinColumns = @JoinColumn(name = "baking_skill_id")
    )
    private List<BakingSkill> bakingSkills = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Oven getOven() {
        return oven;
    }

    public void setOven(Oven oven) {
        this.oven = oven;
    }

    public List<BakingSkill> getBakingSkills() {
        return bakingSkills;
    }

    public void setBakingSkills(List<BakingSkill> bakingSkills) {
        this.bakingSkills = bakingSkills;
    }
}