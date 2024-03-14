import java.util.Objects;

public class Card implements Comparable<Card>{
    private long id;
    private String name;
    private Rank rank;
    private long price;

    public Card(){}

    public Card(long id, String name, Rank rank){
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = 0;
    }

    public Card(long id, String name, Rank rank, long price){
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = price;
    }

    public long getPrice(){
        return this.price;
    }

    public long getID(){
        return this.id;
    }

    @Override
    public String toString(){
        return "ID: " + this.id + "\nName: " + this.name +
                "\nRank: " + this.rank + "\nPrice: " + this.price;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Card) {
            Card newCard = (Card) o;
            return (newCard.id == this.id) && (newCard.name.equals(this.name))
                    && (newCard.rank == this.rank);
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        // the variables that need to be compared with "equals" method
        return Objects.hash(this.id, this.name, this.rank);
    }

    @Override
    public int compareTo(Card o) {
        if (this.equals(o)) {
            return 0;
        }
        if (this.id != o.id) {
            return Long.compare(this.id, o.id);
        }
        int nameComparison = this.name.compareTo(o.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        return Integer.compare(this.rank.ordinal(), o.rank.ordinal());
    }

}
