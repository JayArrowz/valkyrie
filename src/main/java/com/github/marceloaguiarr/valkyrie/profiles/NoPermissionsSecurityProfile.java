package com.github.marceloaguiarr.valkyrie.profiles;

import java.security.PermissionCollection;
import java.security.Permissions;

/**
 * SecurityProfile with no permissions.
 *
 * @author Marcelo Aguiar Rodrigues
 */
public class NoPermissionsSecurityProfile implements SecurityProfile {

    @Override
    public PermissionCollection getPermissions() {
        return new Permissions();
    }

}
