
package plsolver.system;

import java.util.concurrent.ThreadLocalRandom;

public class ResolvedorProblema {
    private ResolverProblema rp;
    private String accion;
    private ManejoNumeros mn;

    public ResolvedorProblema(ResolverProblema rp, String accion) {
        this.rp = rp;
        this.accion = accion;
        resolver();
    }
    public void resolver(){
        
    }
    public int obtenerIntervalo(){
        GestionadorFuncionObjetivo gfo=new GestionadorFuncionObjetivo();
        int [] coeficientesFO=gfo.coeficientesTOArray(rp.getFo());
        mn=new ManejoNumeros();
        return mn.maximo(coeficientesFO);
    }
    public int [][] generarNumerosAleatorios(int cantidadX,int cantidadY,int intervalo){//regresar arreglo
        int[ ][ ] numerosAleatorios = new  int[10][4];
        int i,j=0;
        int intervaloDivision=intervalo/10;
        int intervaloAux=intervaloDivision;
        for(i=0;i<cantidadY;i++){//se llena altura
            for(j=0;j<cantidadX;j++){//se llena ancho
                numerosAleatorios[i][j]=ThreadLocalRandom.current().nextInt(0, intervaloAux);
            }
            intervaloAux=intervaloAux+intervaloDivision;
        }
        return numerosAleatorios;
    }
    public boolean evaluarRestriccion(Restriccion restriccion,int [] numerosAleatorios){
        GestionadorRestriccion gr= new GestionadorRestriccion();
        int noVariables=restriccion.getNoVariables();
        int [] coeficientesRestriccion = gr.coeficientesTOArray(restriccion);
        String [] signosRestriccion =gr.signosToArray(restriccion);
        if(!(coeficientesRestriccion.length==numerosAleatorios.length)){
            System.out.println("Algo anda mal en el evaluo de restriccion");
            return false;
        }
        int operacionIzquierda=0;// primero se tiene un 0 a ese cero se le suma el primer producto de i's
        for(int i=0;i<noVariables;i++){
            int producto=coeficientesRestriccion[i]*numerosAleatorios[i];
            operacionIzquierda=sumadorTerminos(operacionIzquierda,producto,signosRestriccion[i]);//multiplicacion de terminos pos i 
        }
        //aqui ya se tiene la operacion izquierda (x) osea x><=u
        switch(restriccion.getDesigualdad()){
            case ">=":
                if(operacionIzquierda>=restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            case "<=":
                if(operacionIzquierda<=restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            case "=":
                if(operacionIzquierda==restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            case ">":
                if(operacionIzquierda>restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            case "<":
                if(operacionIzquierda<restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            default:
                return false;
        }
    }
    public int evaluarFuncionObjetivo(){
        int z=0;
        return z;
    }
    public int sumadorTerminos(int t1,int t2, String signo){
        switch(signo){
            case "+":
                return t1+t2;
            
            case "-":
                return t1-t2;
            default:
                return -1;
        }
    }
}

