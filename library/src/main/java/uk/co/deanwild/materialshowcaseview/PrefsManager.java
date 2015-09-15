package uk.co.deanwild.materialshowcaseview;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by deanwild on 11/08/15.
 */
public class PrefsManager {

    public static int SEQUENCE_NEVER_STARTED = 0;
    public static int SEQUENCE_FINISHED = -1;


    private static final String PREFS_NAME = "material_showcaseview_prefs";
    private static final String STATUS = "status_";
    private String showcaseID = null;
    private Context context;

    public PrefsManager(Context context) {
        this.context = context;
    }

    /***
     * @deprecated Pass the showcaseID when needed
     */
    @Deprecated
    public PrefsManager(Context context, String showcaseID) {
        this.context = context;
        this.showcaseID = showcaseID;
    }


    /***
     * @deprecated send the showcaseID via param
     */
    @Deprecated
    boolean hasFired() {
        int status = getSequenceStatus();
        return (status == SEQUENCE_FINISHED);
    }

    boolean hasFired(String showcaseID) {
        int status = getSequenceStatus(showcaseID);
        return (status == SEQUENCE_FINISHED);
    }

    /***
     * @deprecated send the showcaseID via param
     */
    @Deprecated
    void setFired() {
        setSequenceStatus(SEQUENCE_FINISHED);
    }

    void setFired(String showcaseID) {
        setSequenceStatus(SEQUENCE_FINISHED, showcaseID);
    }

    /***
     * @deprecated send the showcaseID via param
     */
    @Deprecated
    int getSequenceStatus() {
        return context
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt(STATUS + showcaseID, SEQUENCE_NEVER_STARTED);

    }

    int getSequenceStatus(String showcaseID) {
        return context
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt(STATUS + showcaseID, SEQUENCE_NEVER_STARTED);

    }

    /***
     * @deprecated send the showcaseID via param
     */
    @Deprecated
    void setSequenceStatus(int status) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().putInt(STATUS + showcaseID, status).apply();
    }

    void setSequenceStatus(int status, String showcaseID) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().putInt(STATUS + showcaseID, status).apply();
    }

    /***
     * @deprecated send the showcaseID via param
     */
    @Deprecated
    public void resetShowcase() {
        resetShowcase(context, showcaseID);
    }

    public void resetShowcase(String showcaseID) {
        resetShowcase(context, showcaseID);
    }

    static void resetShowcase(Context context, String showcaseID) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().putInt(STATUS + showcaseID, SEQUENCE_NEVER_STARTED).apply();
    }

    public static void resetAll(Context context) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().clear().apply();
    }

    public void close() {
        context = null;
    }
}
