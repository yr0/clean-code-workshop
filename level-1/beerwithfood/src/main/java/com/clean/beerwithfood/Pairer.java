package com.clean.beerwithfood;

import java.util.*;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

class Pairer {
  // Command to be executed
  String c;

  private InputStream getStreamFromProcess(Process p) {
    return p.getInputStream();
  }

  //###
  // exec - provided a name of food, will return a beer that can be paired with it
  // @param f - name of the food
  // @returns String - description of the beer that can be paired with the food
  //###
  public String exec(String f) throws IOException {
    this.c = "curl https://api.punkapi.com/v2/beers?food=" + URLEncoder.encode(f, StandardCharsets.UTF_8.toString()) + "&per_page=1";
    Process p = execCommandLikeInTerminal();
    String response = fetchResponseFromProcess(p);
    Map<String, Object>[] result = new ObjectMapper().readValue(response, HashMap[].class);
    // System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result));

    return f + " can be paired with " + result[0].get("name") + ": " + result[0].get("tagline");
  }

  private String fetchResponseFromProcess(Process p) throws IOException {
    InputStream istr = getStreamFromProcess(p);
    return IOUtils.toString(istr, StandardCharsets.UTF_8);
  }

  // performs string command as if it were executed in the shell
  private Process execCommandLikeInTerminal() throws IOException {
    return Runtime.getRuntime().exec(this.c);
  }
}
