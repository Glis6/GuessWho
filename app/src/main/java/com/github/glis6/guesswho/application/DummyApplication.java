package com.github.glis6.guesswho.application;

import android.app.Application;

import com.github.glis6.guesswho.domain.CharacterLibrary;
import com.github.glis6.guesswho.domain.SimpleCharacterLibrary;
import com.github.glis6.guesswho.services.ServiceManager;
import com.github.glis6.guesswho.services.ServiceManagerProvider;

/**
 * @author Glis
 */
public class DummyApplication extends Application implements ServiceManagerProvider {
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
            initializeServiceManager();
        }
        return serviceManager;
    }

    /**
     * Initializes the connection. We're using lazy loading for this.
     */
    public void initializeServiceManager() {
        serviceManager = new ServiceManager() {
            @Override
            public CharacterLibrary getCharacterLibrary() {
                return new SimpleCharacterLibrary();
            }
        };
    }
}