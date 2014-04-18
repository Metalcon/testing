package de.metalcon.testing;

import de.metalcon.domain.Muid;
import de.metalcon.domain.UidType;
import de.metalcon.exceptions.ServiceOverloadedException;

/**
 * MUID factory
 * 
 * @author sebschlicht
 * 
 */
public class MuidFactory {

    /**
     * generate a MUID<br>
     * waits until the MUID is created, if there were too many MUIDs created for
     * the type requested
     * 
     * @param muidType
     *            type of the MUID requested
     * @return new MUID
     */
    public static Muid generateMuid(final UidType muidType) {
        try {
            return Muid.create(muidType);
        } catch (ServiceOverloadedException e) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e1) {
                // thread interrupted
            }
            return generateMuid(muidType);
        }
    }

}
