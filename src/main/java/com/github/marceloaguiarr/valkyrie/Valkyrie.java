/*
 * Copyright 2018 Marcelo Aguiar Rodrigues
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.marceloaguiarr.valkyrie;

import com.github.marceloaguiarr.valkyrie.enums.ErrorMessages;
import com.github.marceloaguiarr.valkyrie.enums.SecurityManagers;
import com.github.marceloaguiarr.valkyrie.profiles.SecurityProfile;

import java.net.URL;
import java.security.AccessController;
import java.security.Policy;
import java.security.PrivilegedAction;
import java.util.Set;

/**
 * Valkyrie Sandbox Library
 * <p>
 * Sandbox library for applications that run plugins. Provides a more comprehensive
 * way to setup a set of permissions for each classloader that runs a plugin inside
 * the main application while maintaining full permissions for the application itself.
 * </p>
 * <p>
 * With Valkyrie is possible to create a {@link SecurityProfile} that will be used for
 * your application plugins. Each {@link SecurityProfile} is associated with a {@link ClassLoader}.
 * </p>
 * <p>
 * {@link SecurityProfile} is a interface that must be implemented by the application
 * to set the {@link java.security.PermissionCollection} for the plugins.
 * There are two implementations in Valkyrie, one with no permissions at all and one
 * with all permissions, where the latter is used by default as the application set of
 * permissions.
 * </p>
 * <p>
 * {@link ClassLoader} can be any custom classloader that will run the plugins.
 * </p>
 *
 * @author Marcelo Aguiar Rodrigues
 */
public final class Valkyrie {

    private static Policy p;
    private static SecurityManager sm;

    /**
     * Initializes Valkyrie setting the desired Policy and SecurityManager
     * to the system.
     */
    public static void start() {

        if (sm == null) throw new NullPointerException(ErrorMessages.SECURITY_MANAGER_CANT_BE_NULL.get());
        if (p == null) throw new NullPointerException(ErrorMessages.POLICY_CANT_BE_NULL.get());

        Policy.setPolicy(p);
        System.setSecurityManager(sm);
    }

    /**
     * Sets the default java {@link SecurityManager} and the default Valkyrie {@link Policy}
     */
    public static void setSecurityManager() {
        setSecurityManager(SecurityManagers.DEFAULT.getSecurityManager(), new Sandbox());
    }

    /**
     * Sets a custom {@link SecurityManager} and the default Valkyrie {@link Policy}
     *
     * @param securityManager {@link SecurityManager}
     */
    public static void setSecurityManager(SecurityManager securityManager) {
        setSecurityManager(securityManager, new Sandbox());
    }

    /**
     * Sets one of the provided {@link SecurityManagers}
     *
     * @param securityManager {@link SecurityManagers}
     */
    public static void setSecurityManager(SecurityManagers securityManager) {
        setSecurityManager(securityManager.getSecurityManager(), new Sandbox());
    }

    /**
     * Sets one of the provided {@link SecurityManagers} and a custom {@link Policy}
     *
     * @param securityManager {@link SecurityManagers}
     * @param policy          {@link Policy}
     */
    public static void setSecurityManager(SecurityManagers securityManager, Policy policy) {
        setSecurityManager(securityManager.getSecurityManager(), policy);
    }

    /**
     * Sets a custom {@link SecurityManager} and a custom {@link Policy}
     *
     * @param securityManager {@link SecurityManagers}
     * @param policy          {@link Policy}
     */
    public static void setSecurityManager(SecurityManager securityManager, Policy policy) {
        p = policy;
        sm = securityManager;
    }

    public static void setSecurityManager(SecurityManagers securityManager, Set<URL> managerApplications) {
        setSecurityManager(securityManager.getSecurityManager(), new Sandbox(managerApplications));
    }

    public static void setSecurityManager(SecurityManager securityManager, Set<URL> managerApplications) {
        setSecurityManager(securityManager, new Sandbox(managerApplications));
    }

    /**
     * Adds a specified permission securityProfile to a classLoader
     *
     * @param classLoader     Plugin classloader
     * @param securityProfile Plugin security securityProfile
     */
    public static void addProfile(Class classLoader, SecurityProfile securityProfile) {

        ProfileManager.addProfile(classLoader, securityProfile);
    }

    /**
     * Runs a block of code with no restrictions. This is useful when the application has to run
     * some logic that requires access that is being blocked by the SecurityManager.
     * <p>
     * In order to do that this method receives a {@link PrivilegedAction} function.
     * <p>
     * Usage:
     * <pre>
     *   Valkyrie.doPrivileged(() -> {
     *       // privileged code here
     *       return null;
     *   });
     * </pre>
     * </p>
     *
     * @param doPrivileged {@link PrivilegedAction}
     */
    public static <T> T doPrivileged(PrivilegedAction<T> doPrivileged) {
        return AccessController.doPrivileged(doPrivileged);
    }

}
