import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CardTest {
    public static void main(String[] args) {
        // completely different
        Card c0 = new Card(1, "card1", Rank.COMMON);
        Card c1 = new Card(2, "card2", Rank.RARE);
        // exactly the same
        Card c2 = new Card(3, "card3", Rank.UNCOMMON);
        Card c3 = new Card(3, "card3", Rank.UNCOMMON);
        // somewhat different (not exactly the same)
        Card c4 = new Card(4, "card5", Rank.UNIQUE);
        Card c5 = new Card(4, "ccard5", Rank.UNIQUE); // different name
        Card c6 = new Card(5, "card5", Rank.UNIQUE);  // different id

        // Testing compareTo method
        System.out.println("c0 and c1: " + c0.compareTo(c1));
        System.out.println("c2 and c3: " + c2.compareTo(c3));
        System.out.println("c4 and c5: " + c4.compareTo(c5));
        System.out.println("c4 and c6: " + c4.compareTo(c6));

        // printing hashcodes of each card
        System.out.println("HC c0: " + c0.hashCode());
        System.out.println("HC c1: " + c1.hashCode());
        System.out.println("HC c2: " + c2.hashCode());
        System.out.println("HC c3: " + c3.hashCode());

        // Testing "hashCode" using assertions
        assert (c0.hashCode() == c1.hashCode()): "Code not equal";
        assert (c2.hashCode() == c3.hashCode()): "Code not equal";
        assert (c4.hashCode() == c5.hashCode()): "Code not equal";
        assert (c4.hashCode() == c6.hashCode()): "Code not equal";

        // Testing "equals" using assertions with each group
        System.out.println("First Group");
        assert (c0.equals(c1)) : "Cards not equal";
        System.out.println("Second Group");
        assert (c2.equals(c3)) : "Cards not equal";
        System.out.println("Third Group");
        assert (c4.equals(c5)) : "Cards not equal";
        assert (c4.equals(c6)) : "Cards not equal";

        // Checking HashSet for repetation
        HashSet<Card> cardHashSet = new HashSet<>();
        cardHashSet.add(c0);
        cardHashSet.add(c1);
        cardHashSet.add(c2);
        cardHashSet.add(c3);
        cardHashSet.add(c4);
        cardHashSet.add(c5);
        cardHashSet.add(c6);

        System.out.println("HashSet");
        for (Card cards: cardHashSet){
            System.out.println(cards);
        }

        // Checking TreeSet for order
        Set<Card> cardTreeSet = new TreeSet<>();
        cardTreeSet.add(c0);
        cardTreeSet.add(c1);
        cardTreeSet.add(c2);
        cardTreeSet.add(c3);
        cardTreeSet.add(c4);
        cardTreeSet.add(c5);
        cardTreeSet.add(c6);

        System.out.println("TreeSet");
        for (Card cards: cardTreeSet){
            System.out.println(cards);
        }

    }
}