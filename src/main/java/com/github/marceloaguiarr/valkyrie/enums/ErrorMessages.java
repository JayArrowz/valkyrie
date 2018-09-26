package com.github.marceloaguiarr.valkyrie.enums;

/**
 * Compilation of error messages used in Valkyrie.
 *
 * @author Marcelo Aguiar Rodrigues
 */
public enum ErrorMessages {

    SECURITY_MANAGER_CANT_BE_NULL("Security Manager can't be null"),
    POLICY_CANT_BE_NULL("Policy can't be null"),;

    private String value;

    ErrorMessages(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
