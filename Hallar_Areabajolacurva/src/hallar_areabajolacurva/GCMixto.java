/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hallar_areabajolacurva;

import java.util.ArrayList;
import java.util.Collections;
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
    double altura_cuadradotemp = 0;
    double altura_cuadrado = 0;
    double resultado;
    int a1 = 0;
    double fmenor=0;
    double fmayor = 0;


    public void Validar_periodo_mixto() {
        // variables 
        double a, b;
        double m;
        double x0 = 1;
        double validacion = 0;
        double semilla = 1;

        double cont = 0;
        boolean reglauno, reglados, reglatres, indeter = true;
        String valores = "";
        String reglasnocum = "";
        ArrayList<Double> x_replace = new ArrayList<Double>();
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
        /* double[] */
        temp = new double[grado + 1];
        //Se crea arreglo guardar los signos en un arreglo 
        /*String[]*/ signo = new String[grado];

        //Se solicita frontera mayor y menos para saber en que rango se va a reemplazar la funcion
        fmenor = Double.parseDouble(JOptionPane.showInputDialog("Inserte Frontera menor de x"));
        fmayor = Double.parseDouble(JOptionPane.showInputDialog("Inserte Frontera mayor de x"));

        //Se inicializa para mostrar la funcion escrita
        String fun = "";

        //Variable para mostrar el grado de la funcion
        int expo = grado;
        //Se crea un for para llenar el arreglo de acuerdi al grado ingresado
        for (int i = 0; i < array.length; i++) {
            //Se solicita el coeficiente
            array[i] = Double.parseDouble(JOptionPane.showInputDialog("Coeficiente de X^" + expo));
            //Se ingresa las variables en un arreglo temporal para luego ser reemplazadas en la funcion 
            temp[i] = array[i];
            //Se realiza vaidacion para verficar la cantidad de signos y que no vuelva a pedir signos en la funcion
            if (i != array.length - 1) {
                //Se solicita los signos 
                signo[i] = JOptionPane.showInputDialog("Signo: " + array[i] + "X^" + expo);
            }
            //Se realiza validacion para mostrar para que en la ultima pocision no coloque el signo  
            if (i == array.length - 1) {
                fun += (array[i]);
            } else {
                //Se muestra el coeficiente con X y su exponente, luego muestra el signo 
                fun += (array[i] + "X^" + expo + " ");
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
            if ((cont % 2) == 0) {
                //Normalizar numeros en X    
            double numero_en_x = normaliza_en_x(fmayor, fmenor,(x0/m));
            double calculo   =  calculavalor(numero_en_x, array, signo);
//            double calculo = calculavalor((x0 / m), array, signo);
            resultado = 0;
            x.add(numero_en_x);
//            x.add(x0/m);
                x_replace.add(calculo);
            } else {
         altura_cuadradotemp = calculavalor(fmayor,array,signo);
        resultado = 0;
      double numero_en_y = normaliza_en_y(altura_cuadradotemp,0,(x0/m));
        resultado = 0;
      y.add(numero_en_y);
//        y.add(x0 / m);
    altura_cuadradotemp = 0;
            }
//  se valida que no se quede en un loop 
            if (validacion == x0 || cont == m) {
                x0 = semilla;
                indeter = false;
            }
        } while (x0 != semilla);
//         Collections.reverse(y);
        System.out.println("El periodo total es:" + cont);

        for (int i = 0; i < x_replace.size(); i++) {
            if (y.get(i) < x_replace.get(i)) {
                bajocurva = bajocurva + 1;
            } else {
                arribacurva = arribacurva + 1;
            }
        }
        System.out.println(bajocurva);
        
        altura_cuadrado = calculavalor(fmayor,array,signo);
        
        System.out.println(bajocurva + "/" + (cont / 2));
        result = ((bajocurva / (cont / 2)) * (altura_cuadrado*(fmayor-fmenor)));// (altodelacurva*(mayor-menor))
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

    public double normaliza_en_x(double superior, double inferior, double aleatorio) {
        double resultado = 0;

        resultado = (((superior - inferior) * aleatorio) + inferior);

        return resultado;
    }

    public double normaliza_en_y(double superior, double inferior, double aleatorio) {
        double resultado = 0;

        resultado = (((superior - inferior) * aleatorio) + inferior);

        if (resultado > superior || resultado < inferior) {
            int convert = (int) resultado;
            resultado = (double) convert;
        }

        return resultado;
    }

}
