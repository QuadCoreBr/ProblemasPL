package plsolver;

import plsolver.view.SolverContanier;

public class PLSolver {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SolverContanier().setVisible(true);
            }
        });
    }
}
