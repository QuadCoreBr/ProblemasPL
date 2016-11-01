
package plsolver.system;

public class ResolvedorProblema {
    private ResolverProblema rp;
    private String accion;

    public ResolvedorProblema(ResolverProblema rp, String accion) {
        this.rp = rp;
        this.accion = accion;
        resolver();
    }
    public void resolver(){
        
    }
    public void obtenerIntervalo(){
        FuncionObjetivo fo=rp.getFo();
        int[ ]   coeficientesFO = new  int[fo.getNoVariables()];
        for(int i=0;i<=coeficientesFO.length;i++){
           //coeficientesFO[i]=fo.
        }
    }
    public void generarNumerosAleatorios(){//regresar arreglo
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

