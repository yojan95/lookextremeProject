
package com.lookextreme.controller;

import com.lookextreme.model.Smtp;
import java.io.Serializable;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named
@ViewScoped
public class EmailController implements Serializable {
    
    private Smtp smtp;

    public Smtp getSmtp() {
        return smtp;
    }

    public void setSmtp(Smtp smtp) {
        this.smtp = smtp;
    }        
    
    @PostConstruct
    public void init(){
        smtp = new Smtp();
        smtp.setTo("alookextreme@gmail.com");
        smtp.setUsername("alookextreme@gmail.com");
        smtp.setPassword("S6ZZGNdKSkym8cu");
        smtp.setSmtp("smtp.gmail.com");
        smtp.setSubject("Contacto Look Extreme");
        smtp.setPort(587);
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
        props.put("mail.smtp.host", smtp.getSmtp());
        props.put("mail.smtp.port", smtp.getPort());
        
        session = Session.getInstance(props,
                new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(smtp.getUsername(), smtp.getPassword());
                    }
                });
        
        message = new MimeMessage(session);
        
        try{
            message.setContent(smtp.getDescr(),"text/plain");
            message.setSubject(smtp.getSubject());
            fromAddress = new InternetAddress(smtp.getFrom());
            message.setFrom(fromAddress);
            toAddress = new InternetAddress(smtp.getTo());
            message.setRecipient(Message.RecipientType.TO, toAddress);
            message.saveChanges();
            
            Transport transport = session.getTransport("smtp");
            transport.connect(smtp.getSmtp(),smtp.getPort(),smtp.getUsername(),smtp.getPassword());
            if (!transport.isConnected()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se envio su correo"));                
            }
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch(MessagingException me){
            System.out.println("error al enviar correo: " + me.getMessage());            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se envio su correo"));                                    
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se envio su correo"));        
    }
    
}
