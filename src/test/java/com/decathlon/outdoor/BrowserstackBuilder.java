package com.decathlon.outdoor;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;


public class BrowserstackBuilder {

    public static JSONObject config;
    public AndroidDriver<AndroidElement> driver;
    public DesiredCapabilities capabilities;

    private BrowserstackBuilder() {
        // utility class
    }

    public static AndroidDriver<AndroidElement> buildDriver(String testName) throws IOException {
        // Here we are setting the apk path for running the test locally


        //Instantiating the Desired capabilities inside the cap variable
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //capabilities.setCapability("app", "bs://3a1b606705756131c952051ade2666d44fd865a6");
        capabilities.setCapability("app", "bs://dc4b02200484eb7fa917510394bfd4ae4da65f0c");
        capabilities.setCapability("project", "Decathlon-outdoor - Junit test");
        capabilities.setCapability("build", "Decathlon-outdoor");
        capabilities.setCapability("name", testName);
        capabilities.setCapability("language", "fr");
        capabilities.setCapability("locale", "fr");
        capabilities.setCapability("browserstack.timezone", "Paris");
        // capabilities.setCapability("appActivity", "com.easymountain.quechua.ui.main.MainActivity");
        capabilities.setCapability("appPackage", "com.decathlon.quechuafinder");
        capabilities.setCapability("browserstack.gpsLocation", "45.210950267624284, 5.7913313153385415");

        JSONParser parser = new JSONParser();

        AndroidDriver driver = null;

        System.out.println(System.getProperty("user.dir"));

        try (FileReader reader = new FileReader(System.getProperty("user.dir") + "/" + "browserstack.json")) {
            //Read JSON file
            JSONObject config = (JSONObject) parser.parse(reader);

            JSONArray envs = (JSONArray) config.get("environments");

            Map<String, String> envCapabilities = (Map<String, String>) envs.get(0);
            Iterator it = envCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }

            String apkFileName = "app-release.apk";
            String apkPath = "apk/" + apkFileName;
            if (Objects.equals(System.getenv("BITRISE_APK_PATH"), "")) {
                apkPath = System.getenv("BITRISE_APK_PATH");
            }

            //Here we provide apk file to the app variable
            File app = new File(apkPath);
            // If the apk name is not present in the provided path show the exception message in terminal
            if (!app.exists() && System.getenv("BITRISE_APK_PATH") != null) {
                throw new IllegalStateException("the apk file was not found. Please add file " + apkPath + " in directory apk");
            } else {
               /*
                * What we want to achieve :
                *
                *   curl -u thibault_VDYvQE:zqkXz1VLpw7Bbm6V2oBc -X POST https://api-cloud.browserstack.com/app-automate/upload -F file=@apk/app-release.apk
                        {"app_url":"bs://3a1b606705756131c952051ade2666d44fd865a6"}
                * Fetch the value of app_url return by browserstack and inject in :
                * capabilities.setCapability("app", "bs://3a1b606705756131c952051ade2666d44fd865a6");
                *
                * And after
                * - Bitrise build the APK
                * - Upload to BrowserStack
                * - Able to use for tests
                */
                HttpEntity entity = MultipartEntityBuilder.create()
                        .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                        .addBinaryBody("file", app)
                        .build();

                HttpUriRequest request = RequestBuilder.post("https://api-cloud.browserstack.com/app-automate/upload")
                        .setEntity(entity).build();

                HttpResponse response = HttpClientBuilder.create().build()
                        .execute(request, buildRequestContext(config));

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 400) {
                    throw new IOException("request to browserstack API failed with response code " + statusCode
                            + " and reason "+ response.getStatusLine().getReasonPhrase());
                }
                String responseAsString = EntityUtils.toString(response.getEntity());
                JSONObject responseObject = (JSONObject) parser.parse(responseAsString);
                String appUrl = (String) responseObject.get("app_url");
                capabilities.setCapability("app", appUrl);
            }


            driver = new AndroidDriver(new URL("http://" + config.get("username") + ":" + config.get("access_key") + "@" + config.get("server") + "/wd/hub"), capabilities);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return driver;
        //return new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
    }

    private static HttpClientContext buildRequestContext(JSONObject config) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials(config.get("username").toString(), config.get("access_key").toString());
        credentialsProvider.setCredentials(AuthScope.ANY, credentials);

        AuthCache authCache = new BasicAuthCache();
        HttpHost targetHost = HttpHost.create("https://api-cloud.browserstack.com");
        authCache.put(targetHost, new BasicScheme());

        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);
        return context;
    }
}
