package com.github.glis6.guesswho.application;

import android.app.Application;

import com.github.glis6.guesswho.persistency.LazySQLiteServiceManager;
import com.github.glis6.guesswho.services.ServiceManager;
import com.github.glis6.guesswho.services.ServiceManagerProvider;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author Glis
 */
public class SQLiteApplication extends Application implements ServiceManagerProvider {
    /**
     * The {@link ServiceManager} to use for the application.
     */
    private ServiceManager serviceManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceManager getServiceManager() {
        if(serviceManager == null) {
            serviceManager = new LazySQLiteServiceManager(getApplicationContext());
        }
        return serviceManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}