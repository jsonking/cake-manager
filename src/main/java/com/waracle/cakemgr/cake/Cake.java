package com.waracle.cakemgr.cake;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notEmpty;

@ToString
@EqualsAndHashCode
@Getter
@Entity
public class Cake {

    static final int MAX_NAME_LENGTH = 100, MAX_DESCRIPTION_LENGTH = 100, MAX_IMAGE_URL_LENGTH = 300;

    private Cake() {}

    public Cake(String name, String description, String imageURL) {
        isTrue(notEmpty(name).length()<=MAX_NAME_LENGTH);
        isTrue(notEmpty(description).length()<=MAX_DESCRIPTION_LENGTH);
        isTrue(notEmpty(imageURL).length()<=MAX_IMAGE_URL_LENGTH);

        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    @Column(nullable = false, length = MAX_DESCRIPTION_LENGTH)
    private String description;

    @Column(nullable = false, length = MAX_IMAGE_URL_LENGTH)
    private String imageURL;

}

