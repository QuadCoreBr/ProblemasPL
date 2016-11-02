package plsolver.system;

public class ResolverProblema {
    private FuncionObjetivo fo;
    private Restriccion r1;
    private Restriccion r2;
    private Restriccion r3;
    private Restriccion r4;
    private Restriccion r5;
    private int noRestricciones;

    public int getNoRestricciones() {
        return noRestricciones;
    }

    public void setNoRestricciones(int noRestricciones) {
        this.noRestricciones = noRestricciones;
    }
    public ResolverProblema() {
        this.fo = null;
        this.r1 = null;
        this.r2 = null;
        this.r3 = null;
        this.r4 = null;
        this.r5 = null;
        this.noRestricciones = 0;
    }

    public ResolverProblema(FuncionObjetivo fo, Restriccion r1, Restriccion r2, Restriccion r3, Restriccion r4, Restriccion r5) {
        this.fo = fo;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
    }

    public ResolverProblema(FuncionObjetivo fo, Restriccion r1, Restriccion r2, Restriccion r3, Restriccion r4) {
        this.fo = fo;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
    }

    public ResolverProblema(FuncionObjetivo fo, Restriccion r1, Restriccion r2, Restriccion r3) {
        this.fo = fo;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }

    public ResolverProblema(FuncionObjetivo fo, Restriccion r1, Restriccion r2) {
        this.fo = fo;
        this.r1 = r1;
        this.r2 = r2;
    }

    public FuncionObjetivo getFo() {
        return fo;
    }

    public void setFo(FuncionObjetivo fo) {
        this.fo = fo;
    }

    public Restriccion getR1() {
        return r1;
    }

    public void setR1(Restriccion r1) {
        this.r1 = r1;
    }

    public Restriccion getR2() {
        return r2;
    }

    public void setR2(Restriccion r2) {
        this.r2 = r2;
    }

    public Restriccion getR3() {
        return r3;
    }

    public void setR3(Restriccion r3) {
        this.r3 = r3;
    }

    public Restriccion getR4() {
        return r4;
    }

    public void setR4(Restriccion r4) {
        this.r4 = r4;
    }

    public Restriccion getR5() {
        return r5;
    }

    public void setR5(Restriccion r5) {
        this.r5 = r5;
    }
    public Restriccion [] getArregloRestricciones(ResolverProblema rp){
        Restriccion[ ]   restricciones = new  Restriccion[rp.getNoRestricciones()];
        switch(rp.getNoRestricciones()){
            case 1:
                restricciones[0]=rp.getR1();
            case 2:
                restricciones[0]=rp.getR1();
                restricciones[1]=rp.getR2();
            break;
            case 3:
                restricciones[0]=rp.getR1();
                restricciones[1]=rp.getR2();
                restricciones[2]=rp.getR3();
            break;
            case 4:
                restricciones[0]=rp.getR1();
                restricciones[1]=rp.getR2();
                restricciones[2]=rp.getR3();
                restricciones[3]=rp.getR4();
            break;
            case 5:
                restricciones[0]=rp.getR1();
                restricciones[1]=rp.getR2();
                restricciones[2]=rp.getR3();
                restricciones[3]=rp.getR4();
                restricciones[4]=rp.getR5();
            break;
        }
        return restricciones;
    }
}
