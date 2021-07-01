package classes;

public class Usager {

    private static int nb_usager = 0;

    private int etageCourrant;//etage actuel de l'usager
    private Direction direction;
    private int destination;
    private int Num_usager;

    public Usager(int etageCourrant, Direction direction, int destination) {
        this.etageCourrant = etageCourrant;
        this.Num_usager = Usager.nb_usager;
        this.destination = destination;
        this.direction = direction;
       
        Usager.nb_usager++;
    }

  

    public void entrer(){
        System.out.println("USAGER Numero " + this.Num_usager + 
        		         ": Monte dans l'ascenceur");
    }

    public void signaler_destination(){
        System.out.println("USAGER Numero" + this.Num_usager + 
        		": entre la destination :" + this.destination);
    }

    public void sortir(){
        System.out.println("USAGER Numero" + this.Num_usager +
        		            ": Descent de l'ascenceur");
    }
    
   //////////////// 
    public int getEtageCourrant() {
        return etageCourrant;
    }

    
    public Direction getDirection() {
        return direction;
    }
    
     public int getDestination() {
        return destination;
    }
}
