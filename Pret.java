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
    
    private int _id;
    private String _description;
    private double _montant;
    private int _nombreAnnee;
    private int _frequenceRemboursement;
    private double _tauxInteret;
    private int _frequenceComposition;
    
    public Pret() {
        
        _id = 0;
        _description = "";
        _montant= 0;
        _nombreAnnee = 0;
        _frequenceRemboursement = 0;
        _tauxInteret = 0;
        _frequenceComposition = 0;
    }
    
    /**
    public Pret(int id, String description, double montant, int nombreAnnee,
                     int frequenceRemboursement, double tauxInteret, 
                     int frequenceComposition) {
    
        _id = id;
        _description = description;
        _montant= montant;
        _nombreAnnee = nombreAnnee;
        _frequenceRemboursement = frequenceRemboursement;
        _tauxInteret = tauxInteret;
        _frequenceComposition = frequenceComposition;
    }
    */
    public int getId() {
        
        return _id;
    }
    
    public String getDescription() {
        
        return _description;
    }
    
    public double getMontant() {
        
        return _montant;
    }
    
    public int getNombreAnnee() {
    
        return _nombreAnnee;
    }
    
    public int getFrequenceRemboursement() {
        
        return _frequenceRemboursement;
    }
    
    public double getTauxInteret() {
        
        return _tauxInteret;
    }
    
    public int getFrequenceComposition() {
        
        return _frequenceComposition;
    }
    
    
    public void setId(int id) {
        _id = id;
    }
    
    public void setDescription(String description) {
        
        _description = description;
    }
    
    public void setMontant(double montant) {
        
        _montant = montant;
    }
    
    public void setNombreAnnee(int nombreAnnee) {
        
        _nombreAnnee = nombreAnnee;
    }
    
    public void setFrequenceRemboursement(int frequenceRemboursement) {
        
        _frequenceRemboursement = frequenceRemboursement;
    }
    
    public void setTauxInteret(double tauxInteret) {
        
        _tauxInteret = tauxInteret;
    }
    
    public void setFrequenceComposition(int frequenceComposition) {
        
        _frequenceComposition = frequenceComposition;
    }
}
