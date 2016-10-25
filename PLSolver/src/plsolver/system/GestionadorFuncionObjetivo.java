
package plsolver.system;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class GestionadorFuncionObjetivo {
    private FuncionObjetivo fo;
    private ArrayList<String> listaVariables;
    private ArrayList<String> listaCoeficientes;
    private ArrayList<String> listaSignos;
    
    public FuncionObjetivo crearFuncionObjetivo(String funcion){
        System.out.println(funcion);
        if(validarFuncionObjetivo(funcion).equals("ok")){
            System.out.println("no esta vacia");
            return fo;
        }else{
            return null;
        }
    }

    public String validarFuncionObjetivo(String funcion){
        String error="";
        if(funcion.isEmpty()){
            return "El campo no puede estar vacio";
        }else{
            if(separarTerminosDeFuncionObjetivo(funcion)==1){
                return "ok";
            }else{
                return null;
            }
        }
    }
    public int separarTerminosDeFuncionObjetivo(String funcion){
        ArrayList<String> ls = new ArrayList<>();
        ArrayList<String> lc = new ArrayList<>();
        ArrayList<String> lv = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(funcion,"+-",true);//separamos signos de los terminos
        String aux=null;
        while (st.hasMoreTokens()) {
            aux=st.nextToken();
            if(aux.equals("+")||aux.equals("-")){
                ls.add(aux);
                //System.out.println(aux+"es un signo");
                this.listaSignos=ls;
            }else{
                //es termino
                StringTokenizer st2 = new StringTokenizer(aux,"abcdefghijklmnopqrstuvwxyz",true);//separamos coeficientes de variables
                String aux2=null;
                while (st2.hasMoreTokens()) {
                    aux2=st2.nextToken();
                    if(isNumeric(aux2)){
                        //es coeficiente
                        //System.out.println(aux2+"es un coeficiente");
                        lc.add(aux2);
                        this.listaCoeficientes=lc;
                    }else{
                        //es variable
                        //System.out.println(aux2+"es unavariable");
                        lv.add(aux2);
                        this.listaVariables=lv;
                    }
                }
            }
        }
        if(ls.size()>=1){
            return 1;
        }else{
            return 0;
        }
    }
    public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("")==false);
    }
}
