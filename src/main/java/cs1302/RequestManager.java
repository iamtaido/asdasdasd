package cs1302;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oracle.javafx.jmx.json.JSONException;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.function.Function;

public class RequestManager<T> {

    private String query = "";
    private Class<T> response;
    private Function<String, String> parseUrl;

    public RequestManager(Class<T> response, Function<String, String> parseUrl) {
        this.response = response;
        this.parseUrl = parseUrl;
    }

    public Optional<T> fetch(String query) throws IOException {
        if (!query.isEmpty() && !getQuery().equals(query)) {
            setQuery(query);
            System.out.println("Sending request to: " + parseUrl.apply(encode(getQuery())));
            InputStream is = new URL(parseUrl.apply(getQuery())).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                Gson gson = new Gson();
                return Optional.of(gson.fromJson(rd, response));
            } finally {
                is.close();
            }
        } else {
            return Optional.empty();
        }
    }

    public boolean newQuery(String query) {
        return !this.query.equalsIgnoreCase(query);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String encode(String query) throws UnsupportedEncodingException {
        return URLEncoder.encode(query, "UTF-8");
    }
}
