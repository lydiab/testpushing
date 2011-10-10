package ca.uqam.inf2015.aut2011.protagoras;

import java.util.ArrayList;

/**
 *
 * @author Équipe L.M.O.
 */
public class PretCalcule extends Pret {
    
    private double versementPeriodique;
    private ArrayList<Periode> amortissement;
    
    
    /**
     * 
     * Constructeur de la class PretCalcule
     */
    public PretCalcule() {
        
        super();
        versementPeriodique = 0;
        amortissement = new ArrayList();
    }
    
    /**
     * 
     * @return double Le monant du versement périodique
     */
    public double getVersementPeriodique() {
        
        return versementPeriodique;
    }
    
    /**
     * 
     * @param double Le monant du versement périodique
     */
    public void setVersementPeriodique(double versementPeriodique) {
        
        this.versementPeriodique = versementPeriodique;
    }
    
    /**
     * 
     * @return ArrayList<Periode> La liste des amortissements
     */
    public ArrayList<Periode> getAmortissement() {
        
        return amortissement;
    }
    
    /**
     * 
     * @param ArrayList<Periode> La liste des amortissements
     */
    public void setAmortissement(ArrayList<Periode> amortissement) {
        
        this.amortissement = amortissement;
    }
}
