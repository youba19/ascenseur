package classes;

public class Porte {


    private int etage;//etage de la porte
    private Etat etat; // "ouvert" ou "fermer"

    public Porte(int etage) {
        this.etage = etage;
        this.etat = Etat.Fermer;
    }

    public boolean isTheDoorClosed() {
        return etat.equals(Etat.Fermer);
    }

    public void FermerPorte(){
    	
        System.out.println("PORTE : Fermeture de la porte de l'etage" + this.etage);
       //   wait(3000); //attendre 3 seconde   // Quand j'utilise cet instruction dans la classe des test il jete une erreur
        this.etat = Etat.Fermer;//fermer la porte
    }

    public void OuvrirePorte() {
        System.out.println("PORTE : Ouverture de la porte de l'etage" + this.etage);
        this.etat = Etat.Ouvrire;//ouvrir les portes
    }
    
    
    
    
    public int getEtage() {
        return etage;
    }

    public Etat isEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
    }

