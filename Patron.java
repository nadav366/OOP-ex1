/**
 * This class represents a library patron that has a name and assigns values to different literary aspects of
 * books.
 */
class Patron {

    /** The first name of this patron. */
    final String FirstName;

    /** The last name of this patron. */
    final String LastName;

    /** The weight the patron assigns to the comic aspects of books. */
    final int comicTendency;

    /** The weight the patron assigns to the dramatic aspects of books. */
    final int dramaticTendency;

    /** The weight the patron assigns to the educational aspects of books. */
    final int educationalTendency;

    /** The minimal literary value a book must have for this patron to enjoy it. */
    final int EnjoymentThreshold;

    /**
     * Creates a new patron with the given characteristics.
     * @param patronFirstName The first name of the patron.
     * @param patronLastName The last name of this patron.
     * @param comicTendency The weight the patron assigns to the comic aspects of books.
     * @param dramaticTendency The weight the patron assigns to the dramatic aspects of books.
     * @param educationalTendency The weight the patron assigns to the educational aspects of books.
     * @param patronEnjoymentThreshold The minimal literary value a book must have for this patron to enjoy it.
     */
    Patron(String patronFirstName,
           String patronLastName,
           int comicTendency,
           int dramaticTendency,
           int educationalTendency,
           int patronEnjoymentThreshold){

        this.FirstName = patronFirstName;
        this.LastName = patronLastName;
        this.comicTendency = comicTendency;
        this.dramaticTendency = dramaticTendency;
        this.educationalTendency = educationalTendency;
        this.EnjoymentThreshold = patronEnjoymentThreshold;

    }

    /**
     * Returns a string representation of the patron, which is a sequence of its first and last name,
     * separated by a single white space. For example, if the patron's first name is "Ricky" and his last
     * name is "Bobby", this method will return the String "Ricky Bobby".
     * @return the String representation of this patron.
     */
    String stringRepresentation(){
        return this.FirstName + " " + this.LastName;
    }

    /**
     * Returns the literary value this patron assigns to the given book.
     * @param book The book to asses.
     * @return the literary value this patron assigns to the given book.
     */
    int getBookScore(Book book){
        int sumScore = 0;
        sumScore += book.comicValue * this.comicTendency;
        sumScore += book.dramaticValue * this.dramaticTendency;
        sumScore += book.educationalValue * this.educationalTendency;
        return sumScore;
    }

    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     * @param book The book to asses.
     * @return true of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book){
        return (this.getBookScore(book) >= this.EnjoymentThreshold);
    }

}
