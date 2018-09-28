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

import com.github.marceloaguiarr.valkyrie.enums.SecurityManagers;
import com.github.marceloaguiarr.valkyrie.managers.ThreadSecurityManager;
import com.github.marceloaguiarr.valkyrie.profiles.NoPermissionsSecurityProfile;
import com.github.marceloaguiarr.valkyrie.profiles.SecurityProfile;
import org.junit.Test;

import java.net.URLClassLoader;
import java.security.Policy;

import static org.junit.Assert.*;

public class ValkyrieTest {


    @Test(expected = NullPointerException.class)
    public void noSetup() {
        Valkyrie.start();
    }

    @Test(expected = NullPointerException.class)
    public void nullPolicy() {
        Policy p = null;
        SecurityManager sm = null;
        Valkyrie.setSecurityManager(sm, p);

        // Should throw exception
        Valkyrie.start();
    }

    @Test(expected = NullPointerException.class)
    public void nullSecurityManager() {
        SecurityManager sm = null;
        Valkyrie.setSecurityManager(sm);

        // Should throw exception
        Valkyrie.start();
    }

    @Test
    public void happyPath() {
        SecurityProfile p = new NoPermissionsSecurityProfile();

        Valkyrie.addProfile(URLClassLoader.class, p);
        Valkyrie.setSecurityManager(SecurityManagers.DEFAULT);
        Valkyrie.start();

        assertNotNull(System.getSecurityManager());
        assertTrue(System.getSecurityManager() instanceof SecurityManager);
    }

    @Test
    public void setWithoutParameters() {
        Valkyrie.setSecurityManager();

        Valkyrie.start();

        assertNotNull(System.getSecurityManager());
        assertNotNull(Policy.getPolicy());
    }

    @Test
    public void setNoThreadSecurityManager() {
        Valkyrie.setSecurityManager(SecurityManagers.NO_THREADS);

        Valkyrie.start();

        assertNotNull(System.getSecurityManager());
        assertNotNull(Policy.getPolicy());
        assertTrue(System.getSecurityManager() instanceof ThreadSecurityManager);

    }

    @Test
    public void doPrivileged() {
        Valkyrie.setSecurityManager(SecurityManagers.DEFAULT);
        Valkyrie.start();

        assertNotNull(System.getSecurityManager());
        assertNotNull(Policy.getPolicy());

        Valkyrie.doPrivileged(() -> {
            System.setSecurityManager(null);
            return null;
        });

        assertNull(System.getSecurityManager());
    }

}
