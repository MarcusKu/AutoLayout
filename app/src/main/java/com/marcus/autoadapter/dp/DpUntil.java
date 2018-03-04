package com.marcus.autoadapter.dp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;

/**
 * Created by Marcus on 2018/3/4.
 */

public class DpUntil {

    private float DEFAULT_DP = 360.0f;

    private int MODEL_DPS[] = {360, 384, 392, 400, 410, 411, 480, 533, 592, 600, 640, 662, 720, 800, 811, 820, 960, 961, 1024, 1280, 1365};

    private String FolderName = "values-sw%dp";

    private String dimen_head = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<resources>\n";
    private String dimen_footer = "</resources>";
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private int m_dp[] = {40, 50, 60};
    private double p_dp[] = {0.1, 0.5};

    public void start() {
        for (int modelDp : MODEL_DPS) {
            start(modelDp);
        }
    }

    public void start(int modelDp) {
        float ratio = calculateRatio(modelDp);
        StringBuilder builder = allotDp(ratio);
        writeDimens(modelDp, builder);
    }

    public float calculateRatio(int modelDp) {
        return modelDp / DEFAULT_DP;
    }

    public StringBuilder allotDp(float ratio) {
        StringBuilder builder = new StringBuilder();
        builder.append(dimen_head);

        for (int i = m_dp.length - 1; i >= 0; i--) {
            String line = "    <dimen name = \"dp_m_" + m_dp[i] + "\">" + decimalFormat.format(-m_dp[i] * ratio) + "dp</dimen>\n";
            builder.append(line);
            System.out.println(line);
        }

        for (int i = 30; i > 0; i--) {
            String line = "    <dimen name = \"dp_m_" + i + "\">" + decimalFormat.format(-i * ratio) + "dp</dimen>\n";
            builder.append(line);
            System.out.println(line);
        }

        builder.append("\r");

        String name[] = {"1", "5"};

        for (int i = 0; i < p_dp.length; i++) {
            String line = "    <dimen name = \"dp_0_" + name[i] + "\">" + decimalFormat.format(p_dp[i] * ratio) + "dp</dimen>\n";
            builder.append(line);
            System.out.println(line);
        }

        builder.append("\r");

        for (int i = 0; i <= 720; i++) {
            String line = "    <dimen name = \"dp_" + i + "\">" + decimalFormat.format(i * ratio) + "dp</dimen>\n";
            builder.append(line);
            System.out.println(line);
        }
        builder.append("\r");
        for (int i = 1; i <= 50; i++) {
            String line = "    <dimen name = \"sp_" + i + "\">" + decimalFormat.format(i * ratio) + "sp</dimen>\n";
            builder.append(line);
            System.out.println(line);
        }
        return builder.append(dimen_footer);
    }

    public void writeDimens(int modelDp, StringBuilder builder) {
        try {
            String fileName = null;
            if (modelDp == 360) {
                fileName = FolderName.replace("-sw%dp", "");
            } else {
                fileName = FolderName.replace("%", String.valueOf(modelDp));
            }
            String resFolderPath = "E:/Projects/Android/autoAdapter/app/src/main/res" + "/" + fileName;
            File folder = new File(resFolderPath);
            if (!folder.exists()) {
                folder.mkdir();
            } else {
                folder.delete();
                folder.mkdir();
            }

            String dimenFile = resFolderPath + "/" + "dimens.xml";
            File dimenFiles = new File(dimenFile);
            if (!dimenFiles.exists()) {
                dimenFiles.createNewFile();
            } else {
                dimenFiles.delete();
                dimenFiles.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(dimenFiles));
            bw.write(builder.toString());
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
