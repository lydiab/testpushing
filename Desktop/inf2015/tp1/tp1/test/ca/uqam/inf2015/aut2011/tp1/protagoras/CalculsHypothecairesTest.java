/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.inf2015.aut2011.tp1.protagoras;

import ca.uqam.inf2015.aut2011.tp1.protagoras.Pret;
import ca.uqam.inf2015.aut2011.tp1.protagoras.Periode;
import ca.uqam.inf2015.aut2011.tp1.protagoras.CalculsHypothecaires;
import ca.uqam.inf2015.aut2011.tp1.protagoras.PretCalcule;
import java.text.ParseException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Didier
 */
public class CalculsHypothecairesTest {
    
    Pret pretOk = new Pret ("id", "description", 100000, 25, 12, 6, 2 );
    Pret pretMontantZero = new Pret ("id", "description", 0, 25, 12, 6, 2 );
    Pret pretInteretZero = new Pret ("id", "description", 100000, 25, 12, 0, 2 );
    int noPeriode = 1; 
    double versementTotal = 639.81;
    double capitalDebut = 100000.00 ;
    double versementInteret = 493.86;
    double versementCapital =  145.95;
    double capitalFin = 99854.05;
    double versemenTotalCumulatif = 639.81;
    double versementInteretCumulatif = 493.86;
    double versementCapitalCumulatif = 145.95;
    int noPeriodeDerniere = 300; 
    double versementTotalDerniere = 637.66;
    double capitalDebutDerniere = 634.53  ;
    double versementInteretDerniere = 3.13;
    double versementCapitalDerniere =  634.53;
    double capitalFinDerniere = 0.00;
    double versemenTotalCumulatifDerniere = 191940.85;
    double versementInteretCumulatifDerniere = 91940.85;
    double versementCapitalCumulatifDerniere = 100000.00;
    
    public CalculsHypothecairesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of creerPretCalcule method dans le cas d'un pret avec tous les valeurs acceptées.
     */
  
     @Test
    public void testCreerPretCalculePremierePeriode() throws Exception {
        System.out.println("creerPretCalcule");
        CalculsHypothecaires instance = new CalculsHypothecaires();
        PretCalcule result = instance.creerPretCalcule(pretOk);
        Periode premierePeriode = result.getAmortissement().get(0);
        Double epsilon = 0.00;
   
        assertEquals(versementTotal, premierePeriode.getVersementTotal(), epsilon);
        assertEquals(capitalDebut, premierePeriode.getCapitalDebut(), epsilon );
        assertEquals(versementInteret, premierePeriode.getVersementInteret(), epsilon);
        assertEquals(versementCapital, premierePeriode.getVersementCapital(), epsilon);
        assertEquals(versemenTotalCumulatif, premierePeriode.getVersementTotalCumulatif(),epsilon);
        assertEquals(versementInteretCumulatif, premierePeriode.getVersementInteretCumulatif(), epsilon );
        assertEquals(versementCapitalCumulatif, premierePeriode.getVersementCapitalCumulatif(), epsilon);
        assertEquals(capitalFin, premierePeriode.getCapitalFin(), epsilon); 
    }
     /**
      * Tester les resultat de la derniere periode
      * @throws Exception 
      */
     @Test
     public void testCreerPretCalculeDernierePeriode() throws Exception {
        System.out.println("creerPretCalcule");
        CalculsHypothecaires instance = new CalculsHypothecaires();
        PretCalcule result = instance.creerPretCalcule(pretOk);
        Periode dernierePeriode = result.getAmortissement().get(299);
        Double epsilon = 0.00;
   
        assertEquals(versementTotalDerniere, dernierePeriode.getVersementTotal(), epsilon);
        assertEquals(capitalDebutDerniere, dernierePeriode.getCapitalDebut(), epsilon );
        assertEquals(versementInteretDerniere, dernierePeriode.getVersementInteret(), epsilon);
        assertEquals(versementCapitalDerniere, dernierePeriode.getVersementCapital(), epsilon);
        assertEquals(versemenTotalCumulatifDerniere, dernierePeriode.getVersementTotalCumulatif(),epsilon);
        assertEquals(versementInteretCumulatifDerniere, dernierePeriode.getVersementInteretCumulatif(), epsilon );
        assertEquals(versementCapitalCumulatifDerniere, dernierePeriode.getVersementCapitalCumulatif(), epsilon);
        assertEquals(capitalFinDerniere, dernierePeriode.getCapitalFin(), epsilon); 
    }
  
     /**
      * Tester les resultat avec un montant a zero
      * 
      * @throws Exception 
      */
     @Test
    public void testCreerPretCalculeAvecMontantZero() throws Exception {
        System.out.println("creerPretCalculeMontantZero");
        CalculsHypothecaires instance = new CalculsHypothecaires();
        PretCalcule result = instance.creerPretCalcule(pretMontantZero);
        Periode premierePeriode = result.getAmortissement().get(0);
        Double epsilon = 0.00;
   
        assertEquals(0.0, premierePeriode.getVersementTotal(), epsilon);
        assertEquals(0.0, premierePeriode.getCapitalDebut(), epsilon );
        assertEquals(0.0, premierePeriode.getVersementInteret(), epsilon);
        assertEquals(0.0, premierePeriode.getVersementCapital(), epsilon);
        assertEquals(0.0, premierePeriode.getVersementTotalCumulatif(),epsilon);
        assertEquals(0.0, premierePeriode.getVersementInteretCumulatif(), epsilon );
        assertEquals(0.0, premierePeriode.getVersementCapitalCumulatif(), epsilon);
        assertEquals(0.0, premierePeriode.getCapitalFin(), epsilon); 
    }
     
    /* Test si on passe un pret avec valeurs par défaut
     */  
    @Test(expected= NumberFormatException.class)  
     public void pretVide() throws ParseException { 
     Pret pret = new Pret() ;
     CalculsHypothecaires instance = new CalculsHypothecaires();
     PretCalcule result = instance.creerPretCalcule(pret);
    }
    
    /* Test si on passe un pret avec frequence remboursement a zero
     */  
    @Test(expected= NumberFormatException.class)  
     public void frequenceRemboursementZero() throws ParseException { 
     Pret pret = new Pret (" ", " ",  100000, 25, 0, 6,2);
     CalculsHypothecaires instance = new CalculsHypothecaires();
     PretCalcule result = instance.creerPretCalcule(pret);
    }
     
    
    /* Test si on passe un pret avec nombre année  a zero
     */  
    @Test(expected= NumberFormatException.class)  
     public void nombreAnnéeZero() throws ParseException { 
     Pret pret = new Pret (" ", " ",  100000, 0, 12, 6, 2);
     CalculsHypothecaires instance = new CalculsHypothecaires();
     PretCalcule result = instance.creerPretCalcule(pret);
    }

    /**
     * tester si la methode enregistrer les infos du pret dans pretcalcule.
     */
    @Test
    public void testEnregistrerInfoPret() {
        System.out.println("enregistrerInfoPret");
        PretCalcule pretCalcule = new PretCalcule();
        Pret pret = pretOk;
        CalculsHypothecaires instance = new CalculsHypothecaires();
        Double epsilon = 0.0;
        instance.enregistrerInfoPret(pretCalcule, pret);
        assertEquals (pret.getDescription(), pretCalcule.getDescription());
        assertEquals (pret.getFrequenceComposition(), pretCalcule.getFrequenceComposition());
        assertEquals (pret.getFrequenceRemboursement(), pretCalcule.getFrequenceRemboursement());
        assertEquals (pret.getId(), pretCalcule.getId());
        assertEquals (pret.getMontant(), pretCalcule.getMontant(), epsilon);
        assertEquals (pret.getNombreAnnee(), pretCalcule.getNombreAnnee());
        assertEquals (pret.getTauxInteret(), pretCalcule.getTauxInteret(), epsilon);
    }

    /**
     * Tester si le calculs du versement periodique est exact.
     */
    @Test
    public void testCalculerVersementPeriodique() throws Exception {
        System.out.println("calculerVersementPeriodique");
        Pret pret = pretOk;
        PretCalcule pretCalcule = new PretCalcule ();
        CalculsHypothecaires instance = new CalculsHypothecaires();    
        instance.enregistrerInfoPret(pretCalcule, pret) ;
        double expResult = 639.81;
        Double epsilon = 0.00;
        double result = instance.calculerVersementPeriodique(pretCalcule);
        assertEquals(expResult, result, epsilon);
       }

    /**
     * Tester si le calcul du taux mensuel est exact
     */
    @Test
    public void testCalculerTauxMensuel() {
        System.out.println("calculerTauxMensuel");
        Pret pret = pretOk;
        PretCalcule pretCalcule = new PretCalcule ();
        CalculsHypothecaires instance = new CalculsHypothecaires();    
        instance.enregistrerInfoPret(pretCalcule, pret) ;
        Double epsilon = 0.00;
        double expResult = 0.004938622031196882;
        double result = instance.calculerTauxMensuel(pretCalcule);
        assertEquals(expResult, result, epsilon);
    }

    /**
     * Test si la methode qui formate a deux decimales  est exacte.
     */
    @Test
    public void testFormater2decimales() throws Exception {
        System.out.println("formater2decimales");
        double nombre1 = 12.1234;
        double nombre2 = 12.1249;
        double nombre3 = 12.1250;
        CalculsHypothecaires instance = new CalculsHypothecaires();
        double expResult1_2 = 12.12;
        double expResult3 = 12.13;
        double result1 = instance.formater2decimales(nombre1);
        double result2 = instance.formater2decimales(nombre2);
        double result3 = instance.formater2decimales(nombre3);
        Double epsilon = 0.00;
        assertEquals(expResult1_2, result1, epsilon);
        assertEquals(expResult1_2, result2, epsilon);
        assertEquals(expResult3, result3, epsilon);
    }
}
