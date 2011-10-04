/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.inf2015.aut2011.protagoras;

import java.util.ArrayList;

/**
 *
 * @author Netbook
 */
public class PretCalcule extends Pret {
    
    private double versementPeriodique;
    private ArrayList amortissement;
    
    
    public PretCalcule() {
        
        super();
        versementPeriodique = 0;
        amortissement = new ArrayList();
    }
    
    public double getVersementPeriodique() {
        
        return versementPeriodique;
    }
    
    public void setVersementPeriodique(double versementPeriodique) {
        
        this.versementPeriodique = versementPeriodique;
    }
    
    public ArrayList getAmortissement() {
        
        return amortissement;
    }
    
    public void setAmortissement(ArrayList amortissement) {
        
        this.amortissement = amortissement;
    }
    
    public void ajouterAmortissement(Periode amortissement) {
        
        this.amortissement.add(amortissement);
    }
    
    public void supprimerAmortissement(int index) {
        
        amortissement.remove(index);
    }
}
