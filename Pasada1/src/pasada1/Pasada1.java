/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasada1;

import java.io.*;
import java.util.*;

/**
 *
 * @author Walter
 */
public class Pasada1 {//inicio pasada 1
    
    //Atributos
    File macro;
    FileReader leer_archivo;
    BufferedReader bufer_leer;
    String linea;
    
    //Constructor
    public Pasada1(String ruta) throws FileNotFoundException{//inicio constructor
        macro = new File(ruta);
        leer_archivo=new FileReader(macro);
        bufer_leer=new BufferedReader(leer_archivo);
    }//fin constructor
    
    //Metodos 
    public String Leer() throws IOException{//inicio Leer
        //Si no es el final del archivo
        while(bufer_leer.ready()){//inicio while
            //Si la linea que se lee esta vacia 
            if(!(linea=bufer_leer.readLine()).equals("\000")){//inicio if
                return linea;
            }//fin if
        }//fin while
        bufer_leer.close(); //cerramos flujo
        return null;
    }//fin Leer
    
    public static void main(String[] args) throws FileNotFoundException, IOException{//inicio main
       Pasada1 P1 = new Pasada1("Archivo.txt");
       StringTokenizer tokens,tokens2,tokens3,tokens4;
       boolean bandera = true;
       String linea,nombre,parametros;
       String macro;
       
       
       //Leer todas las lineas del archivo
       do{//inicio do
           linea=P1.Leer();
           //Si la linea que se lee no es null
           if(linea!=null){//inicio if
               linea = linea.toUpperCase();
               //pasamos a tokens la cadena a romper
               tokens = new StringTokenizer(linea);
               
               
               //Si la linea no es END
               while(!linea.equals("END")){//inicio while no es END
                   
                   //Separamos la cadena por espacios
                   while(tokens.hasMoreElements()){//inicio while separar 
                       //Guardamos en variable macro lo que este en token
                       macro=tokens.nextToken();
                       
                       //si lo que separa de la linea es MACRO
                       if(macro.equals("MACRO")){//inicio if MACRO
                           //almacena en parametros los parametros de la MACRO
                           parametros=tokens.nextToken();
                           System.out.println(parametros);
                           //separamos la linea para el nombre
                           tokens2= new StringTokenizer(linea);
                           
                           //mientras halla tokens por leer para guardar
                           //el nombre de las MACROS
                           while(tokens2.hasMoreTokens()){//inicio while nombre MACRO
                               nombre=tokens2.nextToken();
                               //Si lo que separa es distinto de macro y de los parametros
                               //guaradmos nombre de la macro
                               
                               if(!nombre.equals("MACRO")&&!nombre.equals(parametros)){//inicio if
                                   System.out.println(nombre);                                  
                               }//fin if
                           }//fin while nomre MACRO
                           
                           // Si la linea no es MEND
                           //Guardamos lineas siguientes
                           while(!linea.equals("MEND")){//inicio while MEND
                               //Filtro para MACROS anidadas
                               tokens3 = new StringTokenizer(linea);
                               boolean esmacro=false;
                               
                               do{
                                   while(tokens3.hasMoreTokens()){//inicio while
                                       macro=tokens3.nextToken();
                                       if(macro.equals("MACRO")){//inicio if
                                           parametros=tokens3.nextToken();
                                           System.out.println(parametros);
                                           tokens4= new StringTokenizer(linea);
                                           while(tokens4.hasMoreTokens()){//inicio whilw
                                               nombre=tokens4.nextToken();
                                               if(!nombre.equals("MACRO")&&!nombre.equals(parametros)){//inicio if
                                                   System.out.println(nombre);
                                                   esmacro=false;
                                               }//fin if
                                           }//fin while
                                        }//fin if
                                       else{//inicio else
                                           esmacro=false;
                                       }//fin else
                                   }//fin while 
                               }while(esmacro==true);
                      
                               System.out.println(linea+"guardada");
                               linea=P1.Leer();
                               linea=linea.toUpperCase();
                           }//fin while MEND
           
                      
                       }//fin if MACRO
                       
                   }//fin while separar
                   
                  
                   //System.out.println(linea);
                   break;//rompemos el while para salir del ciclo
               }//fin while no es END
               
               //Si si es END leemos siguiente linea
               if(linea.equals("END")){//inicio if
                   linea=P1.Leer();
                   
               }//fin if
               
           }//fin if
           else bandera=false;
       }while(bandera==true); //fin do
       
       
    }//fin main
    
}//fin pasada 1
