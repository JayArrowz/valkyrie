package com.github.marceloaguiarr.valkyrie.enums;

import com.github.marceloaguiarr.valkyrie.managers.ThreadSecurityManager;

/**
 * List of Security Managers available for Valkyrie.
 *
 * @author Marcelo Aguiar Rodrigues
 */
public enum SecurityManagers {

    /**
     * Default security manager provided by the Java Api.
     */
    DEFAULT(new SecurityManager()),

    /**
     * Custom security manager that prevents the plugins from creating
     * new threads.
     */
    NO_THREADS(new ThreadSecurityManager());

    private SecurityManager securityManager;

    SecurityManagers(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    public SecurityManager getSecurityManager() {
        return securityManager;
    }
}
