
package plsolver.system;

import java.util.concurrent.ThreadLocalRandom;

public class ResolvedorProblema {
    private final ResolverProblema rp;
    private final String accion;
    private ManejoNumeros mn;
    private final int noIteraciones;
    private final int noAleatoriosIteracion=10;
    private String [][] salida;

    public ResolvedorProblema(ResolverProblema rp, String accion,int noIteraciones) {
        this.rp = rp;
        this.accion = accion;
        this.noIteraciones=noIteraciones;
        switch(accion){
            case "maximizar":
                this.salida=maximizar();
            break;
            case "minimizar":
                //this.salida=minimizar();
            break;
        }
    }
    public String[][] maximizar(){
        String[][] salida=new String[noIteraciones][rp.getFo().getNoVariables()+2];
        for(int z=0;z<noIteraciones;z++){
            Iteracion i=iterar();
            System.out.println("...en la iteracion :"+z+"Z es:"+i.getZ());
            int [][] combinacionMaximizadora=i.getValorVariables();//solo una combinacion
            System.out.println("tamaño de comb max es" +combinacionMaximizadora.length);
                salida[z][0]=Integer.toString(z);
                salida[z][1]=Integer.toString(i.getZ());
                System.out.println("...la combinacion maximizadora es :");
            for(int l=2;l<(i.getFo().getNoVariables())+2;l++){
                salida[z][l]=Integer.toString(combinacionMaximizadora[0][l-2]);
                System.out.print(combinacionMaximizadora[0][l-2]+"  ");
            }
            System.out.println("");
        }
        return salida;
    }
    public Iteracion iterar(){
        Iteracion i=new Iteracion();
        maximizarZ(i);
        return i;
    }
    public void maximizarZ(Iteracion i){
        int cantidadNoVariables=rp.getFo().getNoVariables();
        int cantidadNumeros=noAleatoriosIteracion;
        int[ ][ ] numerosAleatoriosFiltrados =this.generarFiltrarNoAleatorios(cantidadNumeros, cantidadNoVariables);
        int [][] combinacionMaximizadora=new int[1][cantidadNoVariables];
        int maximoZ=0;
        for(int k=0;k<numerosAleatoriosFiltrados.length;k++){
            int auxEvaluacion=evaluarFuncionObjetivo(rp.getFo(),numerosAleatoriosFiltrados[k]);
            if(auxEvaluacion>maximoZ){
                maximoZ=auxEvaluacion;
                combinacionMaximizadora[0]=numerosAleatoriosFiltrados[k];
            }
        }
        i.setValorVariables(combinacionMaximizadora);
        i.setZ(maximoZ);
        i.setFo(rp.getFo());
    }
    public int evaluarFuncionObjetivo(FuncionObjetivo fo,int []combinacionPosible){
        int z=0;
        GestionadorFuncionObjetivo gfo=new GestionadorFuncionObjetivo();
        int [] coeficientesFO = gfo.coeficientesTOArray(fo);
        System.out.println("...Evaluar z ///////////////////////");
        for(int i=0;i<coeficientesFO.length;i++){
            System.out.println("...numero ale "+i+" es: " +combinacionPosible[i]+"y coeficiente " +i+"es : "+coeficientesFO[i]);
            System.out.println(combinacionPosible[i]+"*"+coeficientesFO[i]);
            z=z+(combinacionPosible[i]*coeficientesFO[i]);
        }
        System.out.println("...////////////z es:" +z);
        return z;
    }
    public int [][] generarNumerosAleatorios(int cantidadNumeros,int cantidadNoVariables,int intervalo){//regresar arreglo
        System.out.println("...el intervalo recibido es "+intervalo);
        int[ ][ ] numerosAleatorios = new  int[cantidadNumeros][cantidadNoVariables];
        int i,j=0;
        System.out.println("...///Generar no aleatorios//");
        for(i=0;i<cantidadNumeros;i++){//se llena altura
            for(j=0;j<cantidadNoVariables;j++){//se llena ancho
                numerosAleatorios[i][j]=ThreadLocalRandom.current().nextInt(0, intervalo);
                System.out.print(numerosAleatorios[i][j]+"  ");
            }
            System.out.println(".../////");
        }
        System.out.println(".../////////////////////////////////////////");
        return numerosAleatorios;
    }
    public boolean evaluarRestriccion(Restriccion restriccion,int [] numerosAleatorios){
        GestionadorRestriccion gr= new GestionadorRestriccion();
        int noVariables=restriccion.getNoVariables();
        int [] coeficientesRestriccion = gr.coeficientesTOArray(restriccion);
        if(!(coeficientesRestriccion.length==numerosAleatorios.length)){
            System.out.println("...Algo anda mal en el evaluo de restriccion");
            return false;
        }
        int operacionIzquierda=0;// primero se tiene un 0 a ese cero se le suma el primer producto de i's
        for(int i=0;i<noVariables;i++){
            //System.out.println("...coeficiente :"+i+"es: "+coeficientesRestriccion[i]);
            //System.out.println("...noAle :"+i+"es: "+numerosAleatorios[i]);
            int producto=coeficientesRestriccion[i]*numerosAleatorios[i];
            operacionIzquierda=sumadorTerminos(operacionIzquierda,producto);//multiplicacion de terminos pos i 
            //System.out.println("...operacion Izquiera: "+operacionIzquierda);
        }
        //aqui ya se tiene la operacion izquierda (x) osea x><=u
        switch(restriccion.getDesigualdad()){
            case ">=":
                System.out.println(operacionIzquierda+">="+restriccion.getCr());
                if(operacionIzquierda>=restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            case "<=":
                System.out.println(operacionIzquierda+"<="+restriccion.getCr());
                if(operacionIzquierda<=restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            case "=":
                System.out.println(operacionIzquierda+"="+restriccion.getCr());
                if(operacionIzquierda==restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            case ">":
                System.out.println(operacionIzquierda+">"+restriccion.getCr());
                if(operacionIzquierda>restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            case "<":
                System.out.println(operacionIzquierda+"<"+restriccion.getCr());
                if(operacionIzquierda<restriccion.getCr()){
                    return true;
                }else{
                    return false;
                }
            default:
                return false;
        }
    }
    public int sumadorTerminos(int t1,int t2){
        return t1+t2;
    }
    public int obetenerCoeficienteMaxRestricciones(Restriccion[] restricciones){
        int intervalo=0;
        GestionadorRestriccion gr =new GestionadorRestriccion();
        for (Restriccion r : restricciones) {
            int[] coeficientesRestriccionaux = gr.coeficientesTOArray(r);
            int aux=(int)ManejoNumeros.calculaMaximo(coeficientesRestriccionaux);
            if(aux>intervalo){
                intervalo=aux;
            }
            if(r.getCr()>intervalo){
                intervalo=r.getCr();
            }
        }
        gr=null;
        mn=null;
        return intervalo;
    }
    public int[][] generarFiltrarNoAleatorios(int cantidadNumeros,int cantidadNoVariables){
        int[ ][ ] numerosAleatoriosFiltrados = new  int[cantidadNumeros][cantidadNoVariables];
        Restriccion[] restricciones=rp.getArregloRestricciones(rp);
        int intervalo=0;
        //intervalo=obtenerIntervaloParcial(intervalo);
        intervalo=obetenerCoeficienteMaxRestricciones(restricciones);
        //necesitamos almacenar en un array los conjuntos de numeros que cumplen las condiciones
        int[ ][ ] numerosAleatoriosAux =generarNumerosAleatorios(cantidadNumeros,cantidadNoVariables,intervalo);
        for(int k=0;k<cantidadNumeros;k++){//recorremos los numeros generados
            int auxValidadorRestriccion=0;
            for(int z=0;z<rp.getNoRestricciones();z++){//recorriendo restricciones, restriccion por restriccion
                System.out.println("restriccion "+z);
                //2 de esos numeros escoger cuales cumplen 
                if(evaluarRestriccion(restricciones[z],numerosAleatoriosAux[k])){//al algoritmo que evalua los numeros se le envia toda la columna
                    System.out.println("...cumplio");
                    auxValidadorRestriccion++;
                }else{
                    System.out.println("...no complio");
                }
            }
            if(auxValidadorRestriccion==rp.getNoRestricciones()){
                    System.out.println("...cumplio todas las restricciones");
                    numerosAleatoriosFiltrados[k]=numerosAleatoriosAux[k];
                    auxValidadorRestriccion=0;
            }else{
                System.out.println("...no cumplio todas las restricciones");
                auxValidadorRestriccion=0;
            }

        }
        return numerosAleatoriosFiltrados;
    }

    public String[][] getSalida() {
        return salida;
    }

    public void setSalida(String[][] salida) {
        this.salida = salida;
    }
    
}

