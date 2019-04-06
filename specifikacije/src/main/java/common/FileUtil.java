package common;

import java.io.File;

/**
 * Pomocne metode za rad sa fajlovima
 */
public final class FileUtil {

    private FileUtil() {

    }

    /**
     * @param archivePath gde se skladisti arhiva
     * @param files       fajlovi koje zelimo da arhiviramo
     */
    public static void zip(String archivePath, File... files) {
        System.out.println("Perform zip operation");
    }
}
