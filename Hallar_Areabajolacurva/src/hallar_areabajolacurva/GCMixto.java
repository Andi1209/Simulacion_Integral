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
    
    int grado;
    double[] array;
    double[] temp;
    String[] signo;
   
    double resultado;
    int a1 = 0;
    
     public void Validar_periodo_mixto() {
       // variables 
        double a, b; 
        double m;
        double x0 = 1;
        double validacion = 0;
        double semilla = 1;
        
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


        //Se solicita por pantalla el grado del polinomio para crear un vector con las pocisiones de acuerdo al grado
     grado = Integer.parseInt(JOptionPane.showInputDialog("Para continuar con el proceso \n"
            + "Inserte Grado De Funcion Polinomica"));
     
     
      //Se crea vecto mas 1 pocision que es la que no esta acompanada de X
/*   double[]*/ array = new double[grado + 1];
    //Arreglo temporal para guardar valores en cada reeplazo de la funcion
   /* double[] */temp = new double[grado + 1];
    //Se crea arreglo guardar los signos en un arreglo 
    /*String[]*/ signo = new String[grado];
     
    //Se solicita frontera mayor y menos para saber en que rango se va a reemplazar la funcion
    double frontera1 = Double.parseDouble(JOptionPane.showInputDialog("Inserte Frontera menor"));
    double frontera2 = Double.parseDouble(JOptionPane.showInputDialog("Inserte Frontera mayor"));
    
    //Se inicializa para mostrar la funcion escrita
    String  fun = "";
    
    
    //Variable para mostrar el grado de la funcion
        int expo = grado;
        //Se crea un for para llenar el arreglo de acuerdi al grado ingresado
        for (int i = 0; i < array.length; i++) {
            //Se solicita el coeficiente
            array[i] = Double.parseDouble(JOptionPane.showInputDialog("Coeficiente"));
            //Se ingresa las variables en un arreglo temporal para luego ser reemplazadas en la funcion 
            temp [i] = array[i];
            //Se realiza vaidacion para verficar la cantidad de signos y que no vuelva a pedir signos en la funcion
            if (i != array.length - 1) {
                //Se solicita los signos 
                signo[i] = JOptionPane.showInputDialog("Signo");
            }
            //Se realiza validacion para mostrar para que en la ultima pocision no coloque el signo  
             if (i == array.length - 1) {
                fun += (array[i]);
            }else{
                 //Se muestra el coeficiente con X y su exponente, luego muestra el signo 
                  fun += (array[i]+"X^"+expo+" ");
                  fun += (signo[i]);
             }
             expo = expo - 1;
        }
        //Se muestra la funcion 
         JOptionPane.showMessageDialog(null, fun);
    
//Generador de numeros aleatorios
        do {
//Ecuacuion del generador mixto
            validacion = x0;
            x0 = (((a * x0) + b) % m);
// contador para saber el perido
            
            cont = cont + 1;
            if((cont%2) == 0){
            y.add(x0/m);
            }else{
            double calculo   =  calculavalor(x0/m, array, signo);
            resultado = 0;
            x.add(calculo);
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
         result = ((bajocurva/cont)*8);
         // Se imprime el resultado
         System.out.println(result);
         
     }
     
     
     
      public double calculavalor(double valorx, double coefi[], String sig[]) {
        double exponente = grado;
        //Se realiza un ciclo para recorer el arreglo de coeficientes y elevarlos a su exponentes 
        for (int j = 0; j < coefi.length - 1; j++) {
            temp[j] = coefi[j] * Math.pow(valorx, exponente);
            exponente = exponente - 1;
        }
        for (int k = 0; k < coefi.length; k++) {
            //Para que como el primero no tiene signo entonces se pone en la primera pocision 
            if (k != 0) {
                //Se valida si la pocision es un + 
                if (sig[a1].equals("+")) {
                    // Le suma la pocision al resultado, y realiza la operacion 
                    resultado = resultado + (temp[k]);
                }
 //Se valida si la pocision es un -
                if (sig[a1].equals("-")) {
                    resultado = resultado - (temp[k]);
                }
                a1 = a1 + 1;
            } else {
                // Le suma la pocision al resultado, y realiza la operacion 
                resultado = resultado + temp[k];
            }

        }
        a1 = 0;
        
        return resultado;
    }
     
     
}
