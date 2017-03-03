package com.example.mihai.bgssimulator.Simultor;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.mihai.bgssimulator.Utils.AbsValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.currentTimeMillis;


/**
 * Created by silviu on 21.02.2017.
 */

public class FileSensorLog {

    private static String TAG = "FileSensorLog";
    private static FileOutputStream fileStepCounter, fileOrientation, fileAltitude, fileGPS, fileActivity;

    public static void createFiles(Context ctx) {
        boolean exist = false;
        String dirPath = ctx.getExternalFilesDir(null).getAbsolutePath() + File.separator + "SensorLog";
        File projDir = new File(dirPath);
        if (!projDir.exists()) {
            projDir.mkdirs();
            exist = false;
        } else {
            if (projDir.list().length != 0) {
                exist = true;
            }
        }
        try {
            if (!exist) {
                fileStepCounter = new FileOutputStream(new File(dirPath, AbsValues.fileStepName));
                fileOrientation = new FileOutputStream(new File(dirPath, AbsValues.fileOrientationName));
                fileAltitude=new FileOutputStream(new File(dirPath, AbsValues.fileAltitudeName));
                fileGPS = new FileOutputStream(new File(dirPath, AbsValues.fileGPSName));
                fileActivity = new FileOutputStream(new File(dirPath, AbsValues.fileActivityName));
            } else {
                fileStepCounter = new FileOutputStream(new File(dirPath, AbsValues.fileStepName), true);
                fileOrientation = new FileOutputStream(new File(dirPath, AbsValues.fileOrientationName), true);
                fileAltitude = new FileOutputStream(new File(dirPath, AbsValues.fileAltitudeName), true);
                fileGPS = new FileOutputStream(new File(dirPath, AbsValues.fileGPSName), true);
                fileActivity = new FileOutputStream(new File(dirPath, AbsValues.fileActivityName), true);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeFistTimeStamp() {
        String timeStamp = String.valueOf(System.currentTimeMillis()) + "$\n";
        try {
            fileStepCounter.write(timeStamp.getBytes());
            fileOrientation.write(timeStamp.getBytes());
            fileGPS.write(timeStamp.getBytes());
            fileAltitude.write(timeStamp.getBytes());
            fileActivity.write(timeStamp.getBytes());
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }


    public static void writeToStepCounterFile(int stepNumber) {
        long currentTime = currentTimeMillis();
        String stringToWrite = currentTime + "|" + stepNumber + "$" + "\n";
        try {
            fileStepCounter.write(stringToWrite.getBytes());
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public static void writeToOrientationFile(double angle) {
        long currentTime = currentTimeMillis();
        String stringToWrite = currentTime + "|" + angle + "$" + "\n";
        try {
            fileOrientation.write(stringToWrite.getBytes());
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public static void writeToAltitudeFile(double altitude) {
        long currentTime = currentTimeMillis();
        String stringToWrite = currentTime + "|" + altitude + "$" + "\n";
        try {
            fileAltitude.write(stringToWrite.getBytes());
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public static void writeToActivityFile(String activityType) {
        long currentTime = currentTimeMillis();
        String stringToWrite = currentTime + "|" + activityType + "$" + "\n";
        try {
            fileActivity.write(stringToWrite.getBytes());
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public static void writeToGPSFile(Location location) {
        long currentTime = currentTimeMillis();
        String stringToWrite = currentTime + "|" + location.getLatitude() + "|" + location.getLongitude()
                + "|" + location.getAltitude() + "|" + location.getAccuracy() + "$" + "\n";
        try {
            fileGPS.write(stringToWrite.getBytes());
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public static String getStepSensorFilePath(Context ctx) {
        return ctx.getExternalFilesDir(null).getAbsolutePath() + File.separator + "SensorLog" + File.separator + "step_log.txt";
    }

    public static String getOrientationSensorFilePath(Context ctx) {
        return ctx.getExternalFilesDir(null).getAbsolutePath() + File.separator + "SensorLog" + File.separator + "orientation_log.txt";
    }


    public static String readFileContent(Context context, String fileName) {
        String aux = "";
        final String dirPath = context.getExternalFilesDir(null).getAbsolutePath() + File.separator;
        try {
            FileInputStream fileInputStream = new FileInputStream(dirPath + File.separator + "SensorLog" + File.separator + fileName);
            StringBuffer fileContent = new StringBuffer("");
            byte[] buffer = new byte[1024];
            int n;
            try {
                while ((n = fileInputStream.read(buffer)) != -1) {
                    fileContent.append(new String(buffer, 0, n));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            aux = fileContent.toString().trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return aux;
    }

    /**
     * closes the file opened for input
     */
    public static void closeFiles() {
        try {
            fileStepCounter.close();
            fileOrientation.close();
            fileAltitude.close();
            fileGPS.close();
            fileActivity.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}