
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
            FuncionObjetivo fo=new FuncionObjetivo(listaVariables.size(),funcion);
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
        ArrayList<String> lc = new ArrayList<>();
        ArrayList<String> lv = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(funcion,"abcdefghijklmnopqrstuvwxyz",true);//separamos coeficientes de variables
        String aux=null;
        while (st.hasMoreTokens()) {
            aux=st.nextToken();
            if(isNumeric(aux)){
                //es coeficiente
                System.out.println("coeficiente " +aux);
                lc.add(aux);
                this.listaCoeficientes=lc;
            }else{
                //es variable
                System.out.println("variable " +aux);
                lv.add(aux);
                this.listaVariables=lv;
            }
        }
        if(lv.size()>=2&&lc.size()>=2){
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
                fo.setV1(listaVariables.get(0));
                fo.setV2(listaVariables.get(1));
            break;
            case 3:
                fo.setC1(Integer.parseInt(listaCoeficientes.get(0)));
                fo.setC2(Integer.parseInt(listaCoeficientes.get(1)));
                fo.setC3(Integer.parseInt(listaCoeficientes.get(2)));
                fo.setV1(listaVariables.get(0));
                fo.setV2(listaVariables.get(1));
                fo.setV2(listaVariables.get(2));
            break;
            case 4:
                fo.setC1(Integer.parseInt(listaCoeficientes.get(0)));
                fo.setC2(Integer.parseInt(listaCoeficientes.get(1)));
                fo.setC3(Integer.parseInt(listaCoeficientes.get(2)));
                fo.setC4(Integer.parseInt(listaCoeficientes.get(3)));
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
    public int[] coeficientesTOArray(FuncionObjetivo fo){
        int noVariables = fo.getNoVariables();
        int[ ]   coeficientesArray = new  int[noVariables];
        switch(noVariables){
            case 2:
                coeficientesArray[0]=fo.getC1();
                coeficientesArray[1]=fo.getC2();
            break;
            case 3:
                coeficientesArray[0]=fo.getC1();
                coeficientesArray[1]=fo.getC2();
                coeficientesArray[2]=fo.getC3();
            break;
            case 4:
                coeficientesArray[0]=fo.getC1();
                coeficientesArray[1]=fo.getC2();
                coeficientesArray[2]=fo.getC3();
                coeficientesArray[3]=fo.getC4();
            break;
        }
        return coeficientesArray;
    }
    public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("")==false);
    }
}
