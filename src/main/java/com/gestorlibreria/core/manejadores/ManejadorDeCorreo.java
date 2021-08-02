package com.gestorlibreria.core.manejadores;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class ManejadorDeCorreo {
    private static ManejadorDeCorreo instancia;
    public static ManejadorDeCorreo getInstancia(){
        if(instancia == null){
            instancia = new ManejadorDeCorreo();
        }
        return instancia;
    }

    public synchronized void enviarCorreoRegistro(String asuntoMensaje, String tituloMensaje, String cuerpoMensaje, String pieMensaje, String correoDestino) throws Exception{
        //try{
        // Propiedades de la conexión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.office365.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", "Fundación Kinal");
        props.setProperty("mail.smtp.auth", "true");
        // Preparamos la sesion
        Session session = Session.getDefaultInstance(props);
        // Construimos el mensaje
        MimeMessage message = new MimeMessage(session);
        //Datos del remitente
        message.setFrom(new InternetAddress("edwintumax@kinal.edu.gt"));
        Address direcciones[] = new Address[]{new InternetAddress(correoDestino),new InternetAddress("fernandotumax11@gmail.com")};
        message.addRecipient(
                Message.RecipientType.TO,new InternetAddress(correoDestino));
        message.addHeader("Disposition-Notification-To",correoDestino);
        message.setSubject(asuntoMensaje);
        message.setReplyTo(direcciones);
        message.setText(
                "<div>"+
                        "<div style='border:solid 1px #dfdfdf;color:#686868;font:13px Arial'>"+
                        "<div style='background-color:#fff;padding:20px'>"+
                        "<table cellpadding='0' cellspacing='0'>"+
                        "<tbody>"+
                        "<tr>"+
                        "<td style='padding-right:15px;vertical-align:top'>"+
                        "<img src='icono.png' height='75' width='75'>"+
                        "</td>"+
                        "<td style='width:578px;color:#333;font:13px Arial;vertical-align:top;color:#686868;font:16px Arial'>"+
                        "TuUtilesEscolares.com"+
                        "<div style='width:578px;color:#333;font:13px Arial;vertical-align:top;padding-top:10px'>"+
                        "<div style='background-color: rgb(255, 255, 255); border: 0px none; padding: 0px;'>"+
                        "<div style='margin-bottom: 15px;' dir='ltr'>"+
                        tituloMensaje +
                        "</div></div>"+
                        "<div style='background-color: rgb(255, 255, 255); border: 0px none; padding: 0px;'>"+
                        "<div style='margin-bottom: 15px;' dir='ltr'>"+
                        cuerpoMensaje +
                        "</div></div>"+
                        pieMensaje+
                        "</div>"+
                        "</td>"+
                        "</tr>"+
                        "</tbody>"+
                        "</table>"+
                        "</div>"+
                        "<div style='border-top:solid 1px #dfdfdf;margin:0; padding:0; background-color:#f5f5f5; color:#636363;font:11px Arial; text-align:center;' >" +
                        "<span>4a. Avenida 5-20, zona 7, Colonia Landívar, CP 01007. Ciudad de Guatemala, Guatemala.</span><br>" +
                        "<span>PBX: (502) 3853-3824</span><br>" +
                        "<span>Copyright © 2011 <a href='#' style='color:#3366CC;text-decoration:none' target='_blank'>Fundación Kinal</a>.</span>"+
                        "</div>" +
                        "</div>" +
                        "</div>","UTF-8", "html");
        MimeBodyPart messageBodyPart1 = new MimeBodyPart();
        String filename = "C:\\Reporte\\FacturacionGestorLibreria.pdf";//change accordingly
        DataSource source = new FileDataSource(filename);
        messageBodyPart1.setDataHandler(new DataHandler(source));
        messageBodyPart1.setFileName(filename);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart1);
        message.setContent(multipart);
        Transport t = session.getTransport("smtp");
        t.connect("edwintumax@kinal.edu.gt", "Inicio.2020");
        t.sendMessage(message, message.getAllRecipients());
        t.close();
    }

}
