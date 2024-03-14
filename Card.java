import java.util.Objects;

public class Card implements Comparable<Card>{
    private long id;
    private String name;
    private Rank rank;
    private long price;

    public Card(long id, String name, Rank rank){
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = 0;
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
        // if everything is exactly the same, it's 0
        if (this.equals(o)){
            return 0;
        }
        if ((this.rank.ordinal() < o.rank.ordinal()) ||
                (this.id < o.id) ||
                 (this.name.compareTo(o.name) > 0)){
            return 1;
        }
        else {
            return -1;
        }
    }
}
