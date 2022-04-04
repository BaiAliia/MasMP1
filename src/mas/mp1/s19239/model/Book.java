package mas.mp1.s19239.model;

import mas.mp1.s19239.model.exceptions.ModelValException;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.spi.LocaleNameProvider;
import java.util.stream.Collectors;

public class Book implements Serializable {
    private static List<Book> extent =new ArrayList<>();//My extent

    private long id;
    private String name;
    private LocalDate releaseDate ;
    private Set<String> Genres=new HashSet<>();//multivalued
    private Language language;
    private int originalPrice;
    private Sale sale;
    private Author author;
    private String publisher;//Optional
    private static int totalNOfbooks=0; //class attribute
    private Adress adress;//complex





//mandatory attributes
    public Book(long id, String name,LocalDate date,String genre,Language language,int price,Sale sale,Author author,Adress adr) {
        this.id = id;
        setName(name);
        setReleaseDate(date);
        addGenre(genre);
        setLanguage(language);
        setOriginalPrice(price);
        setSale(sale);
        setAuthor(author);
        totalNOfbooks++;
        extent.add(this);
        setAdress(adr);

    }

   // This constructor includes optional attribute publisher (String)
    public Book(long id, String name,LocalDate date,String genre,Language language,int price,Sale sale,Author author,String pub,Adress adr) {
        this.id = id;
        setName(name);
        setReleaseDate(date);
        addGenre(genre);
        setLanguage(language);
        setOriginalPrice(price);
        setSale(sale);
        setAuthor(author);
        setPublisher(pub);
        totalNOfbooks++;
        extent.add(this);
        setAdress(adr);
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null||name.isBlank()){
            throw new ModelValException("The name cant be null or empty ");
        }
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if(releaseDate==null){
            throw new ModelValException("invalid date ");
        }
        this.releaseDate = releaseDate;
    }

    public Set<String> getGenres() {
        return Collections.unmodifiableSet(this.Genres);
    }

    public void addGenre(String genre){
        if(genre==null||genre.isBlank()){
            throw new ModelValException("The genre cant be null or empty ");
        }
        this.Genres.add(genre);
    }
    public void removeGenre(String genre){
        if(this.Genres.size()<2){
            throw new ModelValException("The book should have at least one genre ,cant remove the only genre");
        }
        this.Genres.remove(genre);

    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        if(sale==null){
            throw new ModelValException("Invalid sale option ");

        }
        this.sale = sale;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        if(language==null){
            throw new ModelValException("language cant be null ");

        }
        this.language = language;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        if(originalPrice<=0){
            throw new ModelValException("Price cant be negative value ");

        }
        this.originalPrice = originalPrice;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        if(author==null){
            throw new ModelValException("Author cant be null");

        }
        this.author = author;
    }

    //Derived attribute
    public int getCurrentPrice() {
       int currentprice = (int)(this.originalPrice-(this.originalPrice*this.sale.getPercentage()));
      return  currentprice;
    }
    //Method overload gives opportunity to calculate the current price directly and change the sale.
    public int getCurrentPrice(Sale sale) {
        if(sale!=null) {
            int currentprice = (int) (originalPrice - (originalPrice * sale.getPercentage()));
            setSale(sale);
            return currentprice;
        }else {
            throw new ModelValException("sale cant be null");
        }
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        if(adress==null){
            throw new ModelValException("Adress cant be null");
        }
        this.adress = adress;
    }

    public String getPublisher() {
        if(publisher == null || publisher.isBlank()) {
            System.out.println("There is no info about publisher");
            return null;
        }
        else {
            return publisher;
        }
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public static int getTotalNOfbooks() {
        return totalNOfbooks;
    }

    public static List<Book> getExtent() {
        return Collections.unmodifiableList(extent);
    }
    //Extent persistency saving in the file
    public static void saveExtent() throws IOException {
     try(   ObjectOutputStream saving= new ObjectOutputStream(new FileOutputStream("D:\\Lessons\\books.ser"))){
         saving.writeObject(extent);
     }
    }
    public static void loadExtent() throws IOException, ClassNotFoundException {
        try(   ObjectInputStream saving= new ObjectInputStream(new FileInputStream("D:\\Lessons\\books.ser"))){
            extent=(List<Book>) saving.readObject();
        }
    }

    //Class method which finds books with given genre,returns list of books
    public static List<Book> findByGenre(String genre){
        if(genre==null||genre.isBlank()){
            return new ArrayList<>();
        }
        return extent.stream()
                .filter(b ->b.Genres.contains(genre))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Name: '" + this.name + "', OriginalPrice: '" + this.originalPrice+"', Genre: '" + this.getGenres() ;
    }
}
