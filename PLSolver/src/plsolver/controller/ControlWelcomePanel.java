package plsolver.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import plsolver.system.FuncionObjetivo;
import plsolver.system.GestionadorFuncionObjetivo;
import plsolver.system.GestionadorRestriccion;
import plsolver.system.ResolvedorProblema;
import plsolver.system.ResolverProblema;
import plsolver.system.Restriccion;
import plsolver.view.WelcomePanel;

public class ControlWelcomePanel implements ActionListener,MouseListener{
    private WelcomePanel panel;
    private GestionadorFuncionObjetivo gfo;
    private FuncionObjetivo fo;
    private ResolverProblema rp=new ResolverProblema(); // se intuye que al agregar todas las restricciones 
    //y funciones objetivo se crea ResolverProblema
    private GestionadorRestriccion gr=new GestionadorRestriccion();
    private ResolvedorProblema rop;
    
    public ControlWelcomePanel(WelcomePanel panel){
        this.panel = panel;
        showWelcomePanel();
    }
    public void  showWelcomePanel(){
        CardLayout welcomeLayout = (CardLayout) panel.welcomePanelContainer.getLayout();
        welcomeLayout.show(panel.welcomePanelContainer, "welcomePanel");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "verifyObjectiveFunction":
                gfo=new GestionadorFuncionObjetivo();
                fo=gfo.crearFuncionObjetivo(panel.objectiveFunctionFiled.getText());
                if(fo==null){
                    JOptionPane.showMessageDialog(null,"No se pudo crear la función objetivo","Error",JOptionPane.ERROR_MESSAGE);         
                }else{
                    JOptionPane.showMessageDialog(null,"Funcion Objetivo Creada","OK",JOptionPane.INFORMATION_MESSAGE);
                    rp.setFo(fo);
                }
            break;
            case "resttriction1Button":
                Restriccion r1=gr.CrearRestriccion(panel.restriction1Field.getText());
                if(r1==null){
                    JOptionPane.showMessageDialog(null,"No se pudo crear la restriccion","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Restricción establecida","OK",JOptionPane.INFORMATION_MESSAGE);
                    rp.setR1(r1);
                    rp.setNoRestricciones(rp.getNoRestricciones()+1);
                }
            break;
            case "resttriction2Button":
                Restriccion r2=gr.CrearRestriccion(panel.restriction2Field.getText());
                if(r2==null){
                    JOptionPane.showMessageDialog(null,"No se pudo crear la restriccion","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Restricción establecida","OK",JOptionPane.INFORMATION_MESSAGE);
                    rp.setR2(r2);
                    rp.setNoRestricciones(rp.getNoRestricciones()+1);
                }
            break;
            case "resttriction3Button":
                Restriccion r3=gr.CrearRestriccion(panel.restriction3Field.getText());
                if(r3==null){
                    JOptionPane.showMessageDialog(null,"No se pudo crear la restriccion","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Restricción establecida","OK",JOptionPane.INFORMATION_MESSAGE);
                    rp.setR3(r3);
                    rp.setNoRestricciones(rp.getNoRestricciones()+1);
                }
            break;
            case "resttriction4Button":
                Restriccion r4=gr.CrearRestriccion(panel.restriction4Field.getText());
                if(r4==null){
                    JOptionPane.showMessageDialog(null,"No se pudo crear la restriccion","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Restricción establecida","OK",JOptionPane.INFORMATION_MESSAGE);
                    rp.setR4(r4);
                    rp.setNoRestricciones(rp.getNoRestricciones()+1);
                }
            break;
            case "resttriction5Button":
                Restriccion r5=gr.CrearRestriccion(panel.restriction5Field.getText());
                if(r5==null){
                    JOptionPane.showMessageDialog(null,"No se pudo crear la restriccion","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Restricción establecida","OK",JOptionPane.INFORMATION_MESSAGE);
                    rp.setR5(r5);
                    rp.setNoRestricciones(rp.getNoRestricciones()+1);
                }
            break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CardLayout welcomeLayout = (CardLayout) panel.welcomePanelContainer.getLayout();
        int noIteraciones;
        GestionadorFuncionObjetivo gfo=new GestionadorFuncionObjetivo();
        switch(e.getComponent().getName()){
            case "welcomeBannerIcon":
                welcomeLayout.show(panel.welcomePanelContainer, "fieldsRequerimentPanel");
            break;
            case "maxButton":
                noIteraciones = Integer.parseInt(JOptionPane.showInputDialog("Numero de iteraciones"));
                System.out.println("seran"+noIteraciones+ " iteraciones");
                welcomeLayout.show(panel.welcomePanelContainer, "resultsPanelContainer");
                rop=new ResolvedorProblema(rp,"maximizar",noIteraciones);//recordar que rp ya esta creado y lleno
                String[][] salida=rop.getSalida();
                DefaultTableModel modelo=new DefaultTableModel();
                panel.resultsTable.setModel(modelo);
                modelo.addColumn("Iteración");
                modelo.addColumn("Z");
                String[] variablesFOArray=gfo.variablesTOArray(rp.getFo());
                for(int i=0;i<rp.getFo().getNoVariables();i++){
                    modelo.addColumn((variablesFOArray[i]).toUpperCase());
                }
                for(int i=0;i<noIteraciones;i++){
                    modelo.addRow(salida[i]);
                }
            break;
            case "mixButton":
                noIteraciones = Integer.parseInt(JOptionPane.showInputDialog("Numero de iteraciones"));
                welcomeLayout.show(panel.welcomePanelContainer, "resultsPanelContainer");
                rop=new ResolvedorProblema(rp,"minimizar",noIteraciones);//recordar que rp ya esta creado y lleno
            break;
            default:
            break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
   
    }
    
}
