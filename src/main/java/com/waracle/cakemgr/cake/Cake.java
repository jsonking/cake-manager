package com.waracle.cakemgr.cake;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@EqualsAndHashCode
@Entity
public class Cake {

    protected Cake() {}

    public Cake(String name, String description, String imageURL) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Getter
    @Column(nullable = false, length = 100)
    private String description;

    @Getter
    @Column(nullable = false, length = 300)
    private String imageURL;

}
