package com.demo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void checkErrorCode() {
        Assert.assertFalse(Utils.checkErrorCode(null));
        Assert.assertFalse(Utils.checkErrorCode("HelloidERRCODEHelloid"));
        Assert.assertFalse(Utils.checkErrorCode("HelloidERRCODE=Helloid"));
        Assert.assertFalse(Utils.checkErrorCode("HelloidERRCODE=1Helloid"));
        Assert.assertFalse(Utils.checkErrorCode("HelloidERRCODE=1Helloid"));
        Assert.assertFalse(Utils.checkErrorCode("HelloidERRCODE=01Helloid"));
        Assert.assertTrue(Utils.checkErrorCode("HelloidERRCODE=0Helloid"));

    }

}
