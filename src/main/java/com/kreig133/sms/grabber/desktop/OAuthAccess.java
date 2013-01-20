package com.kreig133.sms.grabber.desktop;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.http.HttpRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author kreig133
 * @version 1.0
 */
public class OAuthAccess {
    public static void main( String[] args ) throws Throwable{
        OAuthConsumer consumer = new DefaultOAuthConsumer( "g18d3fe09ebac4178ca0be2043ac31", "12b473ecfa" );
        OAuthProvider provider = new DefaultOAuthProvider(
                "http://api.zenmoney.ru/oauth/request_token",
                "http://api.zenmoney.ru/oauth/access_token",
                "http://api.zenmoney.ru/access/" );

        String authUrl = provider.retrieveRequestToken(consumer, "http://kreig133.com");

//        System.out.println("Request token: " + consumer.getToken());
//        System.out.println("Token secret: " + consumer.getTokenSecret());

        System.out.println("Now visit:\n" + authUrl + "\n... and grant this app authorization");
        System.out.println("Enter the PIN code and hit ENTER when you're done:");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pin = br.readLine();

        System.out.println("Fetching access token from Twitter...");

        provider.retrieveAccessToken(consumer, pin);

        System.out.println("Access token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());

        URL url = new URL("http://api.zenmoney.ru/v1/transaction/");
        HttpURLConnection request = (HttpURLConnection) url.openConnection();

        HttpRequest sign = consumer.sign( request );

        System.out.println("Sending request to Twitter...");
        request.connect();

        System.out.println("Response: " + request.getResponseCode() + " " + request.getResponseMessage());

    }
}
