package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.Direction;
import classes.Usager;

public class UsagerTest {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getEtageCourrant() {

        System.out.println("Tester : get Etage Courrant");

        Usager instance= new Usager(5,Direction.Up,6);
        int expresult=5;
        int res= instance.getEtageCourrant();
        assertEquals(expresult,res);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getDestination() {

        System.out.println("Tester : get Destination");

        Usager instance= new Usager(5,Direction.Up,6);
        int expresult=6;
        int res= instance.getDestination();
        assertEquals(expresult,res);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getDirection() {

        System.out.println("Tester : getDirection");

        Usager instance= new Usager(5,Direction.Up,6);
        Direction expresult=Direction.Up;
        Direction res= instance.getDirection();
        assertEquals(expresult,res);
    }

}