
package plsolver.system;

import java.util.concurrent.ThreadLocalRandom;

public class ResolvedorProblema {
    private ResolverProblema rp;
    private String accion;
    private ManejoNumeros mn;
    private int noIteraciones;
    private int intervaloMax=1000;
    private int noAleatoriosIteracion=10;

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
        System.out.println("...la cantidad de variables en fo es: "+cantidadNoVariables);
        int cantidadNumeros=noAleatoriosIteracion;
        int[ ][ ] numerosAleatoriosFiltrados = new  int[cantidadNumeros][cantidadNoVariables];
        Restriccion[] restricciones=rp.getArregloRestricciones(rp);
        
        int intervalo=0;
        for(int i=0; i<=noIteraciones;i++){
            //intervalo=obtenerIntervaloParcial(intervalo);
            intervalo=intervaloMax;
            //necesitamos almacenar en un array los conjuntos de numeros que cumplen las condiciones
            int[ ][ ] numerosAleatoriosAux =generarNumerosAleatorios(cantidadNoVariables,cantidadNumeros,intervalo);
            for(int k=0;k<cantidadNumeros;k++){//recorremos los numeros generados
                int auxValidadorRestriccion=0;
                for(int l=0;l<cantidadNoVariables;l++){
                    for(int z=0;z<rp.getNoRestricciones();z++){//recorriendo restricciones, restriccion por restriccion
                        if(evaluarRestriccion(restricciones[z],numerosAleatoriosAux[k])){//al algoritmo que evalua los numeros se le envia toda la columna
                            auxValidadorRestriccion++;
                        }
                    }
                }  
                if(auxValidadorRestriccion==rp.getNoRestricciones()){
                    numerosAleatoriosFiltrados[k]=numerosAleatoriosAux[k];
                }
            }
            intervalo=intervalo+intervalo;
        }
        //termina generacion y filtrado de numeros aleatorios
    }
    public int [][] generarNumerosAleatorios(int cantidadNoVariables,int cantidadNumeros,int intervalo){//regresar arreglo
        System.out.println("...el intervalo recibido es "+intervalo);
        int[ ][ ] numerosAleatorios = new  int[cantidadNumeros][cantidadNoVariables];
        int i,j=0;
        System.out.println("///Generar no aleatorios//");
        for(i=0;i<cantidadNumeros;i++){//se llena altura
            for(j=0;j<cantidadNoVariables;j++){//se llena ancho
                numerosAleatorios[i][j]=ThreadLocalRandom.current().nextInt(0, intervalo);
                System.out.print(numerosAleatorios[i][j]+"  ");
            }
        }
        System.out.println("/////////////////////////////////////////");
        //prueba
        
        return numerosAleatorios;
    }
    public boolean evaluarRestriccion(Restriccion restriccion,int [] numerosAleatorios){
        GestionadorRestriccion gr= new GestionadorRestriccion();
        int noVariables=restriccion.getNoVariables();
        int [] coeficientesRestriccion = gr.coeficientesTOArray(restriccion);
        String [] signosRestriccion =gr.signosToArray(restriccion);
        if(!(coeficientesRestriccion.length==numerosAleatorios.length)){
            System.out.println("...Algo anda mal en el evaluo de restriccion");
            return false;
        }
        int operacionIzquierda=0;// primero se tiene un 0 a ese cero se le suma el primer producto de i's
        for(int i=0;i<noVariables;i++){
            System.out.println("...coeficiente :"+i+"es: "+coeficientesRestriccion[i]);
            System.out.println("...noAle :"+i+"es: "+numerosAleatorios[i]);
            int producto=coeficientesRestriccion[i]*numerosAleatorios[i];
            operacionIzquierda=sumadorTerminos(operacionIzquierda,producto,signosRestriccion[i]);//multiplicacion de terminos pos i 
            System.out.println("...operacion Izquiera: "+operacionIzquierda);
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

