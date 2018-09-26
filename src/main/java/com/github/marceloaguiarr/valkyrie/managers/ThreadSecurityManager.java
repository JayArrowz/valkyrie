package com.github.marceloaguiarr.valkyrie.managers;

/**
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

}
