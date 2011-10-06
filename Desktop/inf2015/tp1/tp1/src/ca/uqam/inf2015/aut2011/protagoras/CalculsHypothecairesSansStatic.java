/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor....
 */
package ca.uqam.inf2015.aut2011.protagoras;

import java.util.ArrayList;

/**
 *
 * @author Netbook
 */
public class CalculsHypothecairesSansStatic {
 
    private double interetCumulatif ;
    private double capitalCumulatif ;
    private double capitalDebut ;
    private double tauxMensuel ;
    private double versementPeriodique ;
   
    private PretCalcule pretCalcule;
    

    public CalculsHypothecairesSansStatic() {
    	
	this.interetCumulatif = 0;
	this.capitalCumulatif = 0;
	this.capitalDebut = 0;
	this.tauxMensuel = 0;
	this.versementPeriodique = 0;
	this.pretCalcule = new PretCalcule ();
                
    }


	/**
     * Cree un pret calcule avec periode d'amortissement et versement periodique
     * @param pret pret a calcule
     * @return pretCalcule 
     */
    public PretCalcule creerPretCalcule (Pret pret){
        
        enregistrerInfoPret (pret);     
        pretCalcule.setVersementPeriodique(calculerVersementPeriodique());
        pretCalcule.setAmortissement(creerTableauAmortissement()); 
         
        return pretCalcule;
    }
    
    
    /**
     * Enregistre les infos du pret dand le pret a calcule
     * @param pretCalcule pret a calcule
     * @param pret contenant les infos du pret � calculer
     */
   private void enregistrerInfoPret (Pret pret){
       
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
    private double calculerVersementPeriodique (){
        
        int frequenceRemboursement = pretCalcule.getFrequenceRemboursement();
        int nombreAnnee = pretCalcule.getNombreAnnee();
        
        tauxMensuel = calculerTauxMensuel();
        
        
        versementPeriodique = (capitalDebut * tauxMensuel) / 
                              (1 - (1/ Math.pow((1 + tauxMensuel),
                              (frequenceRemboursement*nombreAnnee))));
         
        return versementPeriodique;
    }
    

    /**
     * Calcule le taux d'interet mensuel
     * @param pretCalcule
     * @return tauxmensuel
     */
    private double calculerTauxMensuel (){
        
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
    private ArrayList <Periode>  creerTableauAmortissement(){
       
       ArrayList <Periode> amortissement = new ArrayList <Periode> ();
       int numeroPeriode = 0;
       int nombrePeriodesTotales =  pretCalcule.getFrequenceRemboursement()*pretCalcule.getNombreAnnee();
           
       for (int i = 1; i <= nombrePeriodesTotales; i++){
            numeroPeriode = i ;
            amortissement.add(creerPeriode(numeroPeriode));
       }    
       
       return amortissement;
    }
    
    /**
     * @todo : D�couper le code ???
     * Cr�e un periode contenant les infos du r�sultat du calculs : 
     * @param numeroPeriode 
     * @return periodeCalcule periode avec les resultats du calculs
     */
    private Periode creerPeriode (int numeroPeriode){
            
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
    
    /**
     * Calcul et inscrit le versement total cumulatif
     * @param periodeCalcule 
     */
     private void inscrireVersementTotalCumulatif(Periode periodeCalcule ){
        
        int nombreVersements = periodeCalcule.getPreriode();
        double versementTotalCumulatif = nombreVersements*versementPeriodique;
        
        periodeCalcule.setVersementTotalCumulatif(versementTotalCumulatif);
 
    }
    
    /**
     * Calcul et inscrit le versement en interet periodique  
     * @param periodeCalcule
     */
    private void inscrireVersementInteret (Periode periodeCalcule){
       
        double versementInteret = capitalDebut*tauxMensuel;
        
        periodeCalcule.setVersementInteret(versementInteret);
                  
    }
    
    /**
     * Calcul et inscrit le versement total en interet � ce jour 
     * @param periodeCalcule
     */
    private void inscrireVersementInteretCumulatif(Periode periodeCalcule){
    
        double interetVerse = periodeCalcule.getVersementInteret();
        interetCumulatif = interetCumulatif + interetVerse;
        
        periodeCalcule.setVersementInteretCumulatif(interetCumulatif);
    }
    
    
    /**
     * Calcule et inscrit le versement en capital periodique
     * @param periodeCalcule
     */
    private void inscrireVersementCapital(Periode periodeCalcule){
    	
    	double interetVerse = periodeCalcule.getVersementInteret();   
        double versementCapital = versementPeriodique - interetVerse;
        
        periodeCalcule.setVersementCapital(versementCapital);
        
        }
       
    
    
    /**
     * Calcule et inscrit le versement en capital total a ce jour 
     * @param periodeCalcule
     */
    private void inscrireVersementCapitalCumulatif (Periode periodeCalcule){
        
      double capitalVerse = periodeCalcule.getVersementCapital();
            
      capitalCumulatif = capitalCumulatif + capitalVerse;
      periodeCalcule.setVersementCapitalCumulatif(capitalCumulatif);  
        
    }

    /**
     * Calcul le capital de la fin de la periode et inscrit les capitals debut et fin
     * @param periodeCalcule
     */
    private void inscrireCapitalDebutFin(Periode periodeCalcule){
    	
    	double versementCapital = periodeCalcule.getVersementCapital();    
        double capitalFin = capitalDebut - versementCapital;
            
        periodeCalcule.setCapitalDebut(capitalDebut);
        periodeCalcule.setCapitalFin(capitalFin);
            
        capitalDebut = capitalFin;
            
        }

}
