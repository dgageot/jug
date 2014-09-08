package jug;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.SecurityUtils;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.model.TableDataInsertAllRequest;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

import static com.google.api.services.bigquery.BigqueryScopes.BIGQUERY;
import static com.google.api.services.bigquery.model.TableDataInsertAllRequest.Rows;
import static com.google.common.base.Throwables.propagate;
import static java.util.Arrays.asList;

public class Logs {
  private static final String SERVICE_ACCOUNT = "599202762438-96btemioa19vm8jromonuqebflgdld18@developer.gserviceaccount.com";
  private static final String GOOGLE_SERVICE_ACCOUNT_PRIVATE_KEY = "/google-service-account.p12";
  private static final String STORE_PASS = "notasecret";
  private static final String ALIAS = "privatekey";
  private static final String KEY_PASS = "notasecret";

  private final Supplier<Bigquery> bigquery;

  public Logs() {
    bigquery = Suppliers.memoize(this::createDrive);
  }

  private Bigquery createDrive() {
    NetHttpTransport transport = new NetHttpTransport();
    JacksonFactory jsonFactory = new JacksonFactory();

    GoogleCredential credentials = new GoogleCredential.Builder()
        .setTransport(transport)
        .setJsonFactory(jsonFactory)
        .setServiceAccountId(SERVICE_ACCOUNT)
        .setServiceAccountScopes(asList(BIGQUERY))
        .setServiceAccountPrivateKey(readPrivateKey(GOOGLE_SERVICE_ACCOUNT_PRIVATE_KEY))
        .build();

    return new Bigquery.Builder(transport, jsonFactory, credentials).setApplicationName("JUG").build();
  }

  private static PrivateKey readPrivateKey(String name) {
    try (InputStream input = Logs.class.getResource(name).openStream()) {
      return SecurityUtils.loadPrivateKeyFromKeyStore(SecurityUtils.getPkcs12KeyStore(), input, STORE_PASS, ALIAS, KEY_PASS);
    } catch (IOException | GeneralSecurityException e) {
      throw propagate(e);
    }
  }

  public static void main(String[] args) {
    new Logs().insert("localhost", "blue");
  }

  public void insert(String hostname, String color) {
    try {
      Rows rows = new Rows().setJson(ImmutableMap.of("name", hostname, "color", color));

      TableDataInsertAllRequest content = new TableDataInsertAllRequest().setRows(asList(rows));

      bigquery.get().tabledata().insertAll("code-story-blog", "demo", "colors", content).execute();
    } catch (IOException e) {
      System.err.println("Unable to insert data into BigQuery");
    }
  }
}
