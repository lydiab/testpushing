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
    
    private int _periode;
    private double _capitalDebut;
    private double _versementTotal;
    private double _versementInteret;
    private double _versementCapital;
    private double _capitalFin;
    private double _versementTotalCumulatif;
    private double _versementInteretCumulatif;
    private double _versementCapitalCumulatif;
    
    public int getPreriode() {
        
        return _periode;
    }
    
    public double getCapitalDebut() {
        
        return _capitalDebut;
    }
    
    public double getVersementTotal() {
        
        return _versementTotal;
    }
    
    public double getVersementInteret() {
        
        return _versementInteret;
    }
    
    public double getVersementCapital() {
        
        return _versementCapital;
    }
            
    public double getCapitalFin() {
        
        return _capitalFin;
    }
    
    public double getVersementTotalCumulatif() {
        
        return _versementTotalCumulatif;
    }
    
    public double getVersementInteretCumulatif() {
        
        return _versementInteretCumulatif;
    }
    
    public double getVersementCapitalCumulatif() {
        
        return _versementCapitalCumulatif;
    }
    
    
    public void setPeriode(int periode) {
        
        _periode = periode;
    }
    
    public void setVersementTotal(double versementTotal) {
        
        _versementTotal = versementTotal;
    }
    
    public void setVersementInteret(double versementInteret) {
        
        _versementInteret = versementInteret;
    }
    
    public void setVersementCapital(double versementCapital) {
        
        _versementCapital = versementCapital;
    }
    
    public void setCapitalFin(double capitalFin) {
        
        _capitalFin = capitalFin;
    }
    
    public void setVersementTotalCumulatif(double versementTotalCumulatif) {
        
        _versementTotalCumulatif = versementTotalCumulatif;
    }
    
    public void setVersementInteretCumulatif(double versementInteretCumulatif) {
        
        _versementInteretCumulatif = versementInteretCumulatif;
    }
    
    public void setVersementCapitalCumulatif(double versementCapitalCumulatif) {
        
        _versementCapitalCumulatif = versementCapitalCumulatif;
    }
    
}
