package com.github.marceloaguiarr.valkyrie.profiles;

import java.security.PermissionCollection;
import java.security.Permissions;

/**
 * A SecurityProfile is a set of permissions that a specific classloader has.
 * <p>
 * A class that implements this interface and wishes to have a set
 * of permissions should create a constructor that sets a {@link Permissions}
 * object. This object can be returned when calling the getPermissions
 * method.
 * </p>
 *
 * @author Marcelo Aguiar Rodrigues
 * @see Permissions
 * @see PermissionCollection
 */
public interface SecurityProfile {

    /**
     * Returns a collection of permissions from a profile.
     *
     * @return PermissionCollection {@link PermissionCollection}
     */
    PermissionCollection getPermissions();

}
