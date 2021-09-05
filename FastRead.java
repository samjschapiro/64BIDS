import java.util.*;
import java.util.function.*;

public class FastRead {
    public static void main(String[] args) {
        System.out.println(fastRead(500, 900, "{\"timestamp\":15,\"book\":\"TSLA\",\"bids\":[{\"price\":800,\"volume\":50},{\"price\":850,\"volume\":110}],\"asks\":[]}\""));
        /* {"timestamp":15,"book":"TSLA","bids":[{"price":800,"volume":50},{"price":850,"volume":110}],"asks":[]} */
        System.out.println(fastRead(700, 900, "{\"timestamp\":0,\"book\":\"AAPL\",\"bids\":[],\"asks\":[{\"price\":600,\"volume\":10}]}\""));
        System.out.println(fastRead(700, 900, "{\"timestamp\":0,\"book\":\"AAPL\",\"bids\":[],\"asks\":[]}\""));
    }
    
    public static String fastRead(double maxBuyPrice, double minSellPrice, String marketState) {
       
        /*Instantiate variables for things to keep track of*/
            String toReturn = "S13";
            int read = 0;
        
       	/*Stage 1 - Reading Timestamp*/
        	boolean isTimestamp = true;
        
        /*Stage 2 - Reading Book*/
        	boolean isBook = true;
        
        /*Stage 3 - Reading Bids*/
        	boolean isBids = true;
        	String bidPrice = "";
        
        /*Stage 4 - Reading Asks - does this when isBids == false*/
        	boolean isAsks = true;
        	String askPrice = "";
        
        /*Loop through*/
            for (int i = 13; i < marketState.length(); i++) {
                char currentChar = marketState.charAt(i);
                if (isTimestamp) {
                    read++;
                    if (currentChar == ',') { /*Reads timestamp value*/
                        toReturn += " R" + read + " S8"; /*Skips "book":"*/
                        i += 8;
                        isTimestamp = false;
                        read = 0;
                        continue;
                    }
                } else if (isBook) {
                    read++;
                    if (currentChar == '"') { /*Reads book*/
                        toReturn += " R" + read + " S9"; /*Skips ,"bids":[*/
                        i += 9;
                        isBook = false;
                        read = 0;
                        continue;
                    }
                } else if (isBids) {
                    char nextChar = marketState.charAt(i+1);
                    read++;
                    if (currentChar == '{') { /* If {, we know not empty and skip {"price": */
                        toReturn += " R" + read + " S8";
                        i += 8;
                        read = 0;
                        continue;
                    } else if (currentChar == ',') { /* If comma, we skip "volume": */
                        if (minSellPrice <= Double.parseDouble(bidPrice)) {
                           toReturn += " R" + read;
                           return toReturn;
                        }
                        toReturn += " R" + read + " S9";
                        bidPrice = "";
                        i += 9;
                        read = 0;
                        continue;
                    } else if (currentChar == '}' && nextChar == ',') { /*If  {, we skip next entry's "price": */
                        toReturn += " R" + (read + 1) + " S9";
                        bidPrice = "";
                        i += 10;
                        read = 0;
                        continue;
                    } else if (nextChar == ']') { /*Last condition*/
                    	isBids = false;
                        toReturn += " R" + (read + 1) + " S9";
                        i += 10;
                        read = 0;
                    	continue;
                    } else if (currentChar == ']') { /*Last condition if it's not empty*/
                        isBids = false;
                        toReturn += " R" + read + " S9";
                        i += 9;
                        read = 0;
                        continue;
                    } else {
                        /*Store value in variable*/
                        bidPrice += currentChar; 
                    }
                } else if (isAsks) {
                    char nextChar = marketState.charAt(i+1);
                    read++;
                    if (currentChar == '{') { /* If {, we know not empty and skip {"price": */
                        toReturn += " R" + read + " S8";
                        i += 8;
                        read = 0;
                        continue;
                    } else if (currentChar == ',') { /* If comma, we skip "volume": */
                        if (minSellPrice >= Double.parseDouble(askPrice)) {
                           toReturn += " R" + read;
                           return toReturn;
                        }
                        toReturn += " R" + read + " S9";
                        askPrice = "";
                        i += 9;
                        read = 0;
                        continue;
                    } else if (currentChar == '}' && nextChar == ',') { /*If  {, we skip next entry's "price": */
                        toReturn += " R" + (read + 1) + " S9";
                        askPrice = "";
                        i += 10;
                        read = 0;
                        continue;
                    } else if (currentChar == ']') { /*Last condition if it's not empty*/
                        isBids = false;
                        toReturn += " R" + read;
                        break;
                    }  else if (nextChar == ']') { /*Last condition*/
                    	isBids = false;
                        toReturn += " R" + (read+1);
                    	break;
                    }else {
                        /*Store value in variable*/
                        askPrice += currentChar; 
                    }
                }
            }
            return toReturn;
    }
}

