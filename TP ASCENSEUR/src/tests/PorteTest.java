package tests;

import org.junit.Test;

import classes.Porte;

import static org.junit.Assert.*;

public class PorteTest {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getEtage() {
        System.out.println("Test : getEtage");

        Porte instance= new Porte(4);
        int expresult= 4;
        int result=instance.getEtage();
        assertEquals(expresult,result);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void isTheDoorClosed() throws InterruptedException {
        //Verifie si la porte est fermé ou non 
        System.out.println("Test : la Porte est Fermer !!!");

        Porte instance= new Porte(3);
        boolean expResult= false;
        instance.OuvrirePorte();
        boolean res= instance.isTheDoorClosed();
         assertEquals(expResult,res);
        
         expResult=true;
         instance.FermerPorte();
        res= instance.isTheDoorClosed();
        assertEquals(expResult,res);

  

       
        
      
    }
}