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
package com.github.marceloaguiarr.valkyrie.managers;

import com.github.marceloaguiarr.valkyrie.enums.ErrorMessages;

/**
 * {@inheritDoc}
 *
 * Extends the {@link SecurityManager} to override the getThreadGroup
 * method in order to prevent the plugins from creating new threads.
 *
 * @author Marcelo Aguiar Rodrigues
 * @see <a href="https://stackoverflow.com/a/31039987">Extending Security Manager</a>
 */
public class ThreadSecurityManager extends SecurityManager {

    private static ThreadGroup rootGroup;

    private static ThreadGroup getRootGroup() {
        ThreadGroup root = Thread.currentThread().getThreadGroup();
        while (root.getParent() != null) {
            root = root.getParent();
        }
        return root;
    }

    @Override
    public ThreadGroup getThreadGroup() {
        if (rootGroup == null) {
            rootGroup = getRootGroup();
        }
        return rootGroup;
    }

    @Override
    public void checkExit(int status) {
        super.checkExit(status);
        throw new RuntimeException(ErrorMessages.PLUGINS_CAN_NOT_CLOSE_APPLICATION.get());
    }
}
