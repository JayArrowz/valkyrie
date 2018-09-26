package com.github.marceloaguiarr.valkyrie.profiles;

import java.security.AllPermission;
import java.security.PermissionCollection;
import java.security.Permissions;

/**
 * Default application profile. An application should have all permissions
 * and a more restrictive set of permissions for the plugins it runs.
 *
 * @author Marcelo Aguiar Rodrigues
 */
public class ApplicationSecurityProfile implements SecurityProfile {

    @Override
    public PermissionCollection getPermissions() {
        Permissions permissions = new Permissions();
        permissions.add(new AllPermission());
        return permissions;
    }

}
