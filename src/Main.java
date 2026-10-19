import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


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

        JSONArray hand =  new JSONArray();

        Map<String, List<String>> hands = new HashMap<>();

        for (int i = 0; i < pokerHands.length(); i++) {
            hand = pokerHands.getJSONObject(i).getJSONArray("cards");
            
        }

    }
}
