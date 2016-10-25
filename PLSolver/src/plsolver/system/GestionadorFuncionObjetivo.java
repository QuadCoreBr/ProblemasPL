
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
            
        }
        return fo;
    }

    public String validarFuncionObjetivo(String funcion){
        String error="";
        if(funcion.isEmpty()){
            return "El campo no puede estar vacio";
        }else{
            separarTerminosDeFuncionObjetivo(funcion);
            return "ok";
        }
    }
    public ArrayList obtenerListaCoeficientes(String funcion){
        ArrayList<String> listaCoeficientes = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(funcion,"+-",true);
        String termino=null;
        while (st.hasMoreTokens()) {
            termino=st.nextToken();
            if(!termino.equals("+")||!termino.equals("-")){
                //es un termino
                StringTokenizer st2 = new StringTokenizer(funcion,"+-",true);
                String coeficiente=null;
                while (st2.hasMoreTokens()) {
                    coeficiente=st.nextToken();
                    if(isNumeric(coeficiente)==true){
                        //es coeficiente
                        listaCoeficientes.add(coeficiente);
                    }
                }
            }
        }
        return listaCoeficientes;
    }
    public ArrayList obtenerListaVariables(String funcion){
        ArrayList<String> listaVariables = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(funcion,"+-",true);
        String termino=null;
        while (st.hasMoreTokens()) {
            termino=st.nextToken();
            if(!termino.equals("+")||!termino.equals("-")){
                //es un termino
                StringTokenizer st2 = new StringTokenizer(funcion,"+-",true);
                String variable=null;
                while (st2.hasMoreTokens()) {
                    variable=st.nextToken();
                    if(isNumeric(variable)==false){
                        //es variable
                        listaVariables.add(variable);
                    }
                }
            }
        }
        return listaCoeficientes;
    }
    public ArrayList obtenerListaSignos(String funcion){
        ArrayList<String> listaSignos = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(funcion,"+-",true);
        String termino=null;
        while (st.hasMoreTokens()) {
            termino=st.nextToken();
            if(termino.equals("+")||termino.equals("-")){
                listaSignos.add(termino);
            }
        }
        return listaSignos;
    }
    public void separarTerminosDeFuncionObjetivo(String funcion){
        this.listaCoeficientes=obtenerListaCoeficientes(funcion);
        this.listaVariables=obtenerListaVariables(funcion);
        this.listaSignos=obtenerListaSignos(funcion);
    }
    public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("")==false);
    }
}
