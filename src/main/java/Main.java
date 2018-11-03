
import Agent.BookSellerAgent;
import Agent.NegociateAgent.BookBuyerNegAgent;
import Agent.NegociateAgent.BookList;
import Agent.NegociateAgent.BookSellerNegAgent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

import java.util.*;


public class Main {
    public static void main(String[] args){


        Map<List<Integer>, Integer > map = new HashMap< List<Integer>, Integer>();

//put values in the map like this

        /*BookList b1 = new BookList(0, new ArrayList<Integer>(Arrays.asList(1,2,4)));
        BookList b2 = new BookList(0, new ArrayList<Integer>(Arrays.asList(2,4)));

        BookList s1 = new BookList(15,new ArrayList<Integer>(Arrays.asList(1,3)));
        BookList s2 = new BookList(25,new ArrayList<Integer>(Arrays.asList(2,3,4)));
        BookList s3 = new BookList(20,new ArrayList<Integer>(Arrays.asList(1,2)));
        BookList s4 = new BookList(20,new ArrayList<Integer>(Arrays.asList(1,3,4)));
        BookList s5 = new BookList(5,new ArrayList<Integer>(Arrays.asList(2,3)));
        */

        HashMap<String, Integer> hmb1 = new HashMap<>();
        hmb1.put("LOTR", null);
        hmb1.put("Drizzt",null);
        hmb1.put("Ellana",null);
        BookList b1 = new BookList(0, hmb1);

        HashMap<String, Integer> hmb2 = new HashMap<>();
        hmb2.put("LOTR",null);
        hmb2.put("Ellana",null);
        BookList b2 = new BookList(0, hmb2);

        HashMap<String, Integer> hms1 = new HashMap<>();
        hms1.put("LOTR",15);
        hms1.put("Snoopy",22);
        BookList s1 = new BookList(15,hms1);

        HashMap<String, Integer> hms2 = new HashMap<>();
        hms2.put("Drizzt",14);
        hms2.put("Snoopy",20);
        hms2.put("Ellana",18);
        BookList s2 = new BookList(25,hms2);

        HashMap<String, Integer> hms3 = new HashMap<>();
        //hms3.put("LOTR",3);
        hms3.put("Drizzt",7);
        BookList s3 = new BookList(20,hms3);

        HashMap<String, Integer> hms4 = new HashMap<>();
        //hms4.put("LOTR",4);
        hms4.put("Snoopy",5);
        hms4.put("Ellana",3);
        BookList s4 = new BookList(20,hms4);

        HashMap<String, Integer> hms5 = new HashMap<>();
        hms5.put("Drizzt",6);
        hms5.put("Snoopy",5);
        BookList s5 = new BookList(5,hms5);


        /*ArrayList<BookList> listSeller = new ArrayList<BookList>();
        listSeller.add(s1);
        listSeller.add(s2);
        listSeller.add(s3);
        listSeller.add(s4);
        listSeller.add(s5);

        b1.bestCombi2(listSeller);
        b1.printBooks();
        System.out.println(b1.getTotal());

        b2.bestCombi2(listSeller);
        b2.printBooks();
        System.out.println(b2.getTotal());*/

        /* Get values based on key*/
        //Boolean var= map.containsKey(Arrays.asList(1,3));
        //System.out.println("Value at index 2 is: "+var);


        try {
            Runtime runtime = Runtime.instance();
            Properties properties = new ExtendedProperties();
            properties.setProperty(Profile.GUI, "true");
            Profile prolfile = new ProfileImpl(properties);
            AgentContainer agentContainer = runtime.createMainContainer(prolfile);
            agentContainer.start();
            AgentController agentB1 = null;
            AgentController agentB2 = null;
            AgentController agentS1 = null;
            AgentController agentS2 = null;
            AgentController agentS3 = null;
            AgentController agentS4 = null;
            AgentController agentS5 = null;

            agentB1 = agentContainer.createNewAgent("Bob",
                    "Agent.NegociateAgent.BookBuyerNegAgent", new BookList[]{b1});
            agentB2 = agentContainer.createNewAgent("LeMangeurDeTomate",
                    "Agent.NegociateAgent.BookBuyerNegAgent", new BookList[]{b2});

            agentS1 = agentContainer.createNewAgent("Ted",
                    "Agent.NegociateAgent.BookSellerNegAgent", new BookList[]{s1});
            agentS2 = agentContainer.createNewAgent("Fred",
                    "Agent.NegociateAgent.BookSellerNegAgent", new BookList[]{s2});
            agentS3 = agentContainer.createNewAgent("Karl",
                    "Agent.NegociateAgent.BookSellerNegAgent", new BookList[]{s3});
            agentS4 = agentContainer.createNewAgent("Hans",
                    "Agent.NegociateAgent.BookSellerNegAgent", new BookList[]{s4});
            agentS5 = agentContainer.createNewAgent("Gnah",
                    "Agent.NegociateAgent.BookSellerNegAgent", new BookList[]{s5});
            Thread.sleep(60000);
            agentS1.start();
            agentS2.start();
            agentS3.start();
            agentS4.start();
            agentS5.start();
            agentB1.start();
            agentB2.start();
            /*BookBuyerNegAgent buyer1 = new BookBuyerNegAgent();
            buyer1.addBookList(b1);
            buyer1.setup();

            BookSellerNegAgent seller1 = new BookSellerNegAgent();
            BookSellerNegAgent seller2 = new BookSellerNegAgent();
            BookSellerNegAgent seller3 = new BookSellerNegAgent();
            BookSellerNegAgent seller4 = new BookSellerNegAgent();
            BookSellerNegAgent seller5 = new BookSellerNegAgent();
            seller1.addBookList(s1);
            seller2.addBookList(s2);
            seller3.addBookList(s3);
            seller4.addBookList(s4);
            seller5.addBookList(s5);
            seller1.setup();
            seller2.setup();
            seller3.setup();
            seller4.setup();
            seller5.setup();
            */


        } catch (ControllerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

