package ca.uqam.inf2015.aut2011.protagoras;

import java.util.ArrayList;

/**
 *
 * @author Équipe L.M.O.
 */
public class Pret {
    
    private String id;
    private String description;
    private double montant;
    private int nombreAnnee;
    private int frequenceRemboursement;
    private double tauxInteret;
    private int frequenceComposition;
    
    /**
     * 
     * Constructeur de la class Pret
     */
    public Pret() {
        
        id = "";
        description = "";
        montant= 0;
        nombreAnnee = 0;
        frequenceRemboursement = 0;
        tauxInteret = 0;
        frequenceComposition = 0;
    }

      
    
    public Pret(String id, String description, double montant, int nombreAnnee,
                     int frequenceRemboursement, double tauxInteret, 
                     int frequenceComposition) {
    
        this.id = id;
        this.description = description;
        this.montant = montant;
        this.nombreAnnee = nombreAnnee;
        this.frequenceRemboursement = frequenceRemboursement;
        this.tauxInteret = tauxInteret;
        this.frequenceComposition = frequenceComposition;
    }


    /**
     * 
     * @return String Id du prêt
     */
    public String getId() {
        
        return id;
    }
    
    /**
     * 
     * @return String Description du prêt
     */
    public String getDescription() {
        
        return description;
    }
    
    /**
     * 
     * @return double Montant du prêt
     */
    public double getMontant() {
        
        return montant;
    }
    
    /**
     * 
     * @return int Nombre d'année du prêt
     */
    public int getNombreAnnee() {
    
        return nombreAnnee;
    }
    
    /**
     * 
     * @return int La fréquence de remboursement
     */
    public int getFrequenceRemboursement() {
        
        return frequenceRemboursement;
    }
    
    /**
     * 
     * @return double Le taux d'intérêt
     */
    public double getTauxInteret() {
        
        return tauxInteret;
    }
    
    /**
     * 
     * @return int La fréquence de la composition
     */
    public int getFrequenceComposition() {
        
        return frequenceComposition;
    }
    
    
    /**
     * 
     * @param String L'id du prêt
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 
     * @param String La description du prêt
     */
    public void setDescription(String description) {
        
        this.description = description;
    }
    
    /**
     * 
     * @param double Le montant du prêt
     */
    public void setMontant(double montant) {
        
        this.montant = montant;
    }
    
    /**
     * 
     * @param int Le nombre d'année du prêt
     */
    public void setNombreAnnee(int nombreAnnee) {
        
        this.nombreAnnee = nombreAnnee;
    }
    
    /**
     * 
     * @param int La fréequence de rembourssement
     */
    public void setFrequenceRemboursement(int frequenceRemboursement) {
        
        this.frequenceRemboursement = frequenceRemboursement;
    }
    
    /**
     * 
     * @param double Le taux d'intéret du prêt
     */
    public void setTauxInteret(double tauxInteret) {
        
        this.tauxInteret = tauxInteret;
    }
    
    /**
     * 
     * @param int La fréquence de la composition
     */
    public void setFrequenceComposition(int frequenceComposition) {
        
        this.frequenceComposition = frequenceComposition;
    }
    
    /**
     * 
     * Retourne la liste des descriptions des erreurs des données.
     * 
     * @return ArrayList<String> Liste d'erreur des données dans l'objet.
     */
    public ArrayList<String> validerPret() {
        
        ArrayList<String> listeErreur = new ArrayList();
        
        /** Valider le montant */
        if (this.montant <= 0) {
            
            listeErreur.add("Le montant doit etre un nombre superieur a 0.");
        }
        
        /** Valider le nombreAnnee */
        if (this.nombreAnnee <= 0) {
            
            listeErreur.add("Le nombre d annees doit etre superieur a 0");
        }
        
        /** Valider la frequenceRemboursement */
        if(this.frequenceRemboursement <= 0) {
            
            listeErreur.add("La frequence de remboursement doit etre superieur a 0");
        }
        
        /** Valider la tauxInteret */
        if(this.tauxInteret < 0) {
            
            listeErreur.add("Le taux d interet doit etre superieur ou egal a 0");
        }
        
        /** Valider la frequenceComposition */
        if(this.frequenceComposition <= 0) {
            
            listeErreur.add("Le frequence de composition doit etre superieur a 0");
        }
        
        return listeErreur;
    
    }
}
