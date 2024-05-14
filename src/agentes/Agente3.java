package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import modelo.Cliente;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Agente3 extends Agent {
    // Cuerpo del agente usando la libreria
    Cliente cliente;

    @Override
    protected void setup(){ // Configuracion del agente
        addBehaviour(new Comportamiento()); // le paso el comportamiento como una instancia
    }

    @Override
    protected void takeDown(){
        try {
            // Para que se muera el agente
            AgentContainer contenedorPrincipal = (AgentContainer) getArguments()[0];
            contenedorPrincipal.createNewAgent("AgenteH", AgenteH.class.getName(), new Object[]{cliente, contenedorPrincipal, 1}).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Agente3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Un comportamiento es una clase o subclase (em este caso)
    // Hereda de la libreria pero especificando el tipo (simple, cyclic, parallel)
    class Comportamiento extends Behaviour {

        @Override
        public void action(){ // Ejecucion del agente, se coloca lo que se desea que el agente haga
            try {
                ACLMessage aclmsj = blockingReceive();
                cliente = (Cliente) aclmsj.getContentObject();
                cliente.setEdad(cliente.getEdad() * 2);
                doDelete();
            } catch (UnreadableException ex) {
                Logger.getLogger(Agente5.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public boolean done() { // Necesario para el comportamiento simple
            return true;
        }
    }
}
