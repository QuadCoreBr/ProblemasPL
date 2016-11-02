
package plsolver.system;

public class ManejoNumeros {
    public static float calculaMaximo(int valores[]) {
        float maximo = 0;
        int indice;
        
        for (indice = 0;indice < valores.length; indice++) {
            if(valores[indice]>maximo) {
                maximo = valores[indice];
            }
        }        
        return maximo;
    }
}
