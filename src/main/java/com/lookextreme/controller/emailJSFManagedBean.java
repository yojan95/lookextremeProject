/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import java.util.HashSet;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.Properties;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 correo: alookextreme@gmail.com
 contraseña: S6ZZGNdKSkym8cu
 */
@Named(value = "emailJSFManagedBean")
@RequestScoped
public class emailJSFManagedBean {
    
    private String to;
    private String from;
    private String subject;
    private String descr;
    private String username;
    private String password; 
    private String smtp;
    private int port;
    
    
    public emailJSFManagedBean() {
        
        this.to = "alookextreme@gmail.com";
        this.from = null;
        this.subject = "contacto lookextreme";
        this.descr = null;
        this.username = "alookextreme@gmail.com";
        this.password = "S6ZZGNdKSkym8cu";
        this.smtp = "smtp.gmail.com";
        this.port = 587;
        this.descr = null;
    }

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    public void validateEmail(FacesContext context, UIComponent toValidate,Object value){
        String message = "";
        String email = (String) value;
        if ((email == null) || (email.equals(""))) {
            ((UIInput) toValidate).setValid(false);
            message = "Email Requerido";
            context.addMessage(toValidate.getClientId(context), new FacesMessage(message));
        }else if((!email.contains("@")) || (!email.contains("."))){
            ((UIInput) toValidate).setValid(false);
            message = "Email Invalido";
            context.addMessage(toValidate.getClientId(context), new FacesMessage(message));
        }
    }
    public void submitEmail(){
        Properties props = null;
        Session session = null;
        MimeMessage message = null;
        Address fromAddress= null;
        Address toAddress = null;
        
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", port);
        //props.put("mail.smtp.ssl.trust", "*");
        
        session = Session.getInstance(props,
                new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username, password);
                    }
                });
        message = new MimeMessage(session);
        try{
            message.setContent(getDescr(),"text/plain");
            message.setSubject(getSubject());            
            message.setFrom(new InternetAddress(getFrom()));
            System.out.println(getFrom());
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(getTo()));
            message.saveChanges();
            message.setReplyTo(new Address[]{ new InternetAddress(getFrom())});
            
            
            Transport transport = session.getTransport("smtp");
            transport.connect(this.smtp,this.port,this.username,this.password);
            if (!transport.isConnected()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se envio su correo"));
                //return "emailFal";
            }
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch(MessagingException me){
            System.out.println("error");
            me.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se envio su correo"));
            //return "emailFal";
            
            
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se envio su correo"));
        //return "emailOK";
    }
    
}