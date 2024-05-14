package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import modelo.Cliente;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Agente5 extends Agent {
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
                ACLMessage aclmsj = blockingReceive();
                Cliente cliente = (Cliente) aclmsj.getContentObject();
                cliente.setEdad(cliente.getEdad() + 1);
                Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag2", null, cliente, "CD-05-02");
            } catch (UnreadableException ex) {
                Logger.getLogger(Agente5.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
