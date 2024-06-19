import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] baseCurrencies = {"USD", "EUR", "GBP", "JPY", "CNY"};
        String[] targetCurrencies = {"USD", "EUR", "GBP", "JPY", "CNY"};

        System.out.println("Available base currencies:");
        for (int i = 0; i < baseCurrencies.length; i++) {
            System.out.println((i + 1) + ". " + baseCurrencies[i]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of your chosen base currency: ");
        int baseCurrencyIndex = scanner.nextInt() - 1;
        String baseCurrency = baseCurrencies[baseCurrencyIndex];

        System.out.println("Available target currencies:");
        for (int i = 0; i < targetCurrencies.length; i++) {
            System.out.println((i + 1) + ". " + targetCurrencies[i]);
        }

        System.out.print("Enter the number of your chosen target currency: ");
        int targetCurrencyIndex = scanner.nextInt() - 1;
        String targetCurrency = targetCurrencies[targetCurrencyIndex];

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        String url = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        // Parse the JSON response
        int start = responseBody.indexOf("{");
        int end = responseBody.lastIndexOf("}");
        String json = responseBody.substring(start, end + 1);

        // Extract the exchange rate
        int startRate = json.indexOf("rates") + 7;
        int endRate = json.indexOf(",", startRate);
        String rateString = json.substring(startRate, endRate);
        double rate = Double.parseDouble(rateString.split(":")[1].trim().replace("{", "").replace("}", "").replace("\"", ""));

        double convertedAmount = amount * rate;

        System.out.println(amount + " " + baseCurrency + " is equal to " + convertedAmount + " " + targetCurrency);
    }
}