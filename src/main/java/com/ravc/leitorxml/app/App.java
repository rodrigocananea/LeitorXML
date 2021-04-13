/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ravc.leitorxml.app;

import com.ravc.leitorxml.views.LeitorXML;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;

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
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
            if (UIManager.getLookAndFeel() instanceof MaterialLookAndFeel) {
                MaterialLookAndFeel.changeTheme(new MaterialOceanicTheme());
            }
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            System.out.println("Iniciando aplicação!");
            System.out.println("");
            new LeitorXML().setVisible(true);
        });
    }

}
