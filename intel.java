/*
@Author: Eeshan
Version 1.1
Launch Date - 21st January, 2026.
Please, use this judiciously, and have fun (: 
*/

import java.util.*;
import java.util.concurrent.*;
import static java.util.Map.entry;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class intel {

    //declaring colors as constants
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        //creating our map
        Map<String, String> m = new HashMap<>(
                Map.ofEntries(
                        entry("Github", "https://github.com/"),
                        entry("Instagram", "https://www.instagram.com/"),
                        entry("Twitter", "https://x.com/"),
                        entry("Pinterest", "https://www.pinterest.com/"),
                        entry("Amazon", "url"),
                        entry("Spotify", "https://www.spotify.com/in-en/"),
                        entry("office365", "https://www.microsoft.com/en-in/"),
                        entry("Snapchat", "https://www.snapchat.com/"),
                        entry("Discord", "https://discord.com/"),
                        entry("Duolingo", "https://www.duolingo.com/")));

        // Get the target username from the user.
        System.out.println("Enter target username");
        String target_username = sc.next();

        // Set Up the "Worker Pool" (The Multithreading Part):
        ExecutorService esob = Executors.newFixedThreadPool(10);

        // making the client once for multiple uses later in the loop
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                // addiing timeout so it doesnt hang
                .connectTimeout(java.time.Duration.ofSeconds(5))
                // .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
                // .authenticator(Authenticator.getDefault())
                .build();

        // looping through the Map
        for (Map.Entry<String, String> entry : m.entrySet()) {
            //creating a finalized url variable 
            String finalUrl = entry.getValue() + target_username;
            // doing stuff with the pool esob
            try {
                // submitting a task, viz, submitting a request, to get status code
                esob.submit(() -> {
                    // using HTTPreq to set the uri and specifying method to HEAD, adding header
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(finalUrl))
                            .method("HEAD", HttpRequest.BodyPublishers.noBody())
                            .header("User-Agent",
                                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                            .build();

                    // sending the request to the server to get a response, viz,
                    // response.statusCode()
                    HttpResponse<Void> response;
                    try {
                        response = client.send(request, HttpResponse.BodyHandlers.discarding());
                        // getting the status code
                        switch(response.statusCode()){
                            case 301: System.out.print("Status Code: " + response.statusCode() + " ("+entry.getKey()+")"); 
                                    System.out.println(" being redirected "); break; 
                                    
                            case 302: System.out.print(YELLOW + "Status Code: " + response.statusCode() + " ("+entry.getKey()+")" + RESET);
                                    System.out.println(YELLOW + " being redirected " + RESET); break; 

                            case 403: System.out.print(YELLOW + "Status Code: " + response.statusCode() + " ("+entry.getKey()+")" + RESET);
                                    System.out.println(YELLOW + " blocked " + RESET); break;

                            case 404: System.out.print(RED + "Status Code: " + response.statusCode() + " ("+entry.getKey()+")" + RESET);
                                    System.out.println(RED + " NOT FOUND " + RESET); break;

                            case 200: System.out.print(GREEN + "Status Code: " + response.statusCode() + " ("+entry.getKey()+")" + RESET);
                                    System.out.println(GREEN + " FOUND " + RESET); break;

                            default: System.out.print("Status Code: " + response.statusCode() + " ("+entry.getKey()+")");
                                    System.out.print(' '); break;
                        }
                    } catch (IOException | InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                });

            } catch (Exception e) {
                System.err.println("Request failed: " + e.getMessage());
            } 
        }
        esob.shutdown();
    }
}
