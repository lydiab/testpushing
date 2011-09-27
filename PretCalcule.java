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
    
    private double _versementPeriodique;
    private ArrayList _amortissement;
    
    
    public PretCalcule() {
        
        super();
        _versementPeriodique = 0;
        _amortissement = new ArrayList();
    }
    
    public double getVersementPeriodique() {
        
        return _versementPeriodique;
    }
    
    public void setVersementPeriodique(double versementPeriodique) {
        
        _versementPeriodique = versementPeriodique;
    }
    
    public ArrayList getAmortissement() {
        
        return _amortissement;
    }
    
    public void setAmortissement(ArrayList amortissement) {
        
        _amortissement = amortissement;
    }
    
    public void ajouterAmortissement(Periode amortissement) {
        
        _amortissement.add(amortissement);
    }
    
    public void supprimerAmortissement(int index) {
        
        _amortissement.remove(index);
    }
}
