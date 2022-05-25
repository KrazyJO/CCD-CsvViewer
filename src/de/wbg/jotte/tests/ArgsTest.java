package de.wbg.jotte.tests;

import de.wbg.jotte.PageArgResult;
import de.wbg.jotte.ReadArgs;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ArgsTest {

    @Test
    void noArgGiven() {
        String [] args = new String[0];
        PageArgResult result = ReadArgs.readPageSize(args);
        Assertions.assertFalse(result.isArgFound());
    }

    @Test
    void argGiven() {
        String [] args = new String[1];
        args[0] = "2";
        PageArgResult result = ReadArgs.readPageSize(args);
        Assertions.assertTrue(result.isArgFound());
        Assertions.assertEquals(2, result.getPageSizes());
    }

    @Test
    void wrongArg() {
        String [] args = new String[1];
        args[0] = "e";
        PageArgResult result = ReadArgs.readPageSize(args);
        Assertions.assertFalse(result.isArgFound());
        Assertions.assertTrue(result.isHasError());
        Assertions.assertFalse(result.getErrorReason().isEmpty());
    }
}
