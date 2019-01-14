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

/**
 * Compilation of error messages used in Valkyrie.
 *
 * @author Marcelo Aguiar Rodrigues
 */
public enum ErrorMessages {

    SECURITY_MANAGER_CANT_BE_NULL("SecurityManager can not be null"),
    POLICY_CANT_BE_NULL("Policy can not be null"),
    PLUGINS_CAN_NOT_CLOSE_APPLICATION("plugins can not close the application")
    ;

    private String value;

    ErrorMessages(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
