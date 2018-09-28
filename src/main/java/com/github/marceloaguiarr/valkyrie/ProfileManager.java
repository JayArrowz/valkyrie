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

import com.github.marceloaguiarr.valkyrie.profiles.SecurityProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the class loaders with each specific {@link SecurityProfile}
 *
 * @author Marcelo Aguiar Rodrigues
 */
final class ProfileManager {

    private static Map<Class, SecurityProfile> plugins = new HashMap<>();

    /**
     * Associates a securityProfile with a class loader. Every code that runs on an
     * instance of the classloader will be subjected to the permissions
     * set on the securityProfile.
     *
     * @param classLoader     ClassLoader
     * @param securityProfile {@link SecurityProfile}
     */
    static void addProfile(Class classLoader, SecurityProfile securityProfile) {
        plugins.put(classLoader, securityProfile);
    }

    /**
     * Returns the {@link SecurityProfile} associated with a {@link ClassLoader}
     *
     * @param classLoader {@link ClassLoader}
     * @return SecurityProfile
     */
    static SecurityProfile getProfile(ClassLoader classLoader) {
        return plugins.get(classLoader.getClass());
    }

    /**
     * Checks if a specified {@link ClassLoader} should be treated as a plugin
     *
     * @param classLoader {@link ClassLoader}
     * @return true if has a {@link SecurityProfile} for the {@link ClassLoader},
     * false otherwise
     */
    static boolean isPlugin(ClassLoader classLoader) {
        return plugins.containsKey(classLoader.getClass());
    }
}
