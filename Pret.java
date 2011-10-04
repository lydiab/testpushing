/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.inf2015.aut2011.protagoras;

/**
 *
 * @author Netbook
 */
public class Pret {
    
    private String id;
    private String description;
    private double montant;
    private int nombreAnnee;
    private int frequenceRemboursement;
    private double tauxInteret;
    private int frequenceComposition;
    
    public Pret() {
        
        id = "";
        description = "";
        montant= 0;
        nombreAnnee = 0;
        frequenceRemboursement = 0;
        tauxInteret = 0;
        frequenceComposition = 0;
    }
    
    /**
    public Pret(int id, String description, double montant, int nombreAnnee,
                     int frequenceRemboursement, double tauxInteret, 
                     int frequenceComposition) {
    
        id = id;
        description = description;
        montant= montant;
        nombreAnnee = nombreAnnee;
        frequenceRemboursement = frequenceRemboursement;
        tauxInteret = tauxInteret;
        frequenceComposition = frequenceComposition;
    }
    */
    public String getId() {
        
        return id;
    }
    
    public String getDescription() {
        
        return description;
    }
    
    public double getMontant() {
        
        return montant;
    }
    
    public int getNombreAnnee() {
    
        return nombreAnnee;
    }
    
    public int getFrequenceRemboursement() {
        
        return frequenceRemboursement;
    }
    
    public double getTauxInteret() {
        
        return tauxInteret;
    }
    
    public int getFrequenceComposition() {
        
        return frequenceComposition;
    }
    
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setDescription(String description) {
        
        this.description = description;
    }
    
    public void setMontant(double montant) {
        
        this.montant = montant;
    }
    
    public void setNombreAnnee(int nombreAnnee) {
        
        this.nombreAnnee = nombreAnnee;
    }
    
    public void setFrequenceRemboursement(int frequenceRemboursement) {
        
        this.frequenceRemboursement = frequenceRemboursement;
    }
    
    public void setTauxInteret(double tauxInteret) {
        
        this.tauxInteret = tauxInteret;
    }
    
    public void setFrequenceComposition(int frequenceComposition) {
        
        this.frequenceComposition = frequenceComposition;
    }
}
