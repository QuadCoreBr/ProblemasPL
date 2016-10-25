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
    private String s1;
    private String s2;
    private String s3;   

    FuncionObjetivo() {
        v1=null;
        v2=null;
        v3=null;
        v4=null;
        c1=0;
        c2=0;
        c3=0;
        c4=0;
        noVariables=0;
        s1=null;
        s2=null;
        s3=null;
    }
    FuncionObjetivo(int noVariables) {
        v1=null;
        v2=null;
        v3=null;
        v4=null;
        c1=0;
        c2=0;
        c3=0;
        c4=0;
        this.noVariables=noVariables;
        s1=null;
        s2=null;
        s3=null;
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

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public FuncionObjetivo(String v1, String v2, int c1, int c2, int noVariables, String s1) {
        this.v1 = v1;
        this.v2 = v2;
        this.c1 = c1;
        this.c2 = c2;
        this.noVariables = noVariables;
        this.s1 = s1;
    }

    public FuncionObjetivo(String v1, String v2, String v3, int c1, int c2, int c3, int noVariables, String s1, String s2) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.noVariables = noVariables;
        this.s1 = s1;
        this.s2 = s2;
    }

    public FuncionObjetivo(String v1, String v2, String v3, String v4, int c1, int c2, int c3, int c4, int noVariables, String s1, String s2, String s3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.noVariables = noVariables;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }
}
