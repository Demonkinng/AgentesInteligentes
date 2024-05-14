package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Comunicacion {
    // en el receptor solo me interesa su alias por eso no se usa tipo Agent como en el receptor
    public static void msj(int tipoMSJ, Agent emisor, String receptor, String contenidoStr, Serializable contenidoObj, String conversationId){
        ACLMessage acl = new ACLMessage(tipoMSJ);
        AID receptorID = new AID();
        receptorID.setLocalName(receptor);
        acl.addReceiver(receptorID);
        acl.setSender(emisor.getAID());
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);

        //Nota: no se puede enviar dos mensajes de diferentes tipos al mismo tipo
        if(contenidoStr == null){
            try {
                acl.setContentObject(contenidoObj);
            } catch (IOException ex) {
                Logger.getLogger(Comunicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            acl.setContent(contenidoStr);
        }
        acl.setConversationId(conversationId);

        emisor.send(acl);
    }
}
