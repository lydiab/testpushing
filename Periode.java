/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.inf2015.aut2011.protagoras;

/**
 *
 * @author Netbook
 */
public class Periode {
    
    private int periode;
    private double capitalDebut;
    private double versementTotal;
    private double versementInteret;
    private double versementCapital;
    private double capitalFin;
    private double versementTotalCumulatif;
    private double versementInteretCumulatif;
    private double versementCapitalCumulatif;
    
    public int getPreriode() {
        
        return periode;
    }
    
    public double getCapitalDebut() {
        
        return capitalDebut;
    }
    
    public double getVersementTotal() {
        
        return versementTotal;
    }

    public void setCapitalDebut(double capitalDebut) {
        this.capitalDebut = capitalDebut;
    }
    
    public double getVersementInteret() {
        
        return versementInteret;
    }
    
    public double getVersementCapital() {
        
        return versementCapital;
    }
            
    public double getCapitalFin() {
        
        return capitalFin;
    }
    
    public double getVersementTotalCumulatif() {
        
        return versementTotalCumulatif;
    }
    
    public double getVersementInteretCumulatif() {
        
        return versementInteretCumulatif;
    }
    
    public double getVersementCapitalCumulatif() {
        
        return versementCapitalCumulatif;
    }
    
    
    public void setPeriode(int periode) {
        
        this.periode = periode;
    }
    
    public void setVersementTotal(double versementTotal) {
        
        this.versementTotal = versementTotal;
    }
    
    public void setVersementInteret(double versementInteret) {
        
        this.versementInteret = versementInteret;
    }
    
    public void setVersementCapital(double versementCapital) {
        
        this.versementCapital = versementCapital;
    }
    
    public void setCapitalFin(double capitalFin) {
        
        this.capitalFin = capitalFin;
    }
    
    public void setVersementTotalCumulatif(double versementTotalCumulatif) {
        
        this.versementTotalCumulatif = versementTotalCumulatif;
    }
    
    public void setVersementInteretCumulatif(double versementInteretCumulatif) {
        
        this.versementInteretCumulatif = versementInteretCumulatif;
    }
    
    public void setVersementCapitalCumulatif(double versementCapitalCumulatif) {
        
        this.versementCapitalCumulatif = versementCapitalCumulatif;
    }
    
}
