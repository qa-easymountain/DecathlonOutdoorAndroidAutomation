package com.decathlon.outdoor;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;


public class AndroidDriverBuilder {

    public static JSONObject config;
    public AndroidDriver<AndroidElement> driver;
    public DesiredCapabilities capabilities;

    private AndroidDriverBuilder() {
        // utility class
    }

    public static AndroidDriver<AndroidElement> buildDriver(String testname) throws IOException {
        // Here we are setting the apk path for running the test locally


        //Instantiating the Desired capabilities inside the cap variable
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //here we set our android emulator

        /*

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "PoojaEmulatorAppDebug");
        //here we set our capability type
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("platform", "Android");


        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        if(apkFileName == "app-alpha.apk") {
            cap.setCapability("appPackage", "com.decathlon.quechuafinder.alpha");
        } else if(apkFileName == "app-debug.apk") {
            cap.setCapability("appPackage", "com.decathlon.quechuafinder.debug");
        } else {
            cap.setCapability("appPackage", "com.decathlon.quechuafinder");
        }
        cap.setCapability("appActivity", "com.easymountain.quechua.ui.main.MainActivity");

        */
        capabilities.setCapability("app", "bs://3a1b606705756131c952051ade2666d44fd865a6");
        capabilities.setCapability("project", "Decathlon-outdoor - Junit test");
        capabilities.setCapability("build", "Decathlon-outdoor");
        capabilities.setCapability("name", testname);
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
                HttpEntity entity = MultipartEntityBuilder.create().addPart("file", new FileBody(app)).build();
                CredentialsProvider provider = new BasicCredentialsProvider();
                UsernamePasswordCredentials credentials
                        = new UsernamePasswordCredentials(config.get("username").toString(), config.get("access_key").toString());
                provider.setCredentials(AuthScope.ANY, credentials);

                HttpPost request = new HttpPost("https://api-cloud.browserstack.com/app-automate/upload");
                request.setEntity(entity);

                HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
                HttpResponse response = client.execute(request);

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
}
