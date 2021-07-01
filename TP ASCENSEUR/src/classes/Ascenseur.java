package classes;
import java.util.ArrayList;
import java.util.Iterator;

public class Ascenseur {

    

	@SuppressWarnings("unused")
	private int EtageMax;
    private ArrayList<Porte> portes;
    private int etageCourrant;// l'etage actuel de l'ascenseur
    private Direction direction; // "UP" ou "DOWN" ou "NON" si aucune direction
    private ArrayList<Usager> destinations;   //liste contenant les destinations des usagers dans l'ascenseur
    private ArrayList<Usager> appels;  // liste conetant tout les appels en cours effectuer par les usagers

    public Ascenseur(int nb_etages, ArrayList<Porte> list_portes)
    {
        this.EtageMax = nb_etages;
        this.portes = list_portes;

        this.etageCourrant = 0;
        this.direction = Direction.None;
        this.appels = new ArrayList<Usager>();
        this.destinations = new ArrayList<Usager>();
    }

   

    public void ChangerDeriction(){
        
         //  Nous mettons à jour la direction de l'ascenseur après avoir ajouté un nouvel utilisateur dans la liste d'appels ou
         //  Destination ou après avoir atteint la destination de l'un des utilisateurs de la liste de destinations
           // Selon les règles stipulées dans TP
        

        // Priorité de la liste de destinations
        if (!this.destinations.isEmpty()){
            if (this.destinations.get(0).getDestination() > this.etageCourrant){
                this.direction = Direction.Up;
            }else{
                this.direction = Direction.Down;
            }
        }else if (!this.appels.isEmpty()){
            // Si la liste des destinataires est vide, vérifiez la liste des appels, 
        	//si elle n'est pas vide  faire :
            if (direction == Direction.Up || direction == Direction.None){
                // si la direction  vers le haut ou none on verifie s'il y as des appel vers le haut
                if (VerifierAppelHaut()) {
                    // si oui on mets la direction vers le haut
                    this.direction = Direction.Up;
                }else{
                	
                    // sinon on verifie s'il y as des appel vers le bas
                	
                    if (VerifierAppelBas()){
                    	
                        // si oui on mets la direction vers le bas
                        this.direction = Direction.Down;
                    }else{
                    	
                        // sinon on mets la direction a none
                        this.direction = Direction.None;
                    }
                }
            }else{
                // si la direction  vers le bas on verifie s'il y as des appel vers le bas
                if (VerifierAppelBas()){
                    // si oui on mets la direction vers le bas
                    this.direction = Direction.Down;
                }else{
                    // sinon on verifie s'il y as des appel vers le haut
                    if (VerifierAppelHaut()){
                        // si oui on mets la direction vers le haut
                        this.direction = Direction.Up;
                    }else{
                        // sinon on mets la direction a none
                        this.direction = Direction.None;
                    }
                }
            }
        }else{
            // si la liste de destination et d'appel sont vide on met la direction= "none"
            this.direction = Direction.None;
        }

         if (direction == Direction.Up){
            System.out.println("ASCENSEUR: direction vers le haut");
            
        }else 
        	if (direction == Direction.Down){
            System.out.println("ASCENSEUR:  vers le bas");
      
        	}else{
        		
            System.out.println("ASCENSEUR: reste a l'etage courrant avec direction none");
        }
    }

    private boolean VerifierAppelHaut(){
        // verifie s'il existe des appels vers le haut
        for (Usager u : this.appels){
            if (u.getEtageCourrant() > this.etageCourrant){
                return true;
            }
        }
        return false;
    }

    private boolean VerifierAppelBas(){
        // verifie s'il existe des appels vers le bas
        for (Usager u : this.appels){
            if (u.getEtageCourrant() < this.etageCourrant){
                return true;
            }
        }
        return false;
    }

    public void deplacer() throws InterruptedException{
        /*
            cette fonction deplace l'assenceur d'un etage dans la direction courrante de l'assenceur puis verifie
            s'il y as des usager qui vont sortir de l'assenceur ou d'entrer.
        */
        if (this.direction == Direction.None){
            // si on appel cette fonction et que la direction est None on verifie s'il y as des usager qui vont
            // sortir de l'assenceur ou por entrer puis on mets a jours la direction sans se deplacer.
            if (appels.isEmpty() && destinations.isEmpty()){
            	
                // si il n'y a aucun usager, on ne fait rien
                return;
            }
            this.VerifierEtage();
            this.ChangerDeriction();
            return;
        }
        
        // on se deplace d'un etage
        if (direction.equals(Direction.Up)){
            etageCourrant ++;
        } else if (direction.equals(Direction.Down)){
            etageCourrant --;
        }
        System.out.println("+ Ascenseur arriver à l'etage n°" + etageCourrant);

        // on verifie s'il y as des usager a cet etage
        this.VerifierEtage();

        // on mets a jour la direction en fonction des usager qui y sont sortie ou enter
        this.ChangerDeriction();
    }

    private void VerifierEtage() throws InterruptedException{
        // on verifie s'il y as des usager qui vont sortir de l'ascenseur, si oui tous les usager .
        this.PasserDe();

        // on verifie s'il y as des usager qui vont entrer dans l'ascenseur, si oui on verifie qu'il vont dans la direction de l'ascenseur ,si oui ils entrent.
        this.PasserA();

        if (!this.portes.get(this.etageCourrant).isTheDoorClosed()){
            // si la porte a etait ouverte on la ferme
            System.out.println("L'ascensseur redemare");
            this.portes.get(this.etageCourrant).FermerPorte();
        }
    }

    public void PasserA(){
        // on parcourt les usager dans l'ascenseur (la liste de destination), a chaque fois qu'on trouve un usager
        // dont la destination est l'etage courrant il sort et on le supprime de la liste de destinaion
        Iterator<Usager> iterator=destinations.iterator();
        while(iterator.hasNext()){
            Usager usager= iterator.next();
           
            if (usager.getDestination() == this.etageCourrant){
              
            	if (this.portes.get(this.etageCourrant).isTheDoorClosed()){
                    // si la porte n'a pas encore etait ouverte on l'ouvre
                    System.out.println("L'ascensseur s'arrete");
                    this.portes.get(this.etageCourrant).OuvrirePorte();
                }
            	
            	    usager.sortir();
            	    iterator.remove(); // on enleve l'usager de la liste des destination
            
               
            }
        }
    }

    public void PasserDe(){
        // on parcourt la liste d'appel des usager, a chaque fois qu'on trouve un usager à l'etage courrant on verifie
        // si sa direction match avec celle de l'ascenseur si oui il entre et rentre sa destination
        Iterator<Usager> iterator=appels.iterator();
       
        while(iterator.hasNext()){
            Usager usager= iterator.next();

            if (usager.getEtageCourrant() == this.etageCourrant && this.direction == Direction.None){
                if (this.portes.get(this.etageCourrant).isTheDoorClosed()){
                    // si la porte n'a pas encore etait ouverte on l'ouvre
                    System.out.println("L'ascensseur s'arrete");
                    this.portes.get(this.etageCourrant).OuvrirePorte();
                }
                
                
                
                // si la direction de l'ascenseur est none, sa implique que le premier usager a cet etage dans la liste
                // d'appel peut entrer, et on met a jour la direction de l'ascenseur sur la direction de l'usager
                usager.entrer();
                usager.signaler_destination();
                iterator.remove(); // on enleve l'usager de la liste d'appel
                
               
                this.destinations.add(usager); // on ajoute l'usager de la liste des destination
                this.direction = usager.getDirection();
                
            }else
            	
            	if (usager.getEtageCourrant() == this.etageCourrant && usager.getDirection() == this.direction){
                
            		if (this.portes.get(this.etageCourrant).isTheDoorClosed()){
                    // si la porte n'a pas encore ouverte on l'ouvre
                    System.out.println("L'ascensseur s'arrete");
                    this.portes.get(this.etageCourrant).OuvrirePorte();
                }
            		
            		
                usager.entrer();
                usager.signaler_destination();
                iterator.remove(); // on enleve l'usager de la liste d'appel
                this.destinations.add(usager); // on ajoute l'usager de la liste des destination
            }
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public void setDirection(Direction direction) {
    	this.direction = direction;
    	}

    public void setEtageCourrant(int etageCourrant) { 
    	this.etageCourrant = etageCourrant; 
    	}

    public ArrayList<Usager> getList_destinations() {
    	return destinations; 
    	}

    public ArrayList<Usager> getList_appels() { 
    	return appels;
    	}

    public Direction getDirection() { 
    	return direction; 
    	}

    public void ajouterAppel(Usager u){
        appels.add(u);
    }

    
}