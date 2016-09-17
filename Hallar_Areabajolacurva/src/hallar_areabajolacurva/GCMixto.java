/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hallar_areabajolacurva;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class GCMixto {
    
     public void Validar_periodo_mixto() {
       // variables 
        double a, b; 
        double m;
        double x0 = 1;
        double validacion = 0;
        double semilla = 1;
        int resultado;
        double cont = 0;
        boolean reglauno, reglados, reglatres,indeter=true;
        String valores = "";
        String reglasnocum = "";
        
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();
        double bajocurva = 0;
        double arribacurva = 0;
        double result = 0;
        
        //Se solicitan los datos
        a = Double.parseDouble(JOptionPane.showInputDialog("Introdusca el valor de A "));
        b = Double.parseDouble(JOptionPane.showInputDialog("Introdusca el valor de B "));
        m = Double.parseDouble(JOptionPane.showInputDialog("Introdusca el valor de M "));


//Generador de numeros aleatorios
        do {
//Ecuacuion del generador mixto
            validacion = x0;
            x0 = (((a * x0) + b) % m);
// contador para saber el perido
           
            cont = cont + 1;
            if((cont%2) == 0){
            y.add((Math.pow((x0/m), 2)));
            }else{
            x.add(x0/m);
            }
//  se valida que no se quede en un loop 
            if(validacion==x0 || cont == m){
                x0 = semilla;
                indeter = false;
            }     
        } while (x0 != semilla);
        System.out.println("El periodo total es:" + cont);
               
        
         for (int i = 0; i < x.size(); i++) {
            if(y.get(i)< x.get(i)){
               bajocurva = bajocurva + 1; 
            }else{
              arribacurva = arribacurva + 1;
            }  
         }
         System.out.println(bajocurva);
         
         System.out.println(bajocurva+"/"+cont);
         result = ((bajocurva/cont)*1);
         // Se imprime el resultado
         System.out.println(result);
         
     }
}
