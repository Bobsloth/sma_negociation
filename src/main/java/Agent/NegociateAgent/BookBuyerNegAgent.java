/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
 *****************************************************************/

package Agent.NegociateAgent;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.HashMap;

public class BookBuyerNegAgent extends Agent {
	// The title of the book to buy
	private String targetBookTitle;
	// The list of known seller agents


	private BookList buyList ;

	private AID[] sellerAgents;
	private int maxPrice;
	private int priceNeg;

    public void addBookList(BookList _buyList) {
        buyList = _buyList;
    }
	// Put agent initializations here
	public void setup() {
        // Printout a welcome message
        System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");

        // Get the title of the book to buy as a start-up argument
        Object[] args = getArguments();
        buyList = (BookList)args[0];


        buyList.printBooks();
        if (args != null && args.length > 0) {
            //targetBookTitle = (String) args[0];
            /*if (args[1] != null) {
                maxPrice = Integer.parseInt((String) args[1]);
            }*/

            System.out.println("POUET");
            //System.out.println("Target ticket is "+targetBookTitle + " at cost: " + maxPrice );
            System.out.println("Target is " + buyList);

            for( String book: buyList.getBooks().keySet()){

            // Add a TickerBehaviour that schedules a request to seller agents every minute
            addBehaviour(new TickerBehaviour(this, 10000) {
                protected void onTick() {
                    targetBookTitle = book;
                    System.out.println("Trying to buy " + targetBookTitle);
                    // Update the list of seller agents
                    DFAgentDescription template = new DFAgentDescription();
                    ServiceDescription sd = new ServiceDescription();
                    sd.setType("book-selling");
                    template.addServices(sd);
                    try {
                        DFAgentDescription[] result = DFService.search(myAgent, template);
                        System.out.println("Found the following seller agents:");
                        sellerAgents = new AID[result.length];
                        System.out.println("Target ticket ");

                        for (int i = 0; i < result.length; ++i) {
                            sellerAgents[i] = result[i].getName();
                            System.out.println(sellerAgents[i].getName());
                        }
                    } catch (FIPAException fe) {
                        fe.printStackTrace();
                    }

                    // Perform the request
                    myAgent.addBehaviour(new RequestPerformer());
                }
            });
        }
            //doDelete();
        }
        //else {
        // Make the agent terminate
        //	System.out.println("No target ticket title specified");

        //}
    }

	// Put agent clean-up operations here
	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("Buyer-agent "+getAID().getName()+" terminating.");
	}

	/**
	   Inner class RequestPerformer.
	   This is the behaviour used by Book-buyer agents to request seller 
	   agents the target book.
	 */
	private class RequestPerformer extends Behaviour {
		private AID bestSeller; // The agent who provides the best offer 
		private int bestPrice;  // The best offered price
		private int repliesCnt = 0; // The counter of replies from seller agents
		private MessageTemplate mt; // The template to receive replies
		private int step = 0;

		public void action() {
            HashMap<String, Integer> bestComb = new HashMap<>();
		    while(buyList.missingBook(bestComb) != null) {
                switch (step) {
                    case 0:
                        // Send the cfp to all sellers
                        maxPrice = 20;
                        priceNeg = (int) (maxPrice * 0.60);
                        ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                        for (int i = 0; i < sellerAgents.length; ++i) {
                            cfp.addReceiver(sellerAgents[i]);
                        }
                        cfp.setContent(targetBookTitle);
                        cfp.setConversationId("book-trade");
                        cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique value
                        myAgent.send(cfp);
                        // Prepare the template to get proposals
                        mt = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),
                                MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));

                        step = 1;
                        break;
                    case 1:
                        // Receive all proposals/refusals from seller agents
                        ACLMessage reply = myAgent.receive(mt);
                        if (reply != null) {
                            // Reply received
                            if (reply.getPerformative() == ACLMessage.INFORM) {
                                // This is an offer
                                int price = Integer.parseInt(reply.getContent());
                                if (bestSeller == null || price < bestPrice) {
                                    // This is the best offer at present
                                    bestPrice = price;
                                    bestSeller = reply.getSender();
                                }
                            }
                            repliesCnt++;
                            if (repliesCnt >= sellerAgents.length) {
                                // We received all replies
                                System.out.println("Priceneg: " + priceNeg);
                                step = 2;

                            }
                        } else {
                            block();
                        }
                        break;

                    case 2:
                        // Negociate for a better offer
                        ACLMessage negociate = new ACLMessage(ACLMessage.PROPOSE);
                        if (priceNeg < maxPrice || priceNeg < bestPrice) {
                            priceNeg = priceNeg + 1;

                            negociate.setContent(targetBookTitle + "," + priceNeg);
                            System.out.println("Bonjour, je voudrais vous acheter " + targetBookTitle + " à " + priceNeg);
                        } else {
                            negociate.setContent(targetBookTitle + "," + bestPrice);
                            System.out.println("Bonjour, je voudrais vous acheter " + targetBookTitle + " à " + bestPrice);
                        }
                        negociate.addReceiver(bestSeller);
                        negociate.setConversationId("book-trade");
                        negociate.setReplyWith("negociate" + System.currentTimeMillis());
                        myAgent.send(negociate);
                        step = 3;
                        // Prepare the template to get the purchase order reply
                        mt = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),
                                MessageTemplate.MatchInReplyTo(negociate.getReplyWith()));

                        break;
                    case 3:
                        // Receive the purchase order reply
                        reply = myAgent.receive(mt);
                        if (reply != null) {
                            // Purchase order reply received
                            if (reply.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
                                // Purchase successful. We can terminate
                                System.out.println(targetBookTitle + " successfully purchased from agent " + reply.getSender().getName());
                                System.out.println("Price = " + priceNeg);
                                buyList.addBook(targetBookTitle,priceNeg);
                                step = 4;
                            } else if (reply.getPerformative() == ACLMessage.REJECT_PROPOSAL) {
                                step = 2;
                                System.out.println("Je ne peux accepter votre offre");
                                if (Integer.parseInt(reply.getContent()) > maxPrice) {
                                    System.out.println("Nous ne ferons donc pas affaire");
                                    step = 4;
                                }

                            } else {
                                System.out.println("Attempt failed: ticket already sold.");
                                step = 4;
                            }


                        } else {
                            block();
                        }
                        break;
                }
            }
            myAgent.doDelete();
		}

		public boolean done() {
			if (step == 2 && bestSeller == null) {
				System.out.println("Attempt failed: "+targetBookTitle+" not available for sale");
			}
			return ((step == 2 && bestSeller == null) || step == 4);
		}
	}  // End of inner class RequestPerformer
}
