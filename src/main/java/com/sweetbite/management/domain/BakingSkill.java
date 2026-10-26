package com.sweetbite.management.domain;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "baking_skill")
public class BakingSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "bakingSkills")
    @JsonIgnore
    private List<Baker> bakers;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Baker> getBakers() {
        return bakers;
    }

    public void setBakers(List<Baker> bakers) {
        this.bakers = bakers;
    }
}