import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        String line;

        //Reading JSON input from standard input stream
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true){
                line = bufferedReader.readLine();
                if (line.equals("")){
                    break;
                }else stringBuilder.append(line).append("\n");

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //Parsing the JSON data into JSON object
        JSONObject data = new JSONObject(stringBuilder.toString());

        //Extracting poker_hands array
        JSONArray pokerHands = data.getJSONArray("poker_hands");

        JSONArray hand =  new JSONArray();

        List<Hand> handList = new ArrayList<>();


        //Parsing the JSON array to entities Card, Hand
        for (int i = 0; i < pokerHands.length(); i++) {
            hand = pokerHands.getJSONObject(i).getJSONArray("cards");
            Hand handEntity = new Hand();

            for (int j = 0; j < hand.length(); j++) {
                Suit suit = Enum.valueOf(Suit.class, hand.getJSONObject(j).getString("suit").toUpperCase());
                Rank rank = Enum.valueOf(Rank.class, hand.getJSONObject(j).getString("rank").toUpperCase());
                Card card = new Card(suit, rank);
                handEntity.addCard(card);
            }
            handList.add(handEntity);
            
        }

        Map<PokerHandRank, Boolean> map = new HashMap<>();

        System.out.println(handList.size());

    }

    public boolean RoyalFlush(Hand hand) {
        Suit suit = hand.getCards().getFirst().getSuit();
        for (Card card : hand.getCards()){
            if (!card.getSuit().equals(suit)){
                return false;
            }
            if(!(card.getRank().getValue() >= 10)){
                return false;
            }
        }
        return true;
    }

    public boolean StraightFlush(Hand hand) {
        Suit suit = hand.getCards().getFirst().getSuit();
        for (Card card : hand.getCards()){
            if (!card.getSuit().equals(suit)){
                return false;
            }
        }

        Card card = hand.getCards().stream().min(Comparator.comparing(Card::getRank)).get();
        int count = 1;

        for(int i = 0; i < hand.getCards().size(); i++) {
            if (hand.getCards().get(i).getRank().getValue() == card.getRank().getValue() + 1){
                card = hand.getCards().get(i);
                count++;
                i = -1;
            }

            if (count == 5){
                return true;
            }
        }

        return false;
    }

    public boolean Flush(Hand hand) {
        Suit suit = hand.getCards().getFirst().getSuit();
        for (Card card : hand.getCards()){
            if (!card.getSuit().equals(suit)){
                return false;
            }
        }
        return true;
    }
}
