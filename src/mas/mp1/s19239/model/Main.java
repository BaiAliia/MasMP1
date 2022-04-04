package mas.mp1.s19239.model;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        //extent persistency implementation in the class Book method saveExtent()
        //extent in the class Book - List<Book> extent

      Author author1=new Author(1,"Agatha Christie");
      Author author2=new Author(2,"J.K.Rowling");
      Author author3=new Author(3,"Leo Tolstoy");

      Adress adress =new Adress("Bishkek","Chuy","178003");//Complex attribute
      Adress adress2 =new Adress("Warsaw","Sluzew","188003");

      Book book1=new Book(1, "Harry Potter",LocalDate.of(2015, 12, 15),"Fantasy",Language.English,50,Sale.low,author2,adress);
      Book book2=new Book(2, "The mysterious affair at styles ",LocalDate.of(1920, 06, 12),"Crime fiction",Language.English,60,Sale.Not,author1,adress2);
      Book book3=new Book(3, "War and Peace",LocalDate.of(1869, 05, 16),"Novel",Language.Russian,70,Sale.mid,author3,"BestNovels",adress);//has optional attribute publisher

    book2.addGenre("Novel");//Multivalued attribute
     System.out.println( book3.getCurrentPrice());//Derived attribute - calculates current price based on the sale and originalPrice
     System.out.println( book2.getCurrentPrice(Sale.mid));//Method overload
     System.out.println(book1.toString());//my override
     System.out.println( Book.findByGenre("Novel"));//Class method which finds the books by genre
     System.out.println(  Book.getTotalNOfbooks());//class attribute

    Book.saveExtent(); //extent (implemented in Book class)
    }
}
