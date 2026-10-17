package smartlibrary;

public class Document {

    public int id;
    public int year;
    public int citations;

    public String title;
    public String author;
    public String abstractText;
    public String keywords;

    public Document(int id, String title, String author,
                    int year, String abstractText,
                    String keywords, int citations) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.citations = citations;
    }

    public String searchableText() {
        return title + " " + author + " "
                + abstractText + " " + keywords;
    }

    public void print() {
        System.out.println("-----------------------------------");
        System.out.println("Document ID : " + id);
        System.out.println("Title       : " + title);
        System.out.println("Author      : " + author);
        System.out.println("Year        : " + year);
        System.out.println("Abstract    : " + abstractText);
        System.out.println("Keywords    : " + keywords);
        System.out.println("Citations   : " + citations);
        System.out.println("-----------------------------------");
    }
}
