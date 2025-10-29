import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Hardcoded for testing one file only
        String fileName = "file2.json";
        System.out.println("Processing " + fileName + ":");

        Gson gson = new Gson();

        try {
            // Read JSON file
            String jsonContent = new String(Files.readAllBytes(Paths.get(fileName)));
            JsonObject rootObj = gson.fromJson(jsonContent, JsonObject.class);

            JsonObject keysObj = rootObj.getAsJsonObject("keys");
            int n = keysObj.get("n").getAsInt();
            // int k = keysObj.get("k").getAsInt(); // Not used for constant C

            // Collect roots by parsing base-value to BigInteger
            List<BigInteger> roots = new ArrayList<>();
            Set<String> keySet = rootObj.keySet();
            for (String key : keySet) {
                if (!key.equals("keys")) {
                    JsonObject item = rootObj.getAsJsonObject(key);
                    String baseStr = item.get("base").getAsString();
                    int base = Integer.parseInt(baseStr);
                    String valueStr = item.get("value").getAsString();
                    BigInteger val = new BigInteger(valueStr, base);
                    roots.add(val);
                }
            }

            // Compute product of roots using BigInteger
            BigInteger prod = BigInteger.ONE;
            for (BigInteger root : roots) {
                prod = prod.multiply(root);
            }

            // Constant C = (-1)^n * product
            BigInteger C;
            if (n % 2 == 0) {
                C = prod;
            } else {
                C = prod.negate();
            }

            // Output the constant C
            System.out.println("The constant C is: " + C);

        } catch (IOException e) {
            System.err.println("Error reading " + fileName + ": " + e.getMessage());
        }
    }
}