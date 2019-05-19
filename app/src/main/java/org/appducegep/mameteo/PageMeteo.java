package org.appducegep.mameteo;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.ImageView;
import android.text.method.LinkMovementMethod;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import java.util.Scanner;


public class PageMeteo extends AppCompatActivity {

    private JSONObject res;
    private int kp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_meteo);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        try {
            // build a URL
            String s = "https://services.swpc.noaa.gov/json/planetary_k_index_1m.json";
            URL url = new URL(s);

            // read from the URL
            Scanner scan = new Scanner(url.openStream());
            String str = new String();
            while (scan.hasNext())
                str += scan.nextLine();
            scan.close();

            // build a JSON object
            JSONArray obj = new JSONArray(str);

            // get the first result
            res = obj.getJSONObject(obj.length()-1);
            kp = res.getInt("kp_index");
            System.out.println(res.getString("time_tag"));
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
        }

        ImageView iv = (ImageView)this.findViewById(R.id.imageView);
        TextView tv1 = (TextView)this.findViewById(R.id.textView);
        TextView tv2 = (TextView)this.findViewById(R.id.textView2);
        TextView tv3 = (TextView)this.findViewById(R.id.textView3);

        String s = "Déterminez sous quelles latitudes vous vous trouvez à l'adresse suivante : <font color=\"black\"><a href=\"https://www.spaceweatherlive.com/en/help/the-low-middle-and-high-latitude\">https://www.spaceweatherlive.com/en/help/the-low-middle-and-high-latitude</a></font>";

        ((TextView) findViewById(R.id.textView3)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) findViewById(R.id.textView3)).setText(Html.fromHtml(s));

        System.out.println("" + kp);

        switch(kp) {
            case 1:
                iv.setImageResource(R.drawable.kp1);
                tv1.setText("Kp1 : Activité géomagnétique insuffisante");
                tv2.setText("Impossible d'observer des aurores sur les latitudes moyennes");
                break;
            case 2:
                iv.setImageResource(R.drawable.kp2);
                tv1.setText("Kp2 : Activité géomagnétique insuffisante");
                tv2.setText("Impossible d'observer des aurores sur les latitudes moyennes");
                break;
            case 3:
                iv.setImageResource(R.drawable.kp3);
                tv1.setText("Kp3 : Activité géomagnétique insuffisante");
                tv2.setText("Impossible d'observer des aurores sur les latitudes moyennes");
                break;
            case 4:
                iv.setImageResource(R.drawable.kp4);
                tv1.setText("Kp4 : Activité géomagnétique constatée");
                tv2.setText("Aurores éventuellement observables sur les latitudes moyennes selon les conditions météorologiques");
                break;
            case 5:
                iv.setImageResource(R.drawable.kp5);
                tv1.setText("Kp5 / G1 : Tempête géomagnétique mineure");
                tv2.setText("Aurores éventuellement observables sur les latitudes moyennes selon les conditions météorologiques");
                break;
            case 6:
                iv.setImageResource(R.drawable.kp6);
                tv1.setText("Kp6 / G2 : Tempête géomagnétique modérée");
                tv2.setText("Aurores éventuellement observables sur les latitudes moyennes selon les conditions météorologiques");
                break;
            case 7:
                iv.setImageResource(R.drawable.kp7);
                tv1.setText("Kp7 / G3 : Tempête géomagnétique forte");
                tv2.setText("Aurores éventuellement observables sur les latitudes moyennes selon les conditions météorologiques");
                break;
            case 8:
                iv.setImageResource(R.drawable.kp8);
                tv1.setText("Kp8 / G4 : Tempête géomagnétique sévère");
                tv2.setText("Aurores éventuellement observables sur les latitudes moyennes et basses selon les conditions météorologiques");
                break;
            case 9:
                iv.setImageResource(R.drawable.kp9);
                tv1.setText("Kp9 / G5 : Tempête géomagnétique extrême");
                tv2.setText("Aurores éventuellement observables sur les latitudes moyennes et basses selon les conditions météorologiques");
                break;
            default:
                tv1.setText("Kp0 : Activité géomagnétique insuffisante");
                tv2.setText("Impossible d'observer des aurores sur les latitudes moyennes");
                break;
        }
    }
}
