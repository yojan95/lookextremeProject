/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.model;

import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.PrimeFaces;

/**
 *
 * @author hoore
 */
public class Smtp {
    private String to;
    private String from;
    private String subject;
    private String descr;
    private final String username = "alookextreme@gmail.com";
    private final String password = "S6ZZGNdKSkym8cu";
    private final String smtp = "smtp.gmail.com";
    private final int port = 587;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUsername() {
        return username;
    }   

    public String getPassword() {
        return password;
    }
    

    public String getSmtp() {
        return smtp;
    }   

    public int getPort() {
        return port;
    }    
    
    public void enviarCorreo() throws MessagingException{        

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", port);        
         
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        MimeMessage message = generateMessage(session);
        boolean success = false;
        
        if(message != null)
            success = sendEmail(session, message);
        
        if(success)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se envio su correo"));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se envio su correo"));                
    }
    
    private MimeMessage generateMessage(Session session) {
        MimeMessage message = new MimeMessage(session);
        try {
            message.setContent(getDescr(), "text/plain");
            //Asunto
            message.setSubject(getSubject());
            //Desde
            Address fromAddress = new InternetAddress(getFrom());
            message.setFrom(fromAddress);
            //Para
            String[] recipientList = to.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient : recipientList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddress);
            message.saveChanges();
        } catch (MessagingException e) {
            System.out.println("Error al generar el mensaje");
            return null;
        }
        return message;
    }
    
    private boolean sendEmail(Session session, MimeMessage message) throws MessagingException {
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect(this.smtp, this.port, this.username, this.password);
            if (!transport.isConnected()) {
                PrimeFaces.current().executeScript("PF('wdialog').show();");
            }
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            System.out.println("Error al enviar correo");
            return false;
        }
    }

    
}
