package ca.uqam.inf2015.aut2011.protagoras;

import java.io.*;
import java.util.ArrayList;
import com.google.gson.*;
import java.text.ParseException;

/**
 * Classe Main
 * 
 * @author Team L.M.O.
 */
public class Main {

    /**
     * Formatte le nom du fichier en fonction du type de sortie (erreur ou normal)
     * 
     * @param nomFichier nom du fichier a formatter
     * @param typeSortie 'e' pour erreur, 'n' pour normal
     * @return String avec le nomFichier formatter
     */
    public static String formatNomExtrant( String nomFichier, char typeSortie ) {
        
        String nomFinal = "";
        
        nomFinal = nomFichier.toLowerCase();
        nomFinal = nomFinal.replaceAll( " ", "" );
        
        if( typeSortie == 'n' ) {
            nomFinal += ".json";
        } else if( typeSortie == 'e' ) {
            nomFinal += "_erreur.json";
        }
        
        return nomFinal;
    }
    
    /**
     * Transforme une String quelconque au format Json
     * 
     * @param aTransformer la String a transformer
     * @return la String aTransformer, transformee au format Json
     */
    public static String jsonizeString( String aTransformer ) {
        
        String stringFormattee = "";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        stringFormattee = gson.toJson( aTransformer );       
        
        return stringFormattee;
    }
    
    /**
     * Retourne la liste des fichiers contenus dans le répertoire des intrants
     * 
     * @param   String  L'URL de la localisation du répertoire d'intrants.
     * @return  ArrayList<File> Liste des fichiers dans le répertoire.
     * @throws  Exception   Si le répertoire n'existe pas, ne contient aucun
     *                      fichiers .json ou n'est pas disponible en lecture.
     */
    private static ArrayList<File> creerListeFichiers( String repertoireFichiersIntrants ) 
            throws Exception {
        
        File repertoireIntrants = new File( repertoireFichiersIntrants );
        ArrayList<File> listeFichiersIntrants = new ArrayList<File>();
        
        //vérifier le répertoire et générer la liste des fichiers
        if( repertoireIntrants.isDirectory() && repertoireIntrants.exists()  
                && repertoireIntrants.canRead() ) {
            
            File[] temp = repertoireIntrants.listFiles();
            
            //exclure les répertoires ou les fichiers non-JSON
            for( int i = 0 ; i < temp.length ; ++i ) {
                if( temp[i].getName().endsWith(".json") )
                    listeFichiersIntrants.add( temp[i] );
            }
         
        //verifications sur la validité de la liste    
        } else {
            
            throw new Exception("Repertoire inexistant ou innaccessible en "
                    + "lecture.");
        }
        
        if( listeFichiersIntrants.isEmpty() )
            throw new Exception("Repertoire vide ou ne contenant aucun fichier "
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
    private static ArrayList<Pret> creerListePrets( ArrayList<File> listeFichiers,
            String repertoireFichiersExtrants ) throws Exception {
            
        FileReader fr;
        BufferedReader br;
        Pret pretLu;
        ArrayList<Pret> listePrets = new ArrayList<Pret>();
        
        //parcours de la liste des fichiers et lecture du contenu
        for( int i = 0 ; i < listeFichiers.size() ; ++i ) {
            
            try {
                    String contenuFichier = "";
                    fr = new FileReader( listeFichiers.get( i ) );
                    br = new BufferedReader( fr );
                    while( br.ready() )
                        contenuFichier += br.readLine();
                    
                    //verification du contenu
                    JsonParser jp = new JsonParser();
                    JsonElement je = jp.parse( contenuFichier );
                    if( !je.getAsJsonObject().has("id") 
                            || !je.getAsJsonObject().has("description")
                            || !je.getAsJsonObject().has("montant")
                            || !je.getAsJsonObject().has("nombreAnnee")
                            || !je.getAsJsonObject().has("frequenceRemboursement")
                            || !je.getAsJsonObject().has("tauxInteret")
                            || !je.getAsJsonObject().has("frequenceComposition")
                            )
                        throw new JsonParseException( "Champs manquant ou noms de"
                                + " champs invalides dans le fichier Json intrant"
                                + " correspondant." );
                    
                    //transformation en JSON
                    Gson gson = new Gson();
                    pretLu = gson.fromJson(contenuFichier, Pret.class);
                    listePrets.add( pretLu );

            } catch( FileNotFoundException e) {
                
                throw new Exception( "Fichier non-trouve ou impossible a lire.\n"
                        + e.getMessage() );
                
            } catch( JsonSyntaxException jse ) {
                
                creerNouveauFichierJson( repertoireFichiersExtrants, 
                        formatNomExtrant( listeFichiers.get( i ).getName().substring( 
                            0, listeFichiers.get( i ).getName().lastIndexOf( ".")), 'e' ), 
                        jsonizeString( "Erreur dans la lecture du format JSON.\n" + jse.getMessage() ) );
                
            } catch( Exception e ) {
                
                creerNouveauFichierJson( repertoireFichiersExtrants, 
                        formatNomExtrant( listeFichiers.get( i ).getName().substring( 
                            0, listeFichiers.get( i ).getName().lastIndexOf( ".")), 'e' ), 
                        jsonizeString( e.getMessage() ) );
            }
        }
        
        return listePrets;
    }

    /**
     * Cree un nouveau fichier .json avec le contenu specifie
     * 
     * @param repertoireFichiersExtrants repertoire ou sera cree le fichier
     * @param nomFichier nom du fichier cree
     * @param contenu contenu a imprimer dans le fichier
     * @return boolean true si la methode s'est derouler sans exceptions
     * @throws Exception si l'ecriture du fichier ne peut etre fait avec les 
     * parametres recus
     */
    public static boolean creerNouveauFichierJson( String repertoireFichiersExtrants,
            String nomFichier, String contenu ) throws Exception {
        
        //verifications sur le path
        if( repertoireFichiersExtrants == null || repertoireFichiersExtrants.length() == 0 
                || nomFichier == null || nomFichier.length() == 0 )
            throw new Exception( "Nom de repertoire ou de fichier invalide." );
        
        if( !( new File( repertoireFichiersExtrants ).exists() ) 
                || !( new File( repertoireFichiersExtrants ).isDirectory() ) )
            throw new Exception( "Repertoire specifie inexistant." );

        //creation du fichier
        String nomCompletFichier = repertoireFichiersExtrants + File.separator
            + nomFichier;

        File nouveauFichierJson = new File( nomCompletFichier );

        //ecriture du contenu
        FileWriter fr = new FileWriter( nouveauFichierJson );
        BufferedWriter bw = new BufferedWriter( fr );
        PrintWriter printer = new PrintWriter( bw );
        printer.write( contenu );
        printer.close();
        bw.close();
        fr.close();
        
        return true;
    }
    
    /**
     * Verfie les erreurs de contenu dans les Prets lus et retourne dans un fichier
     * .json la liste des erreurs pour chacun, si applicable, dans le repertoire
     * specifie.
     * @param listePret la liste des prets en traitement
     * @param repertoireFichiersExtrants le repertoire de destination des fichiers
     * @return la liste de prets sans les prets contenant des erreurs
     * @throws Exception si une erreur survient dans la creation du fichier .json
     */
    public static ArrayList<Pret> verifierErreurPrets( ArrayList<Pret> listePret, 
            String repertoireFichiersExtrants ) throws Exception {
        
        ArrayList<String> erreursPrets;
        ArrayList<Pret> pretsVerifies = new ArrayList<Pret>();
        
        for( int i = 0 ; i < listePret.size() ; ++i ) {
            
            erreursPrets = listePret.get( i ).validerPret();
            
            if( erreursPrets.size() > 0 ) {
                
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String stringFormattee = gson.toJson( erreursPrets ); 
                creerNouveauFichierJson( repertoireFichiersExtrants, 
                        formatNomExtrant( listePret.get( i ).getId(), 'e'), 
                        stringFormattee );
            } else {
                pretsVerifies.add( listePret.get(i) );
            }
        }
        
        return pretsVerifies;
    }
    
    /**
     * Calcul la liste des prets et retourne le resultat dans un fichier .json
     * dans le repertoire specifie.
     * @param listePret la liste des prets a calculer
     * @param repertoireFichiersExtrants le repertoire de destination des fichiers
     * @return boolean true si la methode s'est derouler sans exceptions
     * @throws Exception si une erreur survient dans la creation du fichier .json
     */
    public static boolean calculerPrets( ArrayList<Pret> listePret, 
            String repertoireFichiersExtrants ) throws Exception {
    
        CalculsHypothecaires calculateur = new CalculsHypothecaires();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        //parcourir la liste des prets a calculer
        for( int i = 0 ; i < listePret.size() ; ++i ) {
            
            try {
                //calcul du pret et impression au format Json
                Pret pretCalc = calculateur.creerPretCalcule( listePret.get( i ) );
                String pretJson = gson.toJson( pretCalc );
                creerNouveauFichierJson( repertoireFichiersExtrants, 
                        formatNomExtrant( listePret.get( i ).getId(), 'n'), pretJson );
                
            } catch ( ParseException pe ) {
                
                creerNouveauFichierJson( repertoireFichiersExtrants, 
                        listePret.get( i ).getId() + "_erreurs.json", 
                        jsonizeString( "Erreur dans le traitement des donnees du "
                        + "pret.\n" + pe.getMessage() ) );
            }
            
        }
        
        return true;
    }

    /**
     * Main
     * 
     * @param args Tableau des URL identifiant la localisation du répertoire 
     * contenant les intrants et du répertoire destiné à contenir les extrants.
     */
    public static void main( String[] args ) {
        
        try {
            if( args[0] == null || args[1] == null )
                throw new Exception( "Parametres en entree manquants ou invalides" );
            
            String repertoireIntrants = args[0];
            String repertoireExtrants = args[1];

            //récupère la liste des fichiers dans le répertoire intrant
            ArrayList<File> listeFichierIntrants = creerListeFichiers( repertoireIntrants );
            
            //récupère la liste des prets en format JSON dans les fichiers
            ArrayList<Pret> listePretsOriginaux = creerListePrets( listeFichierIntrants, 
                    repertoireExtrants );
            
            //verification des erreurs de valeur dans les prets charges
            ArrayList<Pret> pretsVerifies = verifierErreurPrets( listePretsOriginaux, repertoireExtrants );
            
            //parcourir/calculer
            calculerPrets( pretsVerifies, repertoireExtrants );

        } catch( JsonSyntaxException jse ) {
            System.out.println( jse.fillInStackTrace() );
        } catch( ArrayIndexOutOfBoundsException aob ) {
            System.out.println( aob.fillInStackTrace() );
        } catch( Exception e ) {
            System.out.println( e.fillInStackTrace() );
        }
        
    }
    
}
