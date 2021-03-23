/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ravc.leitorxml.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author gustavo.beschorner
 */
public class CellRenderer extends DefaultTableCellRenderer {

    public final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Color foreground = null, background = null;

        switch (column) {
            case 0:
                setHorizontalAlignment(JLabel.CENTER);
                break;
            case 1:
                setHorizontalAlignment(JLabel.CENTER);
                break;
            case 2:
                setHorizontalAlignment(JLabel.CENTER);
                break;
            case 4:
                setHorizontalAlignment(JLabel.CENTER);
                break;
            case 5:
                setHorizontalAlignment(JLabel.RIGHT);
                break;
            default:
                setHorizontalAlignment(JLabel.LEFT);
                break;
        }
        // tabela zebrada
        if (row % 2 == 0) {
            foreground = new Color(56, 56, 56);
            background = new Color(247, 247, 247);
        } else {
            foreground = new Color(56, 56, 56);
            background = new Color(237, 237, 237);
        }

        if (isSelected) {
            foreground = new Color(255, 255, 255);
            background = new Color(51, 153, 255);
        }
        
        if (table.isColumnSelected(column) && isSelected) {
            foreground = new Color(255, 255, 255);
            background = new Color(51, 153, 255);
        }

        if (value instanceof String && String.valueOf(value).length() == 14) {
            value = Tools.formatCNPJ(String.valueOf(value));
        }

        setForeground(foreground);
        setBackground(background);

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
