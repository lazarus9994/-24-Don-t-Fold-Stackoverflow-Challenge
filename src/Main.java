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

        System.out.println(handList.size());

    }

    public boolean RoyalFlush(Hand hand) {

        if(hand.getCards().stream().min(Comparator.comparing(Card::getRank)).get().getRank().getValue() < 10){
            return false;
        }
        return flush(hand) && straight(hand);
    }

    public boolean StraightFlush(Hand hand) {
        if(hand.getCards().stream().anyMatch(card -> card.getRank().getValue() == 14 && hand.getCards().stream().anyMatch(card1 -> card1.getRank().getValue() == 13))){
            return false;
        }
        return flush(hand) && straight(hand);
    }

    public boolean flush(Hand hand) {
        Suit suit = hand.getCards().getFirst().getSuit();
        for (Card card : hand.getCards()){
            if (!card.getSuit().equals(suit)){
                return false;
            }
        }
        return true;
    }

    public boolean straight(Hand hand) {
        boolean isAceInHand = hand.getCards().stream().anyMatch(card -> card.getRank().getValue() == 14);

        if (!isAceInHand){
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
        }

        if (isAceInHand){

            if(hand.getCards().stream().anyMatch(card1 -> card1.getRank().getValue() == 13)){
                Card card = hand.getCards().stream().filter(card1 -> card1.getRank().getValue() == 13).findFirst().get();
                int count = 2;

                for(int i = 0; i < hand.getCards().size(); i++) {
                    if (hand.getCards().get(i).getRank().getValue() == card.getRank().getValue() - 1){
                        card = hand.getCards().get(i);
                        count++;
                        i = -1;
                    }

                    if (count == 5){
                        return true;
                    }
                }
            }

            if(hand.getCards().stream().anyMatch(card1 -> card1.getRank().getValue() == 2)){
                Card card = hand.getCards().stream().filter(card1 -> card1.getRank().getValue() == 2).findFirst().get();
                int count = 2;

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
            }
        }

        return false;
    }

    public boolean fullHouse(Hand hand) {

        boolean threeOfAKind = false;
        boolean twoOfAKind = false;

        int counter = 1;

        int threeOfAKindCard = 0;
        int twoOfAKindCard = 0;

        for (int i = 0; i < hand.getCards().size(); i++) {

            if (threeOfAKindCard != 0 && hand.getCards().get(i).getRank().getValue() == threeOfAKindCard) {
                continue;
            }

            if (twoOfAKindCard != 0 && hand.getCards().get(i).getRank().getValue() == twoOfAKindCard) {
                continue;
            }

            for (int j = i + 1; j < hand.getCards().size(); j++) {
                if (hand.getCards().get(i).getRank().getValue() == hand.getCards().get(j).getRank().getValue()){
                    counter++;
                }
            }

            if (counter == 3){
                threeOfAKindCard = hand.getCards().get(i).getRank().getValue();
                threeOfAKind = true;
                counter = 1;
            }

            if (counter == 2){
                twoOfAKindCard = hand.getCards().get(i).getRank().getValue();
                twoOfAKind = true;
                counter = 1;
            }
        }

        return threeOfAKind && twoOfAKind;
    }
}
