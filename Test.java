import static org.junit.Assert.*;


public class Test {
    Book book1 = new Book("AAA", "BBB", 1994, 3, 6, 19);
    Book book2 = new Book("CC", "DD", 2012, 5, 2, 1);
    Book book3 = new Book("EEE", "FFFF", 8769, 10, 3, 4);

    Patron man1 = new Patron("Nadav", "Hartuv", 2,3,4,101);
    Patron man2 = new Patron("Guy", "Katz", 5,2,4,21);
    Patron man3 = new Patron("Ortal", "Glatt", 1,5,10,180);
    Patron man4 = new Patron("Itay", "Tayar", 0,3,2,2);

    Library lib1 = new Library(3,1,4);
    Library lib2 = new Library(2,0,4);
    Library lib3 = new Library(1,2,2);
    Library lib4 = new Library(0,10,10);
    Library lib5 = new Library(3,1,0);


    @org.junit.Test
    public void testBookStringRepresentation() {
        assertEquals("[AAA,BBB,1994,28]",book1.stringRepresentation());
        assertEquals("[CC,DD,2012,8]",book2.stringRepresentation());
        assertEquals("[EEE,FFFF,8769,17]",book3.stringRepresentation());

    }

    @org.junit.Test
    public void testBookGetLiteraryValue() {
        assertEquals(28,book1.getLiteraryValue());
        assertEquals(8,book2.getLiteraryValue());
        assertEquals(17,book3.getLiteraryValue());

    }

    @org.junit.Test
    public void testGetCurrentBorrowerId() {
        assertEquals(-1,book1.getCurrentBorrowerId());
        assertEquals(-1,book2.getCurrentBorrowerId());
        assertEquals(-1,book3.getCurrentBorrowerId());

        book1.setBorrowerId(89);
        assertEquals(89,book1.getCurrentBorrowerId());

        book2.setBorrowerId(-9);
        assertEquals(-9,book2.getCurrentBorrowerId());

        book2.setBorrowerId(33);
        assertEquals(33,book2.getCurrentBorrowerId());

    }

    @org.junit.Test
    public void testReturnBook() {
        book1.returnBook();
        assertEquals(-1,book1.getCurrentBorrowerId());

        book2.returnBook();
        assertEquals(-1,book2.getCurrentBorrowerId());

        book3.returnBook();
        assertEquals(-1,book3.getCurrentBorrowerId());

    }

    @org.junit.Test
    public void testStringRepresentation() {
        assertEquals("Nadav Hartuv",man1.stringRepresentation());
        assertEquals("Guy Katz",man2.stringRepresentation());
        assertEquals("Ortal Glatt",man3.stringRepresentation());
        assertEquals("Itay Tayar",man4.stringRepresentation());
    }

    @org.junit.Test
    public void testGetBookScore() {
        assertEquals(100,man1.getBookScore(book1));
        assertEquals(103,man2.getBookScore(book1));
        assertEquals(25,man3.getBookScore(book2));
        assertEquals(17,man4.getBookScore(book3));
        assertEquals(25,man3.getBookScore(book2));
    }

    @org.junit.Test
    public void testWillEnjoyBook() {
        assertTrue(man3. willEnjoyBook(book1));
        assertFalse(man3. willEnjoyBook(book2));
        assertFalse(man3. willEnjoyBook(book3));

        assertFalse(man1. willEnjoyBook(book1));
        assertFalse(man1. willEnjoyBook(book2));
        assertFalse(man1. willEnjoyBook(book3));

        assertTrue(man2. willEnjoyBook(book1));
        assertTrue(man2. willEnjoyBook(book2));
        assertTrue(man2. willEnjoyBook(book3));
    }

    @org.junit.Test
    public void testAddBookToLibrary() {
        assertEquals(0,lib1.addBookToLibrary(book1));
        assertEquals(1,lib1.addBookToLibrary(book2));
        assertEquals(1,lib1.addBookToLibrary(book2));
        assertEquals(0,lib1.addBookToLibrary(book1));

        assertEquals(0,lib2.addBookToLibrary(book1));
        assertEquals(1,lib2.addBookToLibrary(book2));
        assertEquals(1,lib2.addBookToLibrary(book2));
        assertEquals(-1,lib2.addBookToLibrary(book3));

        assertEquals(-1,lib4.addBookToLibrary(book1));
        assertEquals(-1,lib4.addBookToLibrary(book2));
        assertEquals(-1,lib4.addBookToLibrary(book3));

        assertEquals(0, lib3.addBookToLibrary(book3));
    }

    @org.junit.Test
    public void testIsBookIdValid() {
        testAddBookToLibrary();

        assertTrue(lib1.isBookIdValid(0));
        assertTrue(lib1.isBookIdValid(1));
        assertFalse(lib1.isBookIdValid(2));
        assertFalse(lib1.isBookIdValid(67));
        assertFalse(lib1.isBookIdValid(-45));
        assertFalse(lib1.isBookIdValid(-1));

        for (int i=-5; i<5; i++) {
            assertFalse(lib4.isBookIdValid(i));
            assertFalse(lib5.isBookIdValid(i));
        }

        assertTrue(lib2.isBookIdValid(0));
        assertTrue(lib2.isBookIdValid(1));
        assertFalse(lib2.isBookIdValid(2));
    }

    @org.junit.Test
    public void testGetBookId() {
        testAddBookToLibrary();

        assertEquals(0,lib1.getBookId(book1));
        assertEquals(1,lib1.getBookId(book2));
        assertEquals(-1,lib1.getBookId(book3));
        assertEquals(-1,lib1.getBookId(null));

        assertEquals(0,lib3.getBookId(book3));
        assertEquals(-1,lib3.getBookId(book2));
        assertEquals(-1,lib3.getBookId(book1));
        assertEquals(-1,lib3.getBookId(null));

    }

    @org.junit.Test
    public void testIsBookAvailable() {
        testAddBookToLibrary();
        testReturnBook();

        assertFalse(lib1.isBookAvailable(-2));
        assertTrue(lib1.isBookAvailable(0));
        assertTrue(lib1.isBookAvailable(1));
        assertFalse(lib1.isBookAvailable(3));
        assertFalse(lib1.isBookAvailable(12));

        book1.setBorrowerId(1);
        book2.setBorrowerId(54);
        assertFalse(lib1.isBookAvailable(0));
        assertFalse(lib1.isBookAvailable(1));
        assertFalse(lib2.isBookAvailable(0));
    }

    @org.junit.Test
    public void testRegisterPatronToLibrary() {
        assertEquals(0,lib1.registerPatronToLibrary(man1));
        assertEquals(1,lib1.registerPatronToLibrary(man2));
        assertEquals(2,lib1.registerPatronToLibrary(man3));
        assertEquals(0,lib1.registerPatronToLibrary(man1));
        assertEquals(1,lib1.registerPatronToLibrary(man2));
        assertEquals(3,lib1.registerPatronToLibrary(man4));

        assertEquals(-1,lib5.registerPatronToLibrary(man1));
        assertEquals(-1,lib5.registerPatronToLibrary(man2));
        assertEquals(-1,lib5.registerPatronToLibrary(man4));

        assertEquals(0,lib4.registerPatronToLibrary(man1));
        assertEquals(1,lib4.registerPatronToLibrary(man2));
        assertEquals(2,lib4.registerPatronToLibrary(man3));
        assertEquals(0,lib4.registerPatronToLibrary(man1));
        assertEquals(1,lib4.registerPatronToLibrary(man2));

    }

    @org.junit.Test
    public void testIsPatronIdValid() {
        testRegisterPatronToLibrary();

        assertTrue(lib1.isPatronIdValid(0));
        assertTrue(lib1.isPatronIdValid(1));
        assertTrue(lib1.isPatronIdValid(2));
        assertTrue(lib1.isPatronIdValid(3));
        assertFalse(lib1.isPatronIdValid(-1));
        assertFalse(lib1.isPatronIdValid(-78));
        assertFalse(lib1.isPatronIdValid(4));
        assertFalse(lib1.isPatronIdValid(48));

        assertFalse(lib5.isPatronIdValid(48));
        assertFalse(lib5.isPatronIdValid(0));
        assertFalse(lib5.isPatronIdValid(-1));
    }

    @org.junit.Test
    public void testGetPatronId() {
        testRegisterPatronToLibrary();

        assertEquals(0, lib1.getPatronId(man1));
        assertEquals(1, lib1.getPatronId(man2));
        assertEquals(2, lib1.getPatronId(man3));
        assertEquals(3, lib1.getPatronId(man4));
        assertEquals(-1, lib1.getPatronId(null));

        assertEquals(0, lib4.getPatronId(man1));
        assertEquals(1, lib4.getPatronId(man2));
        assertEquals(2, lib4.getPatronId(man3));
        assertEquals(-1, lib4.getPatronId(man4));
        assertEquals(-1, lib4.getPatronId(null));

        assertEquals(-1, lib5.getPatronId(man1));
        assertEquals(-1, lib5.getPatronId(man2));
        assertEquals(-1, lib5.getPatronId(man3));
    }

    @org.junit.Test
    public void testBorrowBook() {
        testRegisterPatronToLibrary();
        testAddBookToLibrary();
        testReturnBook();

        assertFalse(lib1.borrowBook(0,67));
        assertFalse(lib1.borrowBook(0,-5));
        assertFalse(lib1.borrowBook(42,0));
        assertFalse(lib1.borrowBook(-87,1));

        book1.setBorrowerId(0);
        book2.setBorrowerId(1);
        assertFalse(lib1.borrowBook(0,3));
        assertFalse(lib1.borrowBook(1,3));

        assertFalse(lib1.borrowBook(2,0));
        assertFalse(lib1.borrowBook(3,1));

        assertFalse(lib1.borrowBook(3,1));

        testReturnBook();

        assertTrue(lib1.borrowBook(0,2));
        assertFalse(lib1.borrowBook(1,2));
        assertFalse(lib1.borrowBook(2,2));

    }

    @org.junit.Test
    public void oldTester() {

        // set up
        Book book1 = new Book("book1", "author1", 2001,
                2, 3, 0);
        Book book2 = new Book("book2", "author2", 2002,
                8, 3, 6);
        Book book3 = new Book("book2", "author2", 2002,
                8, 3, 6);
        Book book4 = new Book("book4", "author4", 2004,
                10, 10, 10);

        int book1ID, book2ID, book3ID, book4ID;

        Patron patron1 = new Patron("patron1", "last1", 3,
                5, 1, 22);
        Patron patron2 = new Patron("patron1", "last1", 2,
                1, 1, 0);
        Patron patron3 = new Patron("patron3", "last3", 2,
                1, 1, 0);

        int patron1ID1, patron2ID1, patron1ID2, patron2ID2;

        Library lib = new Library(3, 2, 2);



        assertTrue(book1.stringRepresentation().equals("[book1,author1,2001,5]"));

        assertTrue(book1.getLiteraryValue() == 2 + 3 );

        assertTrue(book1.getCurrentBorrowerId() < 0);

        book1.setBorrowerId(5);
        assertTrue(book1.getCurrentBorrowerId() == 5);
        book1.returnBook();


        // test patron

        assertTrue(patron1.stringRepresentation().equals("patron1 last1"));

        assertTrue(patron1.getBookScore(book1) == 3 * 2 + 5 * 3 + 1 * 0);

        assertTrue(!patron1.willEnjoyBook(book1)); // 22=22
        assertTrue(patron1.willEnjoyBook(book2));


        // test library
        assertTrue((!lib.isBookIdValid(1)));
        assertTrue((!lib.isBookIdValid(88)));
        assertTrue(!lib.isBookAvailable(1));

        book1ID = lib.addBookToLibrary(book1);
        assertTrue((book1ID >= 0));
        assertTrue(lib.isBookIdValid(book1ID));

        book2ID = lib.addBookToLibrary(book2);
        assertTrue((book2ID >= 0));
        assertTrue((book2ID != book1ID));
        assertTrue((lib.isBookIdValid(book2ID)));
        assertTrue((lib.addBookToLibrary(book2) == book2ID)); // add the same book, should
        // return the index of original and shouldn't save another copy.

        book3ID = lib.addBookToLibrary(book3); // book with same  arguments
        assertTrue((book3ID >= 0 && book3ID != book1ID && book3ID != book2ID));

        assertTrue((lib.addBookToLibrary(book4) < 0)); // no room
        assertTrue((lib.addBookToLibrary(book2) == book2ID)); // add the same book when
        // there is no room.

        assertTrue((!lib.isPatronIdValid(1)));
        assertTrue((!lib.isPatronIdValid(8)));

        patron1ID1 = lib.registerPatronToLibrary(patron1);
        assertTrue((patron1ID1 >= 0));
        assertTrue((lib.registerPatronToLibrary(patron1) == patron1ID1));
        assertTrue((lib.isPatronIdValid(patron1ID1)));

        patron2ID1 = lib.registerPatronToLibrary(patron2);
        assertTrue((lib.registerPatronToLibrary(patron2) == patron2ID1));
        assertTrue((lib.isPatronIdValid(patron2ID1)));

        assertTrue((lib.registerPatronToLibrary(patron3) < 0));
        assertTrue((lib.registerPatronToLibrary(patron1) == patron1ID1));

        assertTrue((lib.getPatronId(patron2) == patron2ID1));

        // tests for 'borrowBook'
        assertTrue((lib.isBookAvailable(book1ID)));
        //assertTrue((lib.borrowBook(book1ID, patron1ID1))); // will not enjoy
        assertTrue((lib.borrowBook(book1ID, patron2ID1)));
        assertTrue((!lib.isBookAvailable(book1ID)));
        assertTrue((!lib.borrowBook(80, patron2ID1))); // book id is not valid
        assertTrue((!lib.borrowBook(book2ID, 80))); // patron id is not
        // valid
        assertTrue((lib.borrowBook(book2ID, patron2ID1)));
        System.out.println((!lib.borrowBook(book3ID, patron2ID1)));
        assertTrue((!lib.borrowBook(book3ID, patron2ID1))); // too many books for one patron
        assertTrue((!lib.borrowBook(book2ID, patron1ID1))); // the book is taken
        lib.returnBook(book1ID);
        assertTrue((lib.borrowBook(book3ID, patron2ID1)));
        assertTrue((book1.getCurrentBorrowerId() < 0));


        assertTrue((lib.getBookId(lib.suggestBookToPatron(patron2ID1)) == book1ID));
        assertTrue((lib.suggestBookToPatron(patron1ID1) == null)); // null because book2 and
        // book3 are taken and he will not enjoy book1

        // two libraries tests
        Library lib2 = new Library(1, 1, 2);

        assertTrue((!lib2.isBookIdValid(0)));

        book4ID = lib2.addBookToLibrary(book4);
        assertTrue((book4ID >= 0));


        patron1ID2 = lib2.registerPatronToLibrary(patron1);
        patron2ID2 = lib2.registerPatronToLibrary(patron2);

        assertTrue((patron1ID2 >= 0));
        assertTrue((patron2ID2 >= 0));
        assertTrue((patron1ID2 != patron2ID2));

        assertTrue((lib2.borrowBook(book4ID, patron1ID2))); // patron1 has the max number
        // of books in library1, should be able to borrow in another library.
    }

    @org.junit.Test
    public void old() {

        // set up
        Book book1 = new Book("book1", "author1", 2001,
                2, 3, 1);
        Book book2 = new Book("book2", "author2", 2002,
                8, 3, 6);
        Book book3 = new Book("book3", "author2", 2002,
                8, 3, 6);
        Book book4 = new Book("book4", "author4", 2004,
                10, 10, 10);

        int book1ID, book2ID, book3ID, book4ID;

        Patron patron1 = new Patron("patron1", "last1", 3,
                5, 1, 22);
        Patron patron2 = new Patron("patron1", "last1", 2,
                1, 1, 0);
        Patron patron3 = new Patron("patron3", "last3", 2,
                1, 1, 0);

        int patron1ID1, patron2ID1, patron1ID2, patron2ID2;

        Library lib = new Library(3, 2, 2);

//        return;


        // test book
        System.out.println("test Book methods");
        System.out.println();

        System.out.println(book1.stringRepresentation().equals("[book1,author1,2001,6]"));

        System.out.println(book1.getLiteraryValue() == 2 + 3 + 1);

        System.out.println(book1.getCurrentBorrowerId() < 0);

        book1.setBorrowerId(5);
        System.out.println(book1.getCurrentBorrowerId() == 5);
        book1.returnBook();


        // test patron
        System.out.println();

        System.out.println("test Patron methods");
        System.out.println();

        System.out.println("0.1:" + patron1.stringRepresentation().equals("patron1 last1"));

        System.out.println("0.2:" + (patron1.getBookScore(book1) == 3 * 2 + 5 * 3 + 1 * 1));

        System.out.println("0.3:" + patron1.willEnjoyBook(book1)); // 22=22

        System.out.println("0.4:" + patron1.willEnjoyBook(book2));


        // test library
        System.out.println();

        System.out.println("test Library methods");
        System.out.println();

        System.out.println("1: " + (!lib.isBookIdValid(1)));
        System.out.println("1.1: " + (!lib.isBookIdValid(88)));
        System.out.println("1.2: " + (!lib.isBookAvailable(1)));

        book1ID = lib.addBookToLibrary(book1);
        System.out.println("2: " + (book1ID >= 0));
        System.out.println("3: " + lib.isBookIdValid(book1ID));

        book2ID = lib.addBookToLibrary(book2);
        System.out.println("4: " + (book2ID >= 0));
        System.out.println("4.1: " + (book2ID != book1ID));
        System.out.println("4.2: " + (lib.isBookIdValid(book2ID)));
        System.out.println("4.3: " + (lib.addBookToLibrary(book2) == book2ID)); // add the same book, should
        // return the index of original and shouldn't save another copy.

        book3ID = lib.addBookToLibrary(book3); // book with same  arguments
        System.out.println("5: " + (book3ID >= 0 && book3ID != book1ID && book3ID != book2ID));

        System.out.println("6: " + (lib.addBookToLibrary(book4) < 0)); // no room
        System.out.println("6.1: " + (lib.addBookToLibrary(book2) == book2ID)); // add the same book when
        // there is no room.

        System.out.println("7: " + (!lib.isPatronIdValid(1)));
        System.out.println("7.1: " + (!lib.isPatronIdValid(8)));

        patron1ID1 = lib.registerPatronToLibrary(patron1);
        System.out.println("8:" + (patron1ID1 >= 0));
        System.out.println("9:" + (lib.registerPatronToLibrary(patron1) == -1));
        System.out.println("10: " + (lib.isPatronIdValid(patron1ID1)));

        patron2ID1 = lib.registerPatronToLibrary(patron2);
        System.out.println("11: " + (lib.registerPatronToLibrary(patron2) == -1));
        System.out.println("12: " + (lib.isPatronIdValid(patron2ID1)));

        System.out.println("13: " + (lib.registerPatronToLibrary(patron3) < 0));
        System.out.println("14: " + (lib.registerPatronToLibrary(patron1) == -1));

        System.out.println("15: " + (lib.getPatronId(patron2) == patron2ID1));

        // tests for 'borrowBook'
        System.out.println("16: " + (lib.isBookAvailable(book1ID)));
//        System.out.println("wiil enjoy?" + patron1.willEnjoyBook(book1));
        System.out.println("17: " + (lib.borrowBook(book1ID, patron1ID1))); // will not enjoy
        System.out.println("18: " + (!lib.borrowBook(book1ID, patron2ID1)));
        System.out.println("19: " + (!lib.isBookAvailable(book1ID)));
        System.out.println("20: " + (!lib.borrowBook(80, patron2ID1))); // book id is not valid
        System.out.println("21: " + (!lib.borrowBook(book2ID, 80))); // patron id is not
        // valid
        System.out.println("22: " + (lib.borrowBook(book2ID, patron2ID1)));
        System.out.println("23: " + (lib.borrowBook(book3ID, patron2ID1))); // too many booksArray for one patron
        System.out.println("24: " + (!lib.borrowBook(book2ID, patron1ID1))); // the book is taken
        lib.returnBook(book1ID);
        System.out.println("25: " + (!lib.borrowBook(book3ID, patron2ID1)));
        System.out.println("26: " + (book1.getCurrentBorrowerId() < 0));


        for (Book book : lib.allBooks)
            if (book != null)
                System.out.println(book.stringRepresentation());
        System.out.println();

        System.out.println("27: " + (lib.getBookId(lib.suggestBookToPatron(patron2ID1)) == book1ID));
        System.out.println("28: " + (lib.suggestBookToPatron(patron1ID1) != null)); // null because book2 and
        // book3 are taken and he will not enjoy book1

        // two libraries tests
        Library lib2 = new Library(1, 1, 2);
        System.out.println();
        System.out.println("two libraries tests");
        System.out.println();

        System.out.println("29: " + (!lib2.isBookIdValid(0)));

        book4ID = lib2.addBookToLibrary(book4);
        System.out.println("30: " + (book4ID >= 0));


        patron1ID2 = lib2.registerPatronToLibrary(patron1);
        patron2ID2 = lib2.registerPatronToLibrary(patron2);

        System.out.println("31: " + (patron1ID2 >= 0));
        System.out.println("32: " + (patron2ID2 >= 0));
        System.out.println("33: " + (patron1ID2 != patron2ID2));

        System.out.println("34: " + (lib2.borrowBook(book4ID, patron1ID2))); // patron1 has the max number
        // of booksArray in library1, should be able to borrow in another library.

    }


}

