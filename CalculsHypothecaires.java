/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.inf2015.aut2011.protagoras;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Netbook
 */
public class CalculsHypothecaires {
  
 
    private double interetCumulatif = 0;
    private double capitalCumulatif = 0;
    private double capitalDebut = 0;
    private double tauxMensuel = 0;
    private double versementPeriodique = 0;
    
    

    
    /**
     * Cree un pret calcule avec periode d'amortissement et versement periodique
     * @param pret pret a calcule
     * @return pretCalcule 
     */
    public PretCalcule creerPretCalcule (Pret pret){
        
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
    public double calculerVersementPeriodique (PretCalcule pretCalcule){
        
        int frequenceRemboursement = pretCalcule.getFrequenceRemboursement();
        int nombreAnnee = pretCalcule.getNombreAnnee();
        
        tauxMensuel = calculerTauxMensuel(pretCalcule);
        
        
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
    public ArrayList <Periode>  creerTableauAmortissement(PretCalcule pretCalcule){
       
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
    public Periode creerPeriode (int numeroPeriode){
            
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
    
    public double formater2decimales (Double nombre) throws ParseException{
        
        DecimalFormat format = new DecimalFormat ("0.00");
        String nombreFormate  = format.format(nombre);
       
        Double doubleFormate = (Double) format.parse(nombreFormate);
        
        return doubleFormate;
    }
}
