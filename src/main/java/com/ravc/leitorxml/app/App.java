/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ravc.leitorxml.app;

import com.ravc.leitorxml.views.LeitorXML;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (info.getName().equals("Windows")) {
                    //if ("Motif".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir sistema, motivo:\n" + ex.getMessage(), "Erro ao incializar", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            System.out.println("Iniciando aplicação!");
            System.out.println("");
            new LeitorXML().setVisible(true);
        });
    }
    
}
