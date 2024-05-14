package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import modelo.Cliente;

public class Agente1 extends Agent {
    // Cuerpo del agente usando la libreria jade
    @Override
    protected void setup(){ // Configuracion del agente
        addBehaviour(new Comportamiento()); // le paso el comportamiento como una instancia
    }

//    @Override
//    public void takeDown(){ // Para que se muera el agente
//        super.takeDown();
//    }

    // Un comportamiento es una clase o subclase (em este caso)
    // Hereda de la libreria pero especificando el tipo (simple, cyclic, parallel)
    class Comportamiento extends CyclicBehaviour {
        @Override
        public void action(){ // Ejecucion del agente, se coloca lo que se desea que el agente haga
            System.out.println(getName());
            // si es fuera del comportamiento pongo this
            //Comunicacion.msj(ACLMessage.INFORM, getAgent(), "Ag2", "Hola soy " + getName(), null, "CD-01-02");
            Comunicacion.msj(ACLMessage.INFORM, getAgent(), "Ag2", null, new Cliente("Angel", "Chuncho", "EC001", 20, 593), "CD-01-02");
            blockingReceive(); // provisional, para frenar la comunicacion y ver el resultado
        }
    }
}
