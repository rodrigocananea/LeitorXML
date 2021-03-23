/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ravc.leitorxml.utils;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc;
import com.ravc.leitorxml.app.App;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Rodrigo
 */
public class Tools {

    public static Image getImage(String name) {
        Image image = null;
        try {
            image = ImageIO.read(App.class.getResourceAsStream("/icons/" + name));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return image;
    }

    public static String asDateZonedDateTime(String date) {
        ZonedDateTime DhEmi = ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime localDateDhEmi = DhEmi.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDateDhEmi.format(formatter);
    }

    public static Date asDate(String date) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        return asDate(zonedDateTime.toLocalDateTime());
    }
    
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String formatCNPJ(String valor) {
        if (valor.length() == 14) {
            if (valor.length() == 3) {
                valor = valor.substring(0, 2) + "." + valor.substring(2, 3);
            } else if (valor.length() >= 4 && valor.length() <= 5) {
                valor = valor.substring(0, 2) + "." + valor.substring(2, valor.length());
            } else if (valor.length() >= 6 && valor.length() <= 8) {
                valor = valor.substring(0, 2) + "." + valor.substring(2, 5) + "." + valor.substring(5, valor.length());
            } else if (valor.length() >= 9 && valor.length() <= 12) {
                valor = valor.substring(0, 2) + "." + valor.substring(2, 5) + "." + valor.substring(5, 8) + "/" + valor.substring(8, valor.length());
            } else if (valor.length() >= 12 && valor.length() <= 14) {
                valor = valor.substring(0, 2) + "." + valor.substring(2, 5) + "." + valor.substring(5, 8) + "/" + valor.substring(8, 12) + "-" + valor.substring(12, valor.length());
            }
        }
        return valor;
    }

    public static TNfeProc readXML(String fileXML) {
        String line = "";
        StringBuilder xml = new StringBuilder();
        TNfeProc nfe = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileXML)))) {
            while ((line = in.readLine()) != null) {
                xml.append(line.trim());
            }
            nfe = xmlToObject(xml.toString(), TNfeProc.class);
        } catch (Exception ex) {
            System.out.println("Erro: \n" + ex.getMessage());
        }
        return nfe;
    }

    public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {
        return JAXB.unmarshal(new StreamSource(new StringReader(xml)), classe);
    }

    public static String currency(double value) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(value);
    }

    public static double currency(String value) throws ParseException {
        if (value == null
                || value.trim().equals("")) {
            return 0.00;
        } else {
            value = value.replace("\t", "").trim();
            value = value.replace(" ", "").trim();
            value = value.replace("R$", "").trim();
            value = value.replace("%", "").trim();
            value = value.replace("%", "").trim();

            NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
            BigDecimal bd = new BigDecimal(nf.parse(value).doubleValue())
                    .setScale(2, RoundingMode.HALF_UP);

            return bd.doubleValue();
        }
    }

}
