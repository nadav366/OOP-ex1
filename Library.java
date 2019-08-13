/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library
 * to be able to check out books, if a copy of the requested book is available.
 */
class Library {
    /** An array containing all books in the library. */
    Book[] allBooks;

    /** An array containing all Patrins in the library. */
    Patron[] allPatron;

    /** The maximal number of registered patrons this library can handle. */
    final int maxBorrowedBooks;

    /* An array that keeps the quantity of books for all patron */
    int[] numBorrowerPatron;

    /* The number of books at any moment in the library. */
    int currentBooksNum = 0;

    /* The number of patrons at any moment in the library. */
    int currentPatronNum = 0;

    /**
     * Creates a new library with the given parameters.
     * @param maxBookCapacity The maximal number of books this library can hold.
     * @param maxBorrowedBooks The maximal number of books this library allows a single patron to borrow at
     *                        the same time.
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    Library(int maxBookCapacity,
            int maxBorrowedBooks,
            int maxPatronCapacity){

        allBooks = new Book[maxBookCapacity];
        allPatron = new Patron[maxPatronCapacity];
        this.maxBorrowedBooks = maxBorrowedBooks;
        numBorrowerPatron = new int[maxPatronCapacity];
    }

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     * @param book The book to add to this library.
     * @returna non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book){
        // Check whether the book already exists in the library-
        for (int i=0; i<currentBooksNum; i++)
            if (book == allBooks[i])
                return i;
        // Check whether there is a library space for another book -
        if (currentBooksNum >= allBooks.length)
            return -1;
        // add book to lib-
        allBooks[currentBooksNum] = book;
        currentBooksNum++;
        return currentBooksNum-1;
    }

    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     * @param bookId The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId){
        return (0 <= bookId) && (bookId < currentBooksNum);
    }

    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     * @param book The book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book){
        for (int i=0; i<currentBooksNum; i++)
            if (book == allBooks[i])
                return i;
        return -1; // If book is not found
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     * @param bookId The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId) {
        if (!isBookIdValid(bookId))
            return false;
        if (allBooks[bookId].getCurrentBorrowerId() != -1) // Check whether the book is not loaned
            return false;
        return true;
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     * @param patron The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron was successfully
     * registered, a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron){
        // Check whether the patron already exists in the library-
        for (int i=0; i<currentPatronNum; i++)
            if (patron == allPatron[i])
                return i;
        // Check whether there is a library space for another patron -
        if (currentPatronNum >= allPatron.length)
            return -1;
        // add patron-
        allPatron[currentPatronNum] = patron;
        numBorrowerPatron[currentPatronNum] = 0;
        currentPatronNum++;
        return currentPatronNum-1;
    }

    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     * @param patronId The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId){
        return (0 <= patronId) && (patronId < currentPatronNum);
    }

    /**
     * Returns the non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     * @param patron The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     */
    int getPatronId(Patron patron){
        for (int i=0; i<currentPatronNum; i++)
            if (patron == allPatron[i])
                return i;
        return -1; // No suitable person found
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id,
     * if this book is available, the given patron isn't already borrowing the maximal number of books
     * allowed, and if the patron will enjoy this book.
     * @param bookId The id number of the book to borrow.
     * @param patronId The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId){
        // Tests can be done for borrowing
        if (!isBookIdValid(bookId) || !isPatronIdValid(patronId)) // Is the ID's valid
            return false;
        if (allBooks[bookId].getCurrentBorrowerId() != -1) // Is the book not borrowed
            return false;
        if (numBorrowerPatron[patronId] >= maxBorrowedBooks) // Can the person take another book
            return false;
        if (!allPatron[patronId].willEnjoyBook(allBooks[bookId])) // Will the person enjoy from the book
            return false;
        numBorrowerPatron[patronId]++;
        allBooks[bookId].setBorrowerId(patronId);
        return true;
    }

    /**
     * Return the given book.
     * @param bookId The id number of the book to return.
     */
    void returnBook(int bookId){
        if (!isBookIdValid(bookId))
            return;
        if(allBooks[bookId].getCurrentBorrowerId() == -1)
            return;
        numBorrowerPatron[allBooks[bookId].getCurrentBorrowerId()]--;
        allBooks[bookId].returnBook();
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most, out of all available books he
     * will enjoy, if any such exist.
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given will enjoy the most. Null if no book is available.
     */
    Book suggestBookToPatron(int patronId){
        int maxBookScore = 0;
        int idMaxBook = -1;
        if (!isPatronIdValid(patronId))
            return null;
        for (int i=0; i<currentBooksNum; i++){
            if (allBooks[i].getCurrentBorrowerId() != -1 || !allPatron[patronId].willEnjoyBook(allBooks[i]))
                continue;
            if (allPatron[patronId].getBookScore(allBooks[i]) > maxBookScore){
                maxBookScore = allPatron[patronId].getBookScore(allBooks[i]);
                idMaxBook = i;
            }
        } // for lop
        if (idMaxBook == -1)
                return null;
        return allBooks[idMaxBook];
    }

}
