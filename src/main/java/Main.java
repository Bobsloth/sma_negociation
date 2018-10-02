
import Agent.BookSellerAgent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;


public class Main {
    public static void main(String[] args){
        try {
            Runtime runtime = Runtime.instance();
            Properties properties = new ExtendedProperties();
            properties.setProperty(Profile.GUI, "true");
            Profile prolfile = new ProfileImpl(properties);
            AgentContainer agentContainer = runtime.createMainContainer(prolfile);
            agentContainer.start();
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }
}
