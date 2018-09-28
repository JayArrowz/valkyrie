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
