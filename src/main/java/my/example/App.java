package my.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!!");
        App a = new App();
        a.listBuckets();
    }

    private void listBuckets() {
        try {
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new FileInputStream(
                            "../auth.json"))
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));

            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            // Storage storage =
            // StorageOptions.newBuilder().setProjectId("psyched-bruin-281217").build().getService();
            Page<Bucket> buckets = storage.list();

            for (Bucket bucket : buckets.iterateAll()) {
                System.out.println(bucket.getName() + " is located at " + bucket.getLocation());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
