package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import modelo.Cliente;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AgenteH extends Agent {
    Cliente cliente;
    AgentContainer contenedorPrincipal;
    int alias;

    @Override
    protected void setup(){ // Configuracion del agente
        addBehaviour(new Comportamiento()); // le paso el comportamiento como una instancia
    }

    protected void takeDown(){ // Para que se muera el agente
        try {
            // Para que se muera el agente
            contenedorPrincipal.createNewAgent("AgenteHijo" + alias, AgenteH.class.getName(), new Object[]{cliente, contenedorPrincipal, alias}).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Agente3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Un comportamiento es una clase o subclase (em este caso)
    // Hereda de la libreria pero especificando el tipo (simple, cyclic, parallel)
    class Comportamiento extends Behaviour {

        @Override
        public void action(){ // Ejecucion del agente, se coloca lo que se desea que el agente haga
            cliente = (Cliente) getArguments()[0];
            cliente.setEdad(cliente.getEdad() + 1);
            contenedorPrincipal = (AgentContainer) getArguments()[1];
            alias = ((int) getArguments()[2] + 1);
            System.out.println(getName());
            System.out.println(cliente.getEdad());
            doDelete();
        }

        @Override
        public boolean done() { // necesario para el comportamiento simple
            return true;
        }
    }
}
