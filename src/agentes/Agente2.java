package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import modelo.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agente2 extends Agent {
    // Cuerpo del agente usando la libreria

    @Override
    protected void setup(){ // Configuracion del agente
        addBehaviour(new Comportamiento()); // le paso el comportamiento como una instancia
    }

    // Un comportamiento es una clase o subclase (em este caso)
    // Hereda de la libreria pero especificando el tipo (simple, cyclic, parallel)
    class Comportamiento extends CyclicBehaviour{

        @Override
        public void action(){ // Ejecucion del agente, se coloca lo que se desea que el agente haga
            try {
                ACLMessage aclmsj = blockingReceive(); // bloqueo los agentes que no inician la conversacion
                Cliente cliente = (Cliente)aclmsj.getContentObject();
                // Dos metodos para verificar de donde proviene un mensaje, puede ser por el alias del
                // emisor o el id de la conversacion, esto es siguiendo la arquitectura
                /*if(aclmsj.getSender().getName().equals("Ag1")){

                }*/

                if(aclmsj.getConversationId().equals("CD-01-02")){
                    cliente.setEdad(cliente.getEdad()+1);
                    Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag5", null, cliente, "CD-02-05");
                }else{
                    if(aclmsj.getConversationId().equals("CD-05-02")){
                        Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag4", null, cliente, "CD-02-04");
                    }else{
                        if(aclmsj.getConversationId().equals("CD-04-02")){
                            Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag4", null, cliente, "CD-02-04");
                        }
                    }
                }



                //Cliente cliente = (Cliente)aclmsj.getContentObject();
                //System.out.println(cliente.getNombre() + " " + cliente.getApellido());
            } catch (UnreadableException ex) {
                Logger.getLogger(Agente2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
