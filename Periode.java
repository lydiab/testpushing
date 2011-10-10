package ca.uqam.inf2015.aut2011.protagoras;

/**
 *
 * @author Équipe L.M.O.
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
    
    /**
     * 
     * @return int L'identifiant de la période
     */
    public int getPreriode() {
        
        return periode;
    }
    
    /**
     * 
     * @return double Le capital de début
     */
    public double getCapitalDebut() {
        
        return capitalDebut;
    }
    
    /**
     * 
     * @return double Le versement total
     */
    public double getVersementTotal() {
        
        return versementTotal;
    }
    
    /**
     * 
     * @return double Le versement d'intérêt
     */
    public double getVersementInteret() {
        
        return versementInteret;
    }
    
    /**
     * 
     * @return double Le versement fait au capital
     */
    public double getVersementCapital() {
        
        return versementCapital;
    }
            
    /**
     * 
     * @return double Capital à la fin
     */
    public double getCapitalFin() {
        
        return capitalFin;
    }
    
    /**
     * 
     * @return double Le versement total du cumulatif
     */
    public double getVersementTotalCumulatif() {
        
        return versementTotalCumulatif;
    }
    
    /**
     * 
     * @return double Le versement d'intérêt cumulatif
     */
    public double getVersementInteretCumulatif() {
        
        return versementInteretCumulatif;
    }
    
    /**
     * 
     * @return double Le versement du capital cumulatif
     */
    public double getVersementCapitalCumulatif() {
        
        return versementCapitalCumulatif;
    }
    
    
    /**
     * 
     * @param int L'identifiant de la période
     */
    public void setPeriode(int periode) {
        
        this.periode = periode;
    }
    
    /**
     * 
     * @param double Le capital de début
     */
    public void setCapitalDebut(double capitalDebut) {
        
        this.capitalDebut = capitalDebut;
    }
    
    /**
     * 
     * @param double Le versement total
     */
    public void setVersementTotal(double versementTotal) {
        
        this.versementTotal = versementTotal;
    }
    
    /**
     * 
     * @param double Le versement d'intérêt
     */
    public void setVersementInteret(double versementInteret) {
        
        this.versementInteret = versementInteret;
    }
    
    /**
     * 
     * @param double Le versement fait au capital
     */
    public void setVersementCapital(double versementCapital) {
        
        this.versementCapital = versementCapital;
    }
    
    /**
     * 
     * @param double Capital à la fin
     */
    public void setCapitalFin(double capitalFin) {
        
        this.capitalFin = capitalFin;
    }
    
    /**
     * 
     * @param double Le versement total du cumulatif
     */
    public void setVersementTotalCumulatif(double versementTotalCumulatif) {
        
        this.versementTotalCumulatif = versementTotalCumulatif;
    }
    
    /**
     * 
     * @param double Le versement d'intérêt cumulatif
     */
    public void setVersementInteretCumulatif(double versementInteretCumulatif) {
        
        this.versementInteretCumulatif = versementInteretCumulatif;
    }
    
    /**
     * 
     * @param double Le versement du capital cumulatif
     */
    public void setVersementCapitalCumulatif(double versementCapitalCumulatif) {
        
        this.versementCapitalCumulatif = versementCapitalCumulatif;
    }
    
}
