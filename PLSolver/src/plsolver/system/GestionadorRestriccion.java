package plsolver.system;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class GestionadorRestriccion {
    private Restriccion r;
    private ArrayList<String> listaVariables;
    private ArrayList<String> listaCoeficientes;
    private ArrayList<String> listaSignos;
    private String desigualdad;
    private Restriccion restricciones[];
    
    public Restriccion CrearRestriccion(String funcion){
        System.out.println(funcion);
        String error=validarRestriccion(funcion);
        if(error==null){
            return null;
        }
        if(error.equals("ok")){
            Restriccion r=new Restriccion(listaVariables.size(),funcion);
            llenarRestriccion(r);
            return r;
        }
        return null;
    }
    public String validarRestriccion(String funcion){
        int error=0;
        if(funcion.isEmpty()){
            return "El campo no puede estar vacio";
        }else{
            error=separarTerminosRestriccion(funcion);
            if(error==-1){
                return "No es Desigualdad";
            }
            if(error==1){
                return "ok";
            }else{
                return null;
            }
        }
    }
     public int separarTerminosRestriccion(String funcion){
        ArrayList<String> lc = new ArrayList<>();
        ArrayList<String> lv = new ArrayList<>();
        String des="";
        StringTokenizer st1 = new StringTokenizer(funcion,"<>=",true);//separamos signos de los terminos
        String aux1=null;
        while (st1.hasMoreTokens()) {
            aux1=st1.nextToken();
            if(aux1.equals("<")||aux1.equals(">")||aux1.equals("=")){//es desigualdad
                des=des+aux1;
                //System.out.println("...desigualdad " +des);
                this.desigualdad=des;
            }else{// es variable o coeficiente
                StringTokenizer st2 = new StringTokenizer(aux1,"abcdefghijklmnopqrstuvwxyz",true);//separamos coeficientes de variables
                String aux2=null;
                while (st2.hasMoreTokens()) {
                    aux2=st2.nextToken();
                    if(isNumeric(aux2)){
                        //es coeficiente
                        //System.out.println("....coeficiente " +aux2);
                        lc.add(aux2);
                        this.listaCoeficientes=lc;
                    }else{// es variable
                        //System.out.println("....variable " +aux2);
                        lv.add(aux2);
                        this.listaVariables=lv;
                    }
                }
            }
        }
        if(lc.size()>=2&&lv.size()>=2&&des.length()>=1){
            return 1;
        }else{
            return 0;
        }
    }
    public void llenarRestriccion(Restriccion r){
        switch(r.getNoVariables()){
            case 2:
                r.setC1(Integer.parseInt(listaCoeficientes.get(0)));
                r.setC2(Integer.parseInt(listaCoeficientes.get(1)));
                r.setCr(Integer.parseInt(listaCoeficientes.get(2)));
                r.setV1(listaVariables.get(0));
                r.setV2(listaVariables.get(1));
                r.setDesigualdad(desigualdad);
            break;
            case 3:
                r.setC1(Integer.parseInt(listaCoeficientes.get(0)));
                r.setC2(Integer.parseInt(listaCoeficientes.get(1)));
                r.setC3(Integer.parseInt(listaCoeficientes.get(2)));
                r.setCr(Integer.parseInt(listaCoeficientes.get(3)));
                r.setV1(listaVariables.get(0));
                r.setV2(listaVariables.get(1));
                r.setV2(listaVariables.get(2));
                r.setDesigualdad(desigualdad);
            break;
            case 4:
                r.setC1(Integer.parseInt(listaCoeficientes.get(0)));
                r.setC2(Integer.parseInt(listaCoeficientes.get(1)));
                r.setC3(Integer.parseInt(listaCoeficientes.get(2)));
                r.setC4(Integer.parseInt(listaCoeficientes.get(3)));
                r.setCr(Integer.parseInt(listaCoeficientes.get(4)));
                r.setV1(listaVariables.get(0));
                r.setV2(listaVariables.get(1));
                r.setV2(listaVariables.get(2));
                r.setV2(listaVariables.get(3));
                r.setDesigualdad(desigualdad);
            break;
            default:
                r=null;
            break;
        }
    }
    public int[] coeficientesTOArray(Restriccion r){
        int noVariables = r.getNoVariables();
        int[ ]   coeficientesArray = new  int[noVariables];
        switch(noVariables){
            case 2:
                coeficientesArray[0]=r.getC1();
                coeficientesArray[1]=r.getC2();
            break;
            case 3:
                coeficientesArray[0]=r.getC1();
                coeficientesArray[1]=r.getC2();
                coeficientesArray[2]=r.getC3();
            break;
            case 4:
                coeficientesArray[0]=r.getC1();
                coeficientesArray[1]=r.getC2();
                coeficientesArray[2]=r.getC3();
                coeficientesArray[3]=r.getC4();
            break;
        }
        return coeficientesArray;
    }
    public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("")==false);
    }
}
