package plsolver.system;

public class FuncionObjetivo {
    private String v1;
    private String v2;
    private String v3;
    private String v4;
    private int c1;
    private int c2;
    private int c3;
    private int c4;
    private int noVariables;
    private String funcionOriginal;

    FuncionObjetivo(int noVariables,String funcionOriginal) {
        v1=null;
        v2=null;
        v3=null;
        v4=null;
        c1=0;
        c2=0;
        c3=0;
        c4=0;
        this.noVariables=noVariables;
        this.funcionOriginal=funcionOriginal;
    }

    public String getFuncionOriginal() {
        return funcionOriginal;
    }

    public void setFuncionOriginal(String funcionOriginal) {
        this.funcionOriginal = funcionOriginal;
    }
    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public int getC3() {
        return c3;
    }

    public void setC3(int c3) {
        this.c3 = c3;
    }

    public int getC4() {
        return c4;
    }

    public void setC4(int c4) {
        this.c4 = c4;
    }

    public int getNoVariables() {
        return noVariables;
    }

    public void setNoVariables(int noVariables) {
        this.noVariables = noVariables;
    }  
}
