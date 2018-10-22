package Agent.NegociateAgent;

import java.util.*;

public class BookList {

    private Integer price;
    private ArrayList<Integer> books;
    private Integer total;

public BookList(Integer _price, ArrayList<Integer> _books){
        price = _price;
        books = _books;
        total = 0;
    }

    public ArrayList<Integer> isIn(BookList listBuyer){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=0; i < listBuyer.size(); i++){
            Boolean in = books.contains(listBuyer.get(i));
            System.out.println("Is " + listBuyer.get(i) + " in " +  books + " ? " + in);
            if(in){
                res.add(listBuyer.get(i));
            }
        }
        return res;
    }


    private ArrayList<Integer> missingBook(ArrayList<Integer> listBook){
        ArrayList<Integer> missingList = new ArrayList<Integer>();
        for (Integer book : books) {
            if (!listBook.contains(book)) {
                missingList.add(book);
            }
        }
        if(missingList.isEmpty())
            return null;
        else
            return missingList;
    }

    public ArrayList<Integer> bestCombi(ArrayList<BookList> listSeller){
        ArrayList<Integer> bestComb = new ArrayList<Integer>();
        while(missingBook(bestComb) != null){
            for(int i = 0; i < size(); i++){
                int priceBookMin=1000;
                for(int j = 0; j < listSeller.size(); j++){
                    if(listSeller.get(j).getBooks().contains(get(i))){
                        if(priceBookMin > listSeller.get(j).price){
                            priceBookMin = listSeller.get(j).price;
                        }
                    }
                }
                bestComb.add(get(i));
                total += priceBookMin;
            }
            return bestComb;
        }
        for (BookList aListSeller : listSeller) {
            isIn(aListSeller);
        }

        return bestComb;
    }

    private Integer size(){
        return books.size();
    }

    private Integer get(Integer i){
        return books.get(i);
    }

    public Integer getTotal(){
    return total;
    }

    private ArrayList<Integer> getBooks(){
        return books;
    }
}
