
package plsolver.system;

import java.util.concurrent.ThreadLocalRandom;

public class ResolvedorProblema {
    private ResolverProblema rp;
    private String accion;
    private ManejoNumeros mn;
    private int noIteraciones;
    private int divisor=10;

    public ResolvedorProblema(ResolverProblema rp, String accion,int noIteraciones) {
        this.rp = rp;
        this.accion = accion;
        this.noIteraciones=noIteraciones;
        resolver();
    }
    public void resolver(){
        //1 generar numeros aleatorios ¿Cuantas veces se hara? noIteraciones
        //2 de esos numeros escoger cuales cumplen 
        int cantidadNoVariables=rp.getFo().getNoVariables();
        int cantidadNumeros=divisor;
        int[ ][ ] numerosAleatoriosFiltrados = new  int[cantidadNumeros][cantidadNoVariables];
        Restriccion[] restricciones=rp.getArregloRestricciones(rp);
        int intervalo=0;
        int intervaloFO=this.obtenerIntervaloFO();
        for(int i=0; i<=noIteraciones;i++){
            intervalo=obtenerIntervaloParcial(intervalo,intervaloFO,divisor);
            //necesitamos almacenar en un array los conjuntos de numeros que cumplen las condiciones
            int[ ][ ] numerosAleatoriosAux =generarNumerosAleatorios(cantidadNoVariables,cantidadNumeros,intervalo);
            for(int k=0;k<cantidadNumeros;k++){//recorremos los numeros generados
                int auxValidadorRestriccion=0;
                for(int z=0;z<=rp.getNoRestricciones();z++){//recorriendo restricciones, restriccion por restriccion
                    if(evaluarRestriccion(restricciones[z],numerosAleatoriosAux[k])){/*al algoritmo que evalua los numeros se le envia toda la columna*/
                        auxValidadorRestriccion++;
                    }
                }
                if(auxValidadorRestriccion==rp.getNoRestricciones()){
                    numerosAleatoriosFiltrados[k]=numerosAleatoriosAux[k];//
                }
            }
            intervalo=intervalo+intervalo;
        }
        //termina generacion y filtrado de numeros aleatorios
    }
    
    public int obtenerIntervaloFO(){
        GestionadorFuncionObjetivo gfo=new GestionadorFuncionObjetivo();
        int [] coeficientesFO=gfo.coeficientesTOArray(rp.getFo());
        mn=new ManejoNumeros();
        return mn.maximo(coeficientesFO);
    }
    public int obtenerIntervaloParcial(int intervalo,int intervaloFO,int divisor){
        return intervalo+(intervaloFO/divisor);
    }
    public int [][] generarNumerosAleatorios(int cantidadNoVariables,int cantidadNumeros,int intervalo){//regresar arreglo
        int[ ][ ] numerosAleatorios = new  int[cantidadNumeros][cantidadNoVariables];
        int i,j=0;

        for(i=0;i<cantidadNumeros;i++){//se llena altura
            for(j=0;j<cantidadNoVariables;j++){//se llena ancho
                numerosAleatorios[i][j]=ThreadLocalRandom.current().nextInt(0, intervalo);
            }
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

