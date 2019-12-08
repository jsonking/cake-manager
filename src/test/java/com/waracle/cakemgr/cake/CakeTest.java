package com.waracle.cakemgr.cake;

import org.junit.Test;

import static com.waracle.cakemgr.cake.Cake.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class CakeTest {

    @Test(expected = NullPointerException.class)
    public void testNameCannotBeNull() {
        new Cake(null,"cake description", "http://image_url");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameCannotBeEmpty() {
        new Cake("","cake description", "http://image_url");
    }

    @Test(expected = NullPointerException.class)
    public void testDescriptionCannotBeNull() {
        new Cake("test cake",null, "http://image_url");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDescriptionCannotBeEmpty() {
        new Cake("test cake","", "http://image_url");
    }

    @Test(expected = NullPointerException.class)
    public void testImageURLCannotBeNull() {
        new Cake("test cake","cake description", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testImageURLCannotBeEmpty() {
        new Cake("test cake","cake description", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownWhenNameToLong() {
        new Cake(randomAlphabetic(MAX_NAME_LENGTH+1),"cake description", "http://image_url");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownWhenDescriptionToLong() {
        new Cake("test cake",randomAlphabetic(MAX_DESCRIPTION_LENGTH+1), "http://image_url");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownImageURLNameToLong() {
        new Cake("test cake","cake description", randomAlphabetic(MAX_IMAGE_URL_LENGTH+1));
    }
}
