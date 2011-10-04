package ca.uqam.inf2015.aut2011.protagoras;

import java.io.*;
import java.util.ArrayList;
import com.google.gson.*;

/**
 * Classe Principale
 * 
 * @author Team L.M.O.
 */
public class Principale {

    /**
     * Retourne la liste des fichiers contenus dans le répertoire des intrants
     * 
     * @param   String  L'URL de la localisation du répertoire d'intrants.
     * @return  ArrayList<File> Liste des fichiers dans le répertoire.
     * @throws  Exception   Si le répertoire n'existe pas, ne contient aucun
     *                      fichiers .json ou n'est pas disponible en lecture.
     */
    private static ArrayList<File> listeFichiers( String repertoireFichiers ) 
            throws Exception {
        
        File repertoireIntrants = new File( repertoireFichiers );
        ArrayList<File> listeFichiersIntrants = new ArrayList<File>();
        
        //vérifier le répertoire et générer la liste des fichiers
        if( repertoireIntrants.isDirectory() /*&& repertoireIntrants.exists()  
                && repertoireIntrants.canRead()*/ ) {
            
            File[] temp = repertoireIntrants.listFiles();
            
            //exclure les répertoires ou les fichiers non-JSON
            for( int i = 0 ; i < temp.length ; ++i ) {
                if( temp[i].getName().endsWith(".json") )
                    listeFichiersIntrants.add( temp[i] );
            }
         
        //verifications sur la validité de la liste    
        } else {
            
            throw new Exception("Répertoire inexistant ou innaccessible en "
                    + "lecture.");
        }
        if( listeFichiersIntrants.isEmpty() )
            throw new Exception("Répertoire vide ou ne contenant aucun fichier "
                    + "JSON."); 

        return listeFichiersIntrants;
    }
    
    /**
     * Retourne la liste des prets en format JSON contenus dans tous les fichiers.
     * 
     * @param   ArrayList<File> La liste des fichiers
     * @return  ArrayList<Pret> La liste des prets
     * @throws  Exception   Si la liste contient des objets JSON invalides
     */
    private static ArrayList<Pret> listePrets( ArrayList<File> listeFichiers )
            throws Exception {
            
        FileReader fr;
        BufferedReader br;
        ArrayList<Pret> listePrets = new ArrayList<Pret>();
        
        try {
            
            //parcours de la liste des fichiers et lecture du contenu
            for( int i = 0; i < listeFichiers.size() ; ++i ) {
                
                String contenuFichier = "";
                fr = new FileReader( listeFichiers.get( i ) );
                br = new BufferedReader( fr );
                while( br.ready() ) {
                    contenuFichier += br.readLine();
                }
                
                //transformation en JSON
                Gson gson = new Gson();
                listePrets.add( gson.fromJson(contenuFichier, Pret.class) );
            }
            
            
            
        } catch( FileNotFoundException e) {
            throw new Exception( "Fichier non-trouvé ou impossible à lire." );
        }
        
        return listePrets;
    }

    /**
     * @param   String[]    Tableau des URL identifiant la localisation du 
     *                      répertoire contenant les intrants et du répertoire 
     *                      destiné à contenir les extrants.
     */
    public static void main( String[] args ) {
        
        try {
            if( args[0] == null || args[1] == null )
                throw new Exception( "Paramètres en entrée"
                        + " manquants ou invalides" );
            
            //récupère la liste des fichiers dans le répertoire intrant
            ArrayList<File> listeFichierIntrants = listeFichiers( args[0] );
            
            //récupère la liste des prets en format JSON dans les fichiers
            ArrayList<Pret> listePretsOriginaux = listePrets( listeFichierIntrants );

            for( int i = 0; i < listeFichierIntrants.size() ; ++i )
                System.out.println( listeFichierIntrants.get( i ).getName() );
            
            System.out.println(listePretsOriginaux.size());
            for( int i = 0; i < listePretsOriginaux.size() ; ++i )
                System.out.println( listePretsOriginaux.get( i ).getDescription() );
          
        } catch( JsonSyntaxException jse ) {
            System.out.println( jse.fillInStackTrace() );
        } catch( ArrayIndexOutOfBoundsException aob ) {
            System.out.println( aob.fillInStackTrace() );
        } catch( Exception e ) {
            System.out.println( e.fillInStackTrace() );
        }
        
    }
    
}
