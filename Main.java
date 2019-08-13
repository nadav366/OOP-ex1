public class Main {
    public static void main(String[] args){
        System.out.println("this test will printout every error found, you'll need to follow" +
                "\n"+"the num of section to see the description of the problem");
        System.out.println("if you see something like: 'A.1.1' that means you got an error");

        //setup part
        System.out.println("Part 0 - setup");

        // Simplifying Assumptions Tests
        // defining variables for all of the tests
        Book book1 = new Book("book1", "Or Tal", 1992,
                5, 6,2);
        Book book2 = new Book("book2", "Or Tal", 1982,
                6, 4,2);
        Book book3 = new Book("book3", "Aharon", 1982,
                9, 2,2);
        Book book4 = new Book("book4", "Or Tal", 1972,
                4, 4,4);
        Book book5 = new Book("book5", "Aharon", 1972,
                2, 2,9);
        Book book6 = new Book("book6", "Or Tal", 1992,
                2, 9,2);
        Patron patron1 = new Patron("Amy", "Winehouse",9,
                4,3, 65);
        Patron patron2 = new Patron("Jimmy", "Handrix",6,
                4,7, 70);
        Patron patron3 = new Patron("Kurt", "Cobain",1,
                9,1, 84);
        Patron patron4 = new Patron("Jim", "Morrison",3,
                4,9, 80);
        Patron patron5 = new Patron("Janis", "Joplin",6,
                4,3, 60);
        Library lib1 = new Library (3, 3, 4);
        Library lib2 = new Library (3, 2, 4);


        //add patrons and books to libraries
        int book1Id = lib1.addBookToLibrary(book1);
        if (book1Id != System.identityHashCode(book1)){
            System.out.println("0.1 - didnt assign Id");
        }
        int book2Id = lib1.addBookToLibrary(book2);
        int book3Id = lib1.addBookToLibrary(book3);
        int book4Id = lib2.addBookToLibrary(book4);
        int book5Id = lib2.addBookToLibrary(book5);
        int book6Id = lib2.addBookToLibrary(book6);
        int patron1Id = lib1.registerPatronToLibrary(patron1);
        if (!(patron1Id == System.identityHashCode(patron1))){
            System.out.println("0.2 - didnt assign Id");
        }
        int patron2Id = lib1.registerPatronToLibrary(patron2);
        int patron3Id = lib1.registerPatronToLibrary(patron3);
        int patron4Id = lib1.registerPatronToLibrary(patron4);
        int patron5Id = lib2.registerPatronToLibrary(patron5);
        lib2.registerPatronToLibrary(patron2);
        lib2.registerPatronToLibrary(patron3);
        lib2.registerPatronToLibrary(patron4);

        // start testing
        System.out.println("Part A - Simplifying assumptions");
        //A.1.1 - adds a book that already exist
        if(!(lib1.addBookToLibrary(book1)==System.identityHashCode(book1))) {
            System.out.println("A.1.1");
        }
        if(!(lib1.addBookToLibrary(book2)==System.identityHashCode(book2)))
            System.out.println("A.1.1");
        //A.1.2 - adds a book beyond capacity
        if (!(lib2.addBookToLibrary(book1)==-1))
            System.out.println("A.1.2");
        //A.1.3 - getBookId() for a book not from library
        if (!(lib1.getBookId(book6)==-1))
            System.out.println("A.1.3");
        //A.1.4 - False idValues given in method
        if((lib1.isBookAvailable(1000679879)))
            System.out.println("A.1.4 - isBookAvailable");
        if((lib1.isBookAvailable(book6Id)))
            System.out.println("A.1.4 - isBookAvailable2");
        if((lib1.borrowBook(0, patron1Id)))
            System.out.println("A.1.4 - borrowBook");
        if((lib1.borrowBook(book6Id, patron1Id)))
            System.out.println("A.1.4 - borrowBook2");
        //A.1.5 - 1. check borrow book function works for book from lib1
        // 2. return the same book to lib2 then check if it's still borrowed
        if(!lib1.borrowBook(System.identityHashCode(book1), System.identityHashCode(patron1))){
            System.out.println("A.1.5.1 - failed to borrow book");
        }
        lib2.returnBook(System.identityHashCode(book1));
        if (!(book1.getCurrentBorrowerId()==System.identityHashCode(patron1))){
            System.out.println("A.1.5.2 - returned the book.");
        }
        //A.1.6 - borrow book to wrong patronId
        if (lib1.borrowBook(System.identityHashCode(book1), 1234567902))
            System.out.println("A.1.6.1");
        if (lib1.borrowBook(System.identityHashCode(book1), System.identityHashCode(patron5)))
            System.out.println("A.1.6.2");
        //A.1.7 - borrow a book that doesn't belong to the library
        if (lib1.borrowBook(System.identityHashCode(book6), System.identityHashCode(patron1)))
            System.out.println("A.1.7");
        //A.1.8 - suggest book to illegal patron
        if (!(lib1.suggestBookToPatron(1236542198)==null))
            System.out.println("A.1.8.1");
        if (!(lib1.suggestBookToPatron(System.identityHashCode(patron5))==null))
            System.out.println("A.1.8.1");

        System.out.println("Part B - Book class");
        //B.1 - check string representation
        if (!(book1.stringRepresentation().equals("[book1,Or Tal,1992,13]")))
            System.out.println("B.1.1");
        if (!(book2.stringRepresentation().equals("[book2,Or Tal,1982,12]")))
            System.out.println("B.1.2");
        if (!(book3.stringRepresentation().equals("[book3,Aharon,1982,13]")))
            System.out.println("B.1.3");
        if (!(book4.stringRepresentation().equals("[book4,Or Tal,1972,12]")))
            System.out.println("B.1.4");
        if (!(book5.stringRepresentation().equals("[book5,Aharon,1972,13]")))
            System.out.println("B.1.5");
        if (!(book6.stringRepresentation().equals("[book6,Or Tal,1992,13]")))
            System.out.println("B.1.6");
        //B.2 - check literary value
        if (!(book1.getLiteraryValue()==13))
            System.out.println("B.2.1");
        if (!(book2.getLiteraryValue()==12))
            System.out.println("B.2.2");
        if (!(book3.getLiteraryValue()==13))
            System.out.println("B.2.3");
        if (!(book4.getLiteraryValue()==12))
            System.out.println("B.2.4");
        if (!(book5.getLiteraryValue()==13))
            System.out.println("B.2.5");
        if (!(book6.getLiteraryValue()==13))
            System.out.println("B.2.6");
        //B.3 - set borrower - check borrower
        book1.setBorrowerId(System.identityHashCode(patron1));
        if (!(book1.getCurrentBorrowerId()==System.identityHashCode(patron1)))
            System.out.println("B.3.1");
        //change borrower using inner value directly
        book1.setBorrowerId(System.identityHashCode(patron2));
        if (!(book1.getCurrentBorrowerId()==System.identityHashCode(patron2)))
            System.out.println("B.3.2");
        //B.4 - return book then check borrower
        book1.returnBook();
        if (!(book1.getCurrentBorrowerId()==-1))
            System.out.println("B.4");

        System.out.println("Part C - Patron Class");
        //C.1 String representation
        if (!(patron1.stringRepresentation().equals("Amy Winehouse")))
            System.out.println("C.1.1");
        if (!(patron2.stringRepresentation().equals("Jimmy Handrix")))
            System.out.println("C.1.2");
        if (!(patron3.stringRepresentation().equals("Kurt Cobain")))
            System.out.println("C.1.3");
        if (!(patron4.stringRepresentation().equals("Jim Morrison")))
            System.out.println("C.1.4"+patron4.stringRepresentation());
        if (!(patron5.stringRepresentation().equals("Janis Joplin")))
            System.out.println("C.1.5");
        //C.2 getBookScore
        if (!(patron1.getBookScore(book1)==75))
            System.out.println("C.2.1");
        if (!(patron1.getBookScore(book2)==76))
            System.out.println("C.2.2");
        //C.3 willEnjoyBook
        if (!(patron1.willEnjoyBook(book1)))
            System.out.println("C.3.1");
        if ((patron1.willEnjoyBook(book5)))
            System.out.println("C.3.1");

        System.out.println("Part D - Library Class");
        //some has been already checked during setup
        //D.1 - isBookValid
        if (!(lib1.isBookIdValid(System.identityHashCode(book1))))
            System.out.println("D.1.1");//valid book
        if ((lib1.isBookIdValid(System.identityHashCode(book6))))
            System.out.println("D.1.2");//book that's registered in lib 2
        if ((lib1.isBookIdValid(0)))
            System.out.println("D.1.1");
        //D.2 - getBookId
        if (!(lib1.getBookId(book1)==System.identityHashCode(book1)))
            System.out.println("D.2.1");
        if (!(lib2.getBookId(book6)==-1))
            System.out.println("D.2.2");//book that's registered in lib 2
        //D.3 - isBookAvailable
        lib1.returnBook(System.identityHashCode(book1));
        if (!(lib1.isBookAvailable(System.identityHashCode(book1))))
            System.out.println("D.3.1");
        if (!(lib1.isBookAvailable(System.identityHashCode(book6))))
            System.out.println("D.3.2");//checks for book that's not in the library
        lib1.borrowBook(System.identityHashCode(book1), System.identityHashCode(patron1));
        if ((lib1.isBookAvailable(System.identityHashCode(book1))))
            System.out.println("D.3.3");//checks availability for borrowed book
        //D.4 - isPatronIdValid
        if (!(lib1.isPatronIdValid(System.identityHashCode(patron1))))
            System.out.println("D.4.1");
        if ((lib1.isPatronIdValid(System.identityHashCode(patron5))))
            System.out.println("D.4.2");//patron from lib2
        if (!(lib1.isPatronIdValid(0)))
            System.out.println("D.4.3");//blank Id
        //D.5 - getPatronId
        if (!(lib1.getPatronId(patron1)==System.identityHashCode(patron1)))
            System.out.println("D.5.1");
        if (!(lib1.getPatronId(patron5)==-1))
            System.out.println("D.5.2");//patron from lib2
        //D.6 - suggestBookToPatron
        if (!(lib1.suggestBookToPatron(System.identityHashCode(patron5))==null))
            System.out.println("D.6.1");//patron from lib2
        if (!(lib1.suggestBookToPatron(System.identityHashCode(patron1))==book3))
            System.out.println("D.6.2");
        if (!(lib1.suggestBookToPatron(0)==null))
            System.out.println("D.6.3");//blankId







    }
}
