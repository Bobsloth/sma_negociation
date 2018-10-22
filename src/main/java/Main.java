
import Agent.BookSellerAgent;
import Agent.NegociateAgent.BookList;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

import java.util.*;


public class Main {
    public static void main(String[] args){


        Map<List<Integer>, Integer > map = new HashMap< List<Integer>, Integer>();

//put values in the map like this

        BookList b1 = new BookList(0, new ArrayList<Integer>(Arrays.asList(1,2,4)));
        BookList b2 = new BookList(0, new ArrayList<Integer>(Arrays.asList(2,4)));

        BookList s1 = new BookList(15,new ArrayList<Integer>(Arrays.asList(1,3)));
        BookList s2 = new BookList(25,new ArrayList<Integer>(Arrays.asList(2,3,4)));
        BookList s3 = new BookList(20,new ArrayList<Integer>(Arrays.asList(1,2)));
        BookList s4 = new BookList(20,new ArrayList<Integer>(Arrays.asList(1,3,4)));
        BookList s5 = new BookList(5,new ArrayList<Integer>(Arrays.asList(2,3)));

        ArrayList<BookList> listSeller = new ArrayList<BookList>();
        listSeller.add(s1);
        listSeller.add(s2);
        listSeller.add(s3);
        listSeller.add(s4);
        listSeller.add(s5);

        ArrayList<Integer> res = b1.bestCombi(listSeller);
        System.out.println(res);
        System.out.println(b1.getTotal());

        ArrayList<Integer> res2 = b2.bestCombi(listSeller);
        System.out.println(res2);
        System.out.println(b2.getTotal());

        /* Get values based on key*/
        //Boolean var= map.containsKey(Arrays.asList(1,3));
        //System.out.println("Value at index 2 is: "+var);

        /*
        try {
            Runtime runtime = Runtime.instance();
            Properties properties = new ExtendedProperties();
            properties.setProperty(Profile.GUI, "true");
            Profile prolfile = new ProfileImpl(properties);
            AgentContainer agentContainer = runtime.createMainContainer(prolfile);
            agentContainer.start();
        } catch (ControllerException e) {
            e.printStackTrace();
        }*/
    }
}

