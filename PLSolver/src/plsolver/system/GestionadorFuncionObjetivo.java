
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
        String error=validarFuncionObjetivo(funcion);
        if(error==null){
            return null;
        }
        if(error.equals("ok")){
            System.out.println("no esta vacia");
            FuncionObjetivo fo=new FuncionObjetivo(listaVariables.size());
            llenarFuncionObjetivo(fo);
            return fo;
        }
        return null;
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
                System.out.println(aux+"es un signo");
                this.listaSignos=ls;
            }else{
                //es termino
                StringTokenizer st2 = new StringTokenizer(aux,"abcdefghijklmnopqrstuvwxyz",true);//separamos coeficientes de variables
                String aux2=null;
                while (st2.hasMoreTokens()) {
                    aux2=st2.nextToken();
                    if(isNumeric(aux2)){
                        //es coeficiente
                        System.out.println(aux2+"es un coeficiente");
                        lc.add(aux2);
                        this.listaCoeficientes=lc;
                    }else{
                        //es variable
                        System.out.println(aux2+"es unavariable");
                        lv.add(aux2);
                        this.listaVariables=lv;
                    }
                }
            }
        }
        if(ls.size()>=1&&lv.size()>=2){
            return 1;
        }else{
            return 0;
        }
    }
    public void llenarFuncionObjetivo(FuncionObjetivo fo){
        switch(fo.getNoVariables()){
            case 2:
                fo.setC1(Integer.parseInt(listaCoeficientes.get(0)));
                fo.setC2(Integer.parseInt(listaCoeficientes.get(1)));
                fo.setS1(listaSignos.get(0));
                fo.setV1(listaVariables.get(0));
                fo.setV2(listaVariables.get(1));
            break;
            case 3:
                fo.setC1(Integer.parseInt(listaCoeficientes.get(0)));
                fo.setC2(Integer.parseInt(listaCoeficientes.get(1)));
                fo.setC3(Integer.parseInt(listaCoeficientes.get(2)));
                fo.setS1(listaSignos.get(0));
                fo.setS2(listaSignos.get(1));
                fo.setV1(listaVariables.get(0));
                fo.setV2(listaVariables.get(1));
                fo.setV2(listaVariables.get(2));
            break;
            case 4:
                fo.setC1(Integer.parseInt(listaCoeficientes.get(0)));
                fo.setC2(Integer.parseInt(listaCoeficientes.get(1)));
                fo.setC3(Integer.parseInt(listaCoeficientes.get(2)));
                fo.setC4(Integer.parseInt(listaCoeficientes.get(3)));
                fo.setS1(listaSignos.get(0));
                fo.setS2(listaSignos.get(1));
                fo.setS3(listaSignos.get(2));
                fo.setV1(listaVariables.get(0));
                fo.setV2(listaVariables.get(1));
                fo.setV2(listaVariables.get(2));
                fo.setV2(listaVariables.get(3));
            break;
            default:
                fo=null;
            break;
        }
    }
    public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("")==false);
    }
}
