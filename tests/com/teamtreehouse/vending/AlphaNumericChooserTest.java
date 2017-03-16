package com.teamtreehouse.vending;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by alexis-toma.ghillis on 3/13/2017.
 */
public class AlphaNumericChooserTest {
    private AlphaNumericChooser chooser;
    private Creditor creditor;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        chooser = new AlphaNumericChooser(26,10);
        creditor = new Creditor();
    }

    @Test
    public void validInputReturnsProperLocation() throws Exception, InvalidLocationException {
        AlphaNumericChooser.Location loc = chooser.locationFromInput("B4");
        assertEquals("proper row",1, loc.getRow());
        assertEquals("proper column", 3, loc.getColumn());
    }
    @Test(expected = InvalidLocationException.class)
    public void choosingWrongInputIsNotAllowed() throws Exception, InvalidLocationException {
        chooser.locationFromInput("WRONG");
    }

    @Test(expected = InvalidLocationException.class)
    public void choosingLargerThanMaxIsNotAllowed() throws Exception, InvalidLocationException {
        chooser.locationFromInput("B52");
    }

    @Test(expected = NotEnoughFundsException.class )
    public void moneyIsBiggerThanFunds() throws Exception, NotEnoughFundsException {
        creditor.addFunds(10);
        creditor.deduct(100);
    }

    @Test
    public void constructingLargerThanAlphabetNotAllowed() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Maximum rows supported is 26");
        new AlphaNumericChooser(27,10);

    }
}