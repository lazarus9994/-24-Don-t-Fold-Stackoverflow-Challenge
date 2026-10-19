import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        String line;

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

        JSONObject data = new JSONObject(stringBuilder.toString());

        JSONArray pokerHands = data.getJSONArray("poker_hands");

        JSONArray cards =  new JSONArray();

        Map<String,String> pokerCards = new HashMap<>();

        for (int i = 0; i < pokerHands.length(); i++) {
            cards = pokerHands.getJSONObject(i).getJSONArray("cards");

            for (int j = 0; j < cards.length(); j++) {
                JSONObject card = cards.getJSONObject(j);
                pokerCards.put(card.getString("suit"), card.getString("rank"));
            }
        }

    }
}
