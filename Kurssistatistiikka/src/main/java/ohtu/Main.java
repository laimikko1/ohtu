package ohtu;


import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "011120775";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("Opiskelijanumero: + "  + studentNr);

        System.out.println("Oliot:");
        for (Submission submission : subs) {
            System.out.println(submission);
        }

    }

}

