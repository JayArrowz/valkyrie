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
