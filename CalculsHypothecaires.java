/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.inf2015.aut2011.protagoras;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Netbook
 */
public class CalculsHypothecaires {
  
 
    private double interetCumulatif = 0;
    private double capitalCumulatif = 0;
    private double versementTotalCumulatif = 0;
    private double capitalDebut = 0;
    private double capitalFin = 0;
    private double tauxMensuel = 0;
    private double versementPeriodique = 0;
    

    public CalculsHypothecaires() {
    }
    
    /**
     * Cree un pret calcule avec periode d'amortissement et versement periodique
     * @param pret pret a calcule
     * @return pretCalcule 
     */
    public PretCalcule creerPretCalcule (Pret pret) throws ParseException{
        
        PretCalcule pretCalcule = new PretCalcule ();
     
        enregistrerInfoPret (pretCalcule, pret);
        pretCalcule.setVersementPeriodique(calculerVersementPeriodique(pretCalcule));
        pretCalcule.setAmortissement(creerTableauAmortissement(pretCalcule));
             
        return pretCalcule ;
    }
    
    
    /**
     * Enregistre les infos du pret dand le pret Calcule
     * @param pretCalcule pret a calcule
     * @param pret contenant les infos du pret � calculer
     */
    public void enregistrerInfoPret (PretCalcule pretCalcule, Pret pret){
       
       pretCalcule.setId(pret.getId());
       pretCalcule.setDescription(pret.getDescription());
       pretCalcule.setMontant(pret.getMontant());
       pretCalcule.setNombreAnnee(pret.getNombreAnnee());
       pretCalcule.setFrequenceRemboursement(pret.getFrequenceRemboursement());
       pretCalcule.setTauxInteret(pret.getTauxInteret());
       pretCalcule.setFrequenceComposition(pret.getFrequenceComposition());
       
       capitalDebut = pretCalcule.getMontant();
       
   }
    
    
    /**
     * Calcul le versement periodique ( mensuel) 
     * @param pretCalcule 
     * @return versement peiodique
     */
    public double calculerVersementPeriodique (PretCalcule pretCalcule) throws ParseException{
        
        int frequenceRemboursement = pretCalcule.getFrequenceRemboursement();
        int nombreAnnee = pretCalcule.getNombreAnnee();
        
        tauxMensuel = calculerTauxMensuel(pretCalcule);
        
        
        double versementPeriodiqueCalcule = (capitalDebut * tauxMensuel) / 
                              (1 - (1/ Math.pow((1 + tauxMensuel),
                              (frequenceRemboursement*nombreAnnee))));
        
       
        versementPeriodique = formater2decimales(versementPeriodiqueCalcule);
        
        
        
        return versementPeriodique;
        
    }
    

    /**
     * Calcule le taux d'interet mensuel
     * @param pretCalcule
     * @return tauxmensuel
     */
    public double calculerTauxMensuel (PretCalcule pretCalcule){
        
        double tauxInteret = pretCalcule.getTauxInteret()/ 100;
        double frequenceComposition = pretCalcule.getFrequenceComposition();
        int    frequenceRemboursement = pretCalcule.getFrequenceRemboursement();
        
        double tauxMensuelCalcule = (Math.pow((1 + tauxInteret/frequenceComposition),
                             (frequenceComposition/frequenceRemboursement))- 1);
        
      
        
        return tauxMensuelCalcule; 
        
    }
       
    
    /**
     * Cr�e le tableau des periodes d'amortissement
     * @param pretCalcule
     * @return amortissement
     */
    public ArrayList <Periode>  creerTableauAmortissement(PretCalcule pretCalcule) throws ParseException{
       
       ArrayList <Periode> amortissement = new ArrayList <Periode> ();
       int numeroPeriode = 0;
       int nombrePeriodesTotales =  pretCalcule.getFrequenceRemboursement()*pretCalcule.getNombreAnnee();
           
       for (int i = 1; i < nombrePeriodesTotales; i++){
            numeroPeriode = i ;
            amortissement.add(creerPeriode(numeroPeriode));
       }    
  
       
       ajusterVersementPeriodiqueDernierePeriode();
       amortissement.add (creerPeriode(nombrePeriodesTotales));
       
       return amortissement;
       
    }
    
    /**
     * @todo : D�couper le code ???
     * Cr�e un periode contenant les infos du r�sultat du calculs : 
     * @param numeroPeriode 
     * @return periodeCalcule periode avec les resultats du calculs
     */
    public Periode creerPeriode (int numeroPeriode) throws ParseException{
            
        Periode periodeCalcule = new Periode ();
        
        
        periodeCalcule.setPeriode(numeroPeriode);
        periodeCalcule.setVersementTotal(versementPeriodique);
        inscrireVersementTotalCumulatif(periodeCalcule) ;
        inscrireVersementInteret(periodeCalcule);   
        inscrireVersementInteretCumulatif(periodeCalcule); 
        inscrireVersementCapital(periodeCalcule);
        inscrireVersementCapitalCumulatif (periodeCalcule);
        inscrireCapitalDebutFin(periodeCalcule);
        
            
        return periodeCalcule; 
    
    }
    
    private void ajusterVersementPeriodiqueDernierePeriode () throws ParseException{
   
        versementPeriodique = formater2decimales(capitalFin + capitalFin*tauxMensuel);

    } 
    
    /**
     * Calcul et inscrit le versement total cumulatif
     * @param periodeCalcule 
     */
     private void inscrireVersementTotalCumulatif(Periode periodeCalcule ) throws ParseException{
       
        versementTotalCumulatif = versementTotalCumulatif + versementPeriodique;
        versementTotalCumulatif = formater2decimales(versementTotalCumulatif);
        periodeCalcule.setVersementTotalCumulatif(versementTotalCumulatif);
 
    }
    
    /**
     * Calcul et inscrit le versement en interet periodique  
     * @param periodeCalcule
     */
    private void inscrireVersementInteret (Periode periodeCalcule) throws ParseException{
       
        double versementInteret = capitalDebut*tauxMensuel;
        versementInteret  = formater2decimales(versementInteret);
        periodeCalcule.setVersementInteret(versementInteret);
                  
    }
    
    /**
     * Calcul et inscrit le versement total en interet � ce jour 
     * @param periodeCalcule
     */
    private void inscrireVersementInteretCumulatif(Periode periodeCalcule) throws ParseException{
    
        double interetVerse = periodeCalcule.getVersementInteret();
        
        interetCumulatif = interetCumulatif + interetVerse;
        
        periodeCalcule.setVersementInteretCumulatif(formater2decimales(interetCumulatif));
        
    }
    
    
    /**
     * Calcule et inscrit le versement en capital periodique
     * @param periodeCalcule
     */
    private void inscrireVersementCapital(Periode periodeCalcule) throws ParseException{
    	
    	double interetVerse = periodeCalcule.getVersementInteret();     
        double versementCapital = versementPeriodique - interetVerse;
        
        versementCapital = formater2decimales(versementCapital);
        
        periodeCalcule.setVersementCapital(versementCapital);
        
    }
       
    
    
    /**
     * Calcule et inscrit le versement en capital total a ce jour 
     * @param periodeCalcule
     */
    private void inscrireVersementCapitalCumulatif (Periode periodeCalcule) throws ParseException{
        
      double capitalVerse = periodeCalcule.getVersementCapital();
            
      capitalCumulatif = capitalCumulatif + capitalVerse;
      
      capitalCumulatif = formater2decimales(capitalCumulatif);
      periodeCalcule.setVersementCapitalCumulatif(capitalCumulatif);  
        
    }

    /**
     * Calcul le capital de la fin de la periode et inscrit les capitals debut et fin
     * @param periodeCalcule
     */
    private void inscrireCapitalDebutFin(Periode periodeCalcule) throws ParseException{
    	
    	double versementCapital = periodeCalcule.getVersementCapital();
           
        capitalFin = capitalDebut - versementCapital;
        
        capitalDebut = formater2decimales(capitalDebut);
        capitalFin = formater2decimales(capitalFin);
        periodeCalcule.setCapitalDebut(capitalDebut);
        periodeCalcule.setCapitalFin(capitalFin);
            
        capitalDebut = capitalFin;
            
        }   
    
    public double formater2decimales (double nombre) throws ParseException{
        
        BigDecimal bigFormate = new BigDecimal(Double.toString(nombre));
        bigFormate = bigFormate.setScale(2,BigDecimal.ROUND_HALF_UP);
   
        return bigFormate.doubleValue(); 
    }
}
