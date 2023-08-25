package de.emir.rcp.parts.vesseleditor.utils;

import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;


public class MIDManager {

    private static MIDManager instance;
    private String filePath = "mids.csv";
    private HashMap<String, String> midCC = new HashMap<>();
    private HashMap<String, String> cnameMid = new HashMap<>();
    private HashMap<String, String> midCName = new HashMap<>();
    private String[] countryNames;

    private MIDManager() {
        URL url;
        try {
            InputStream inputStream = ResourceManager.get(MIDManager.class).getInputStream("mids.csv");
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while (line != null) {
                line = bufRead.readLine();
                if (line != null && !line.equals("")) {

                    String[] lineParts = line.split(",");

                    if (lineParts.length < 5) {
                        continue;
                    }

                    String mid = lineParts[0];
                    String cc = lineParts[1];
                    String cname = lineParts[4];
                    midCC.put(mid, cc);
                    cnameMid.put(cname, mid);
                    midCName.put(mid, cname);
                    countryNames = cnameMid.keySet().toArray(new String[0]);
                    Arrays.sort(countryNames);
                }
            }
            bufRead.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static MIDManager getInstance() {
        if (instance == null) {
            instance = new MIDManager();
        }

        return instance;
    }

    public int getCountryIndex(String country) {
        for (int i = 0; i < countryNames.length; i++) {
            if (countryNames[i].equals(country)) {
                return i;
            }
        }

        return -1;
    }

    public String[] getCountries() {
        return countryNames;
    }

    public String getMid(String countryName) {
        return cnameMid.get(countryName);
    }

    public Image getImage(String mid, int size) {

        if (mid.length() > 3) {
            mid = mid.substring(0, 3);
        }

        String cc = midCC.get(mid);
        if (cc != null) {
            return IconManager.getScaledImage(this, "icons/flags/" + cc.toLowerCase() + ".png", size);
        } else {
            return IconManager.getScaledImage(this, "icons/pirat.png", size);
        }
    }

    public ImageIcon getIcon(String mid, int size) {
        Image img = getImage(mid, size);
        if (img == null)
            return null;
        return new ImageIcon(img);
    }

    public String getCountry(String mid) {
        return midCName.get(mid);

    }

}
