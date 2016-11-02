package plsolver.system;

public class Iteracion {
    private int [][] valorVariables;
    private int z;
    private FuncionObjetivo fo;

    public int[][] getValorVariables() {
        return valorVariables;
    }

    public void setValorVariables(int[][] valorVariables) {
        this.valorVariables = valorVariables;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public FuncionObjetivo getFo() {
        return fo;
    }

    public void setFo(FuncionObjetivo fo) {
        this.fo = fo;
    }
    
}
