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

import com.github.marceloaguiarr.valkyrie.profiles.ApplicationSecurityProfile;
import com.github.marceloaguiarr.valkyrie.profiles.SecurityProfile;

import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.util.Set;

/**
 * Custom sandbox policy.
 *
 * @author Marcelo Aguiar Rodrigues
 */
final class Sandbox extends Policy {

    private Set<CodeSource> managerApplications;

    public Sandbox(Set<CodeSource> managerApplications) {
        this.managerApplications = managerApplications;
    }

    public Sandbox() {
        this.managerApplications = null;
    }


    @Override
    public PermissionCollection getPermissions(ProtectionDomain domain) {

        if (this.managerApplications != null) {
            if (this.managerApplications.stream()
                    .anyMatch(managerApplication -> managerApplication.implies(domain.getCodeSource()))) {

                return applicationPermissions();
            }
        }

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
