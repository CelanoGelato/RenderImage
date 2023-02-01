package com.render.rendermultiimage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputImagePath = "C:\\Tunglt\\group.png";
        String outputImagePath = "C:\\Tunglt\\background.png";
//        for (int i = 0; i < 20; i++) {
//            String width = randomNumber();
//            String height = randomNumber();
//            String codeStr = ("ffmpeg -i " + imagePath + " -vf scale=" + width + ":" + height + " background.png");
//            System.out.println(codeStr);
//            String[] code = codeStr.split(" ");
//            renderVideo(code);
//        }

        String width = randomNumber();
        String height = randomNumber();
        String pathFFmpeg = System.getProperty("user.dir") + "src\\main\\resources\\ffmpeg\\ffmpeg.exe";
        String codeStr = (pathFFmpeg + " -i " + inputImagePath + " -vf scale=" + width + ":" + height + " " + outputImagePath);
        System.out.println(codeStr);
//        renderVideo(codeStr.split(" "));
        Runtime.getRuntime().exec(codeStr.split(" "));
    }

    private static String randomNumber() {
        return String.valueOf((new Random().nextInt(1200) + 720));
    }

    public static void renderVideo(String[] cmd) {
        ProcessBuilder pb = new ProcessBuilder(cmd);
        try {
            Process process = pb.start();
            String s;
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuffer start = new StringBuffer();
            while ((s = stdInput.readLine()) != null) {
                start.append(s);
            }
            stdInput.close();
            while ((s = stdError.readLine()) != null) {
                start.append(s);
            }
            System.out.println(start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}