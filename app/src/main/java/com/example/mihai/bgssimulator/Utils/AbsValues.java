package com.example.mihai.bgssimulator.Utils;

/**
 * Created by mihai on 02.03.2017.
 */

public class AbsValues {

    /**
     * sensor file names
     */
    public static final String fileStepName = "step_log.txt";
    public static final String fileOrientationName = "orientation_log.txt";
    public static final String fileAltitudeName = "altitude_log.txt";
    public static final String fileGPSName = "gps_log.txt";
    public static final String fileActivityName = "activity_log.txt";

    /**gps localization params*/
    public static final Integer GPS_REQUEST_INTERVAL_DEFAULT = 5000;
    public static final Integer GPS_REQUEST_FASTEST_INTERVAL_DEFAULT = 5000;
    public static final Integer GPS_REQUEST_INTERVAL_INCREASED = 3000;
    public static final Integer GPS_REQUEST_FASTEST_INTERVAL_INCREASED = 2000;


    /**gather states*/
    public static final String COLLECT_DATA = "collect";
    public static final String STOP_COLLECT = "stop_collect";

}
