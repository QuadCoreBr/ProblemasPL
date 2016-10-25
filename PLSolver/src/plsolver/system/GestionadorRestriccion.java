package plsolver.system;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class GestionadorRestriccion {
    private Restriccion r;
    private ArrayList<String> listaVariables;
    private ArrayList<String> listaCoeficientes;
    private ArrayList<String> listaSignos;
    private ArrayList<String> desigualdad;
    
    public Restriccion CrearRestriccion(String funcion){
        System.out.println(funcion);
        String error=validarRestriccion(funcion);
        if(error==null){
            return null;
        }
        if(error.equals("ok")){
            System.out.println("no esta vacia");
            Restriccion r=new Restriccion(listaVariables.size());
            //llenarRestriccion(fo);
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
        ArrayList<String> ls = new ArrayList<>();
        ArrayList<String> lc = new ArrayList<>();
        ArrayList<String> lv = new ArrayList<>();
        ArrayList<String> des = new ArrayList<>();
        StringTokenizer st0 = new StringTokenizer(funcion,"<>=",true);//separamos signos de los terminos
        String aux0=null;
        while (st0.hasMoreTokens()) {
            aux0=st0.nextToken();
            if(aux0.equals("<")||aux0.equals(">")||aux0.equals("=")){
                ls.add(aux0);
                System.out.println(aux0+"es un signo");
                this.desigualdad=des;
            }
        }
        if(des.size()==0){
            return -1;
        }
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
    public void llenarRestriccion(FuncionObjetivo fo){
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
