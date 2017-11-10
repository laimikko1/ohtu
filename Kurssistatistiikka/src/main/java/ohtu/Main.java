package ohtu;


import com.google.gson.*;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Anna opiskelijanumerosi: ");
        String studentNr = lukija.nextLine();

        if (args.length > 0) {
            studentNr = args[0];
        }
        String statsUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";
        String kurssitiedot = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String kurssiBody = Request.Get(kurssitiedot).execute().returnContent().asString();
        String statsResponse = Request.Get(statsUrl).execute().returnContent().asString();

        JsonParser parse = new JsonParser();

        JsonObject parsittuData = parse.parse(statsResponse).getAsJsonObject();
        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Kurssi kurssi = mapper.fromJson(kurssiBody, Kurssi.class);

        for (Map.Entry<String, JsonElement> e : parsittuData.entrySet()) {
            JsonElement json = e.getValue();
            kurssi.addWeeklyreturn(mapper.fromJson(json, WeeklyReturn.class));
        }


        System.out.println("Kurssi: " + kurssi.toString());
        System.out.println("Opiskelijanumero: + " + studentNr);

        System.out.println("Oliot:");
        for (Submission submission : subs) {
            System.out.println(submission);
            System.out.println("Viikon tehtävämäärä oli: " + kurssi.getWeeklyMax(submission.getWeek()));
        }
        System.out.println("Kurssilla yhteensä: " + kurssi.getAllSubmissions() + " palautusta. Tehtyjä tehtäviä yhteensä: " + kurssi.getAllDoneExercises());
    }

}

