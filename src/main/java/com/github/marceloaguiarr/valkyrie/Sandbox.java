package com.github.marceloaguiarr.valkyrie;

import com.github.marceloaguiarr.valkyrie.profiles.ApplicationSecurityProfile;
import com.github.marceloaguiarr.valkyrie.profiles.SecurityProfile;

import java.security.PermissionCollection;
import java.security.Policy;
import java.security.ProtectionDomain;

/**
 * Custom sandbox policy.
 *
 * @author Marcelo Aguiar Rodrigues
 */
final class Sandbox extends Policy {

    @Override
    public PermissionCollection getPermissions(ProtectionDomain domain) {

        if (isPlugin(domain)) {

            return pluginPermissions(domain);
        } else {

            return applicationPermissions();
        }
    }

    /**
     * Checks if the domain classLoader is one of the class loaders set as
     * a plugin classloader.
     *
     * @param domain {@link ProtectionDomain}
     * @return true if the classloader is set to an plugin
     */
    private boolean isPlugin(ProtectionDomain domain) {

        return ProfileManager.isPlugin(domain.getClassLoader());
    }

    /**
     * Returns the plugin set of permissions.
     *
     * @param domain {@link ProtectionDomain}
     * @return PermissionCollection with plugin permissions
     */
    private PermissionCollection pluginPermissions(ProtectionDomain domain) {

        SecurityProfile p = ProfileManager.getProfile(domain.getClassLoader());

        return p.getPermissions();
    }

    /**
     * Sets the application permissions to full control
     *
     * @return PermissionCollection with {@link java.security.AllPermission}
     */
    private PermissionCollection applicationPermissions() {

        return new ApplicationSecurityProfile().getPermissions();
    }
}
