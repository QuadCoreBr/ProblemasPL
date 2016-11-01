
package plsolver.system;

public class ManejoNumeros {
    public int maximo(int t[], int pos){
        int res;
        if(pos == t.length - 1){
            res = t[pos];
        }
        else{
            int k = maximo(t, pos + 1);
            if (t[pos] > k){
                res = t[pos];
            }
            else{
                res = k;
            }
        }
        return res;
    }
    public int maximo(int t[]){
        return maximo(t, 0);
    }
}
