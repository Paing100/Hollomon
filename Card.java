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
    public boolean equals(Object otherCard){
        boolean result = false;
        if (otherCard == null){
            return false;
        }
            Card newCard = (Card)otherCard;
            if ((newCard.id == this.id) && (newCard.name.equals(this.name))
                    && (newCard.rank == this.rank)){
                result = true;
            }

        return result;
    }

    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.id, this.name, this.rank);
    }

    @Override
    public int compareTo(Card o) {
        if (this.rank == o.rank && this.name.equals(o.name)
        && this.id == o.id){
            return 0;
        }
        if (this.rank.ordinal() < o.rank.ordinal()){
            return 1;
        }
        if (this.id < o.id){
            return 1;
        }
        if (this.name.compareTo(o.name) > 0){
            return 1;
        }
        else {
            return -1;
        }
    }
}
