package com.sweetbite.management.domain;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "oven")
public class Oven {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int ovenNumber;
    private String name;

    public UUID getId() {
        return id;
    }

    public int getOvenNumber() {
        return ovenNumber;
    }

    public void setOvenNumber(int ovenNumber) {
        this.ovenNumber = ovenNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}