package ca.uqam.aut2011.inf2015;

import java.text.DecimalFormat;

public class e {

	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DecimalFormat  dec2;                 
	    dec2 = new DecimalFormat ( "0.00" );
	    
	    DecimalFormat  dec4;                 
	    dec4 = new DecimalFormat ( "0.0000" );
	    
		
		double capitalFinal = 100000.0000;
															//intéret  , nb de composition		// nb de composition, nb de periode par année 
		double tauxmensuel =  (double) (Math.pow((1.00000+ 0.060000/     2.00000),          (2.000000/                 12.00000)) - 1.0000000);
									// capital final; 																							// NB DE PERIODE TOTAL 			
		double versementTotal  = (capitalFinal * tauxmensuel) / ( 1.00000000000 - ( 1.000000000/ Math.pow((1.000000000+tauxmensuel),(300.0000000))));
		
		double versementInteret = 100000 * tauxmensuel;
		double versementcapital = versementTotal-versementInteret;
		capitalFinal = capitalFinal - versementTotal;
		
		System.out.println("le taux mensuel est : " + dec4.format (tauxmensuel));
		System.out.println("le versement total  est : " + dec2.format (versementTotal));
		System.out.println("le taux versement intéret est : " + dec4.format (versementInteret));
		System.out.println("le taux versement capital est : " + dec4.format (versementcapital));
		System.out.println("le capital final est : " + dec2.format (capitalFinal ));
	
		
		
		
	}

}
