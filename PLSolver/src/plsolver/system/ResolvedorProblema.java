
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
        //int noVariables=restriccion.getNoVariables();
        int [] coeficientesRestriccion = gr.coeficientesTOArray(restriccion);
        if(!(coeficientesRestriccion.length==numerosAleatorios.length)){
            System.out.println("Algo anda mal en el evaluo de restriccion");
            return false;
        }
        int operacionIzquierda=0;
        for(int i=0;i<coeficientesRestriccion.length;i++){
            //parte de los signos
        }
        switch(restriccion.getDesigualdad()){
            case ">=":
                
            break;
            case "<=":
            break;
            case "=":
            break;
            case ">":
            break;
            case "<":
            break;
        }
        return true;
    }
    public int evaluarFuncionObjetivo(){
        int z=0;
        return z;
    }
}

