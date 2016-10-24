package plsolver.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import plsolver.view.SolverContanier;
import plsolver.view.WelcomePanel;

public class ControlSolverContainer implements ActionListener{
    private SolverContanier frame;
    public ControlSolverContainer(SolverContanier frame){
        this.frame = frame;
        CardLayout solverContainer = (CardLayout) frame.solverContainerPanel.getLayout();
        solverContainer.show(frame.solverContainerPanel, "welcomePanel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
