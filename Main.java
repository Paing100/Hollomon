public class Main {
    public static void main(String[] args){
        Card c0 = new Card(1, "card1", Rank.COMMON);
        Card c1 = new Card(2, "card2", Rank.COMMON);
        Card c2 = new Card(1, "card1", Rank.COMMON);
        StringBuilder sb0 = new StringBuilder("card1");
        String s0 = sb0.toString();
        Card c3 = new Card(1, s0, Rank.COMMON);
        Card c4 = new Card(2, "acard2", Rank.COMMON);

        System.out.println(c1.compareTo(c4));
    }
}
