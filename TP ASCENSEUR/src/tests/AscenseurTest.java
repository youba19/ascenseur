package tests;

import org.junit.Test;

import classes.Ascenseur;
import classes.Direction;
import classes.Porte;
import classes.Usager;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AscenseurTest {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testdeplacer() throws InterruptedException {//throw c'est pour l'instruction(wait()) qui dans la classe porte
    
        System.out.println("Test : deplacer");

        //Vérifiez si la fonction de déplacement déplace l'ascenseur et appelez correctement la fonction d'E/S utilisateur.
        //Le resultat de ce test sera une forme de deroulement de scenario 
        ArrayList<Porte> doorList= new ArrayList<>();
        for(int i=0;i<10;i++){
            Porte porte= new Porte(i);
            doorList.add(porte);
        }

        Ascenseur instance= new Ascenseur(10,doorList);

        instance.ajouterAppel(new Usager(2,Direction.Up,5));
        
        instance.ajouterAppel(new Usager(4,Direction.Down,1));
        instance.ajouterAppel(new Usager(1,Direction.Up,3));
        instance.ajouterAppel(new Usager(6,Direction.Down,4));
        instance.ajouterAppel(new Usager(3,Direction.Up,8));

        while(instance.getList_appels().size()!=0 || instance.getList_destinations().size()!=0){
            instance.deplacer();
        }

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void ChangerDirection() throws InterruptedException {

        System.out.println("test : update direction");

        ArrayList<Porte> doorList= new ArrayList<>();
        for(int i=0;i<10;i++){
            Porte porte= new Porte(i);
            doorList.add(porte);
        }
        Ascenseur instance = new Ascenseur(10,doorList);

        Direction expResult = Direction.None;

        //les 2 listes sont vides, on verifie la direction
        instance.ChangerDeriction();
        assertEquals(expResult,instance.getDirection());

        //Ajoutez l'usager à la liste d'appels, puis vérifiez la direction

        instance.ajouterAppel(new Usager(1,Direction.Up,2));
        expResult= Direction.Up;
        instance.ChangerDeriction();
        assertEquals(expResult,instance.getDirection());

        while(instance.getList_appels().size()!=0 || instance.getList_destinations().size()!=0){
            instance.deplacer();
        }

        instance.ajouterAppel(new Usager(1,Direction.Down,0));
        expResult=Direction.Down;
        instance.ChangerDeriction();
        assertEquals(expResult,instance.getDirection());

        //Avec l'ascenseur au milieu, deux utilisateurs passent deux appels depuis deux étages différents.

        //Vérifiez ensuite si la priorité est l'usager en direction de l'ascenseur

        Usager usager1= new Usager(3,Direction.Up,7);
        Usager usager2= new Usager(8,Direction.Down,2);

        //Dans ce cas, l'ascenseur est en bas, donc la direction de l'usager 1 est en haut, mais elle est prioritaire.,
        instance.ajouterAppel(usager1);
        instance.ajouterAppel(usager2);
        instance.ChangerDeriction();

        assertEquals(expResult,instance.getDirection());

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void PasserDe() {

        System.out.println("test : Passer De");

        ArrayList<Porte> doorList= new ArrayList<>();
        for(int i=0;i<10;i++){
            Porte porte= new Porte(i);
            doorList.add(porte);
        }
        Ascenseur instance = new Ascenseur(10,doorList);
        instance.ajouterAppel(new Usager(0,Direction.Up,2));
        instance.PasserA();
        //On teste si l'usager sort de l'ascenseur a sa destination (qui laissera la liste de destination vide)
        instance.setEtageCourrant(2);
        int expres= 0;
        instance.PasserDe();
        assertEquals(expres,instance.getList_destinations().size());

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void PasserA() {

        System.out.println("test : Passer à");

        ArrayList<Porte> doorList= new ArrayList<>();
        for(int i=0;i<10;i++){
            Porte porte= new Porte(i);
            doorList.add(porte);
        }
        Ascenseur instance = new Ascenseur(10,doorList);
        Usager user=new Usager(0,Direction.Up,2);
        instance.ajouterAppel(user);

        //Vérifiez si la fonction supprime l'usager de la liste d'appels et l'ajoute à la liste de destination
        //Qui veut dire que l'usager monte dans l'ascenseur
        ArrayList<Usager> expResult= new ArrayList<>();
        expResult.add(user);
        instance.PasserA();
        assertEquals(expResult,instance.getList_destinations());

        //2 usager 1 etage direction opposé,
        instance.setEtageCourrant(5);
        instance.setDirection(Direction.Up);
        Usager user1= new Usager(5,Direction.Up,7);
        Usager user2= new Usager(5,Direction.Down,4);
        instance.ajouterAppel(user1);
        instance.ajouterAppel(user2);

        instance.PasserA();
        //Seul l'utilisateur 1 doit entrer dans l'ascenseur car ils sont dans la même direction.
        //Alors que l'usager 2 reste dans la liste d'appels
        assertTrue(instance.getList_destinations().contains(user1));
        assertTrue(!(instance.getList_appels().contains(user1)));

        assertFalse(instance.getList_destinations().contains(user2));
        assertFalse(!(instance.getList_appels().contains(user2)));


        //L'ascenseur ne s'arrête pas car l'sager 3 n'est pas dans la même direction.
        Usager user3= new Usager(6,Direction.Down,3);
        instance.ajouterAppel(user3);

        //Déplacez l'ascenseur d'un étage et voyez si l'usager 3 s'y entrer ou non:
        instance.setEtageCourrant(6);
        instance.PasserA();//Rien ne se passe

        assertFalse(instance.getList_destinations().contains(user3));
        assertTrue(instance.getList_appels().contains(user3));
    }
}