
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
    public static int[][] calculaMaximoSalida(String [][] salida){
        //salida.l =filas
        //salida[].l = columnas
        int[][] salidaMaxima=new int[1][salida[0].length+2];
        int iteracion=0;
        int zmax=0;
        for(int z=0;z<salida.length;z++){
            if(Integer.valueOf(salida[z][1])>zmax){
                zmax=Integer.valueOf(salida[z][1]);
                iteracion=z;
                System.out.print("zmax ahora es...:"+zmax);
                System.out.println(" en la iteracion...:"+iteracion);
            }
        }
        salidaMaxima[0][0]=iteracion;
        salidaMaxima[0][1]=zmax;
        for(int i=2;i<salida[0].length;i++){
            salidaMaxima[0][i]=Integer.valueOf(salida[iteracion][i]);
        }
        return salidaMaxima;
    }
}
