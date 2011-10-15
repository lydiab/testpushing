/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.inf2015.aut2011.protagoras;

import com.google.gson.Gson;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author Didier
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
      
        
        Gson jj = new Gson ();
        
        Pret p = new Pret ("test1", "test1", 100000.00, 25, 12, 6, 2);
        CalculsHypothecaires cp = new CalculsHypothecaires ();
        
        
        
       PretCalcule ppp = cp.creerPretCalcule(p);
       
       
      String resultat = jj.toJson(ppp);
        
      System.out.println(resultat);
      
      
        

    }
}
