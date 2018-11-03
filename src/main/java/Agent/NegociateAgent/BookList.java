package Agent.NegociateAgent;

import java.util.*;

public class BookList {

    private Integer priceLot;
    //private ArrayList<Integer> books;
    private HashMap<String, Integer> books;
    private Integer total;

public BookList(Integer _price, HashMap<String, Integer>  _books){
        priceLot = _price;
        books = _books;
        total = 0;
    }


    public ArrayList<String> missingBook(HashMap<String, Integer> listBook){
        ArrayList<String> missingList = new ArrayList<>();
        for (String book : books.keySet()) {
            if (listBook.get(book) == null) {
                missingList.add(book);
            }
        }
        if(missingList.isEmpty())
            return null;
        else
            return missingList;
    }

    public HashMap<String, Integer> bestCombi2 (ArrayList<BookList> listSeller){
        HashMap<String, Integer> bestComb = new HashMap<>();
        Integer oldPrice = 0;
        while(missingBook(bestComb) != null){
            //pour chaque seller
            for(int j = 0; j < listSeller.size(); j++){
                //pour chaque livre du buyer
                for(String i : this.books.keySet()){
                    //si le jieme seller a le livre i
                    if(listSeller.get(j).get(i) != null){
                        //si le prix du livre du seller est inferieur
                        if(this.get(i) == null || this.get(i) > listSeller.get(j).get(i)){
                            this.books.put(i, listSeller.get(j).get(i));
                        }
                    }
                }
            }
            return bestComb;
        }
        return bestComb;
    }

    public void addBook(String titre, Integer value){
        this.books.put(titre,value);
    }

    public Integer size(){
        return books.size();
    }

    public Integer get(String i){
        return books.get(i);
    }

    public HashMap<String, Integer> getBooks(){
        return books;
    }

    public void remove(String title, Integer value){
        this.books.remove(title,value);
    }

    public Integer getTotal(){
        for (String i: books.keySet()){
            if(this.get(i) !=null)
                total += this.get(i);
        }
        return total;
    }

    public void printBooks(){
        System.out.println(books);
    }
/*
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

    public HashMap<Integer, Integer> bestCombi2 (ArrayList<BookList> listSeller){
        HashMap<Integer, Integer> bestComb = new HashMap<>();
        Integer oldPrice = 0;
        while(missingBook(bestComb) != null){

        }

        return bestComb;
    }


    public ArrayList<Integer> bestCombi(ArrayList<BookList> listSeller){
        ArrayList<Integer> bestComb = new ArrayList<Integer>();
        Double old_price = 0.0;
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
                System.out.println(total);
            }
            return bestComb;
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

    */
}
