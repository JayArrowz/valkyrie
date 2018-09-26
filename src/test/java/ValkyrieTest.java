import com.github.marceloaguiarr.valkyrie.Valkyrie;
import com.github.marceloaguiarr.valkyrie.enums.SecurityManagers;
import com.github.marceloaguiarr.valkyrie.managers.ThreadSecurityManager;
import com.github.marceloaguiarr.valkyrie.profiles.NoPermissionsSecurityProfile;
import com.github.marceloaguiarr.valkyrie.profiles.SecurityProfile;
import org.junit.Test;

import java.net.URL;
import java.net.URLClassLoader;
import java.security.Policy;
import java.security.PrivilegedAction;

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
