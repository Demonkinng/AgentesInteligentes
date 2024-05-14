package contenedor;

import agentes.*;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Contenedor {
    public void crearContenedor(String host, int port, String name) {
        jade.core.Runtime runtime = jade.core.Runtime.instance(); // crea un nuevo proceso
        Profile profile = new ProfileImpl(host, port, name);
        // Para pasar como objeto el contenedor al metodo que crea los agentes
        AgentContainer contenedorPrincipal = runtime.createMainContainer(profile);
        crearAgentes(contenedorPrincipal);
    }

    // Metodo para crear los agentes, recibe como parametro el contenedor donde se alojaran
    public void crearAgentes(AgentContainer contenedorPrincipal){
        try {
            // No hacer el siguiente codigo, puesto que genera sobrecarga de metodo
            // y para que funcione el setup se deberia cambiar el protected del setup lo cual no es valido
            //Agente1 ag1 = new Agente1();
            //ag1.setup();
            // Inicio los hilos
            contenedorPrincipal.createNewAgent("Ag3", Agente3.class.getName(), new Object[]{contenedorPrincipal}).start(); // tiene conocimeinto previo (3er atributo)
            contenedorPrincipal.createNewAgent("Ag4", Agente4.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("Ag2", Agente2.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("Ag5", Agente5.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("Ag1", Agente1.class.getName(), null).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
