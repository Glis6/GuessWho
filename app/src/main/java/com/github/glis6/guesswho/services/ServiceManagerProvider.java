package com.github.glis6.guesswho.services;

/**
 * @author Glis
 */
public interface ServiceManagerProvider {
    /**
     * @return The {@link ServiceManager} active for this application.
     */
    ServiceManager getServiceManager();
}
