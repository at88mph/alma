
/*
 ************************************************************************
 *******************  CANADIAN ASTRONOMY DATA CENTRE  *******************
 **************  CENTRE CANADIEN DE DONNÉES ASTRONOMIQUES  **************
 *
 *  (c) 2019.                            (c) 2019.
 *  Government of Canada                 Gouvernement du Canada
 *  National Research Council            Conseil national de recherches
 *  Ottawa, Canada, K1A 0R6              Ottawa, Canada, K1A 0R6
 *  All rights reserved                  Tous droits réservés
 *
 *  NRC disclaims any warranties,        Le CNRC dénie toute garantie
 *  expressed, implied, or               énoncée, implicite ou légale,
 *  statutory, of any kind with          de quelque nature que ce
 *  respect to the software,             soit, concernant le logiciel,
 *  including without limitation         y compris sans restriction
 *  any warranty of merchantability      toute garantie de valeur
 *  or fitness for a particular          marchande ou de pertinence
 *  purpose. NRC shall not be            pour un usage particulier.
 *  liable in any event for any          Le CNRC ne pourra en aucun cas
 *  damages, whether direct or           être tenu responsable de tout
 *  indirect, special or general,        dommage, direct ou indirect,
 *  consequential or incidental,         particulier ou général,
 *  arising from the use of the          accessoire ou fortuit, résultant
 *  software.  Neither the name          de l'utilisation du logiciel. Ni
 *  of the National Research             le nom du Conseil National de
 *  Council of Canada nor the            Recherches du Canada ni les noms
 *  names of its contributors may        de ses  participants ne peuvent
 *  be used to endorse or promote        être utilisés pour approuver ou
 *  products derived from this           promouvoir les produits dérivés
 *  software without specific prior      de ce logiciel sans autorisation
 *  written permission.                  préalable et particulière
 *                                       par écrit.
 *
 *  This file is part of the             Ce fichier fait partie du projet
 *  OpenCADC project.                    OpenCADC.
 *
 *  OpenCADC is free software:           OpenCADC est un logiciel libre ;
 *  you can redistribute it and/or       vous pouvez le redistribuer ou le
 *  modify it under the terms of         modifier suivant les termes de
 *  the GNU Affero General Public        la “GNU Affero General Public
 *  License as published by the          License” telle que publiée
 *  Free Software Foundation,            par la Free Software Foundation
 *  either version 3 of the              : soit la version 3 de cette
 *  License, or (at your option)         licence, soit (à votre gré)
 *  any later version.                   toute version ultérieure.
 *
 *  OpenCADC is distributed in the       OpenCADC est distribué
 *  hope that it will be useful,         dans l’espoir qu’il vous
 *  but WITHOUT ANY WARRANTY;            sera utile, mais SANS AUCUNE
 *  without even the implied             GARANTIE : sans même la garantie
 *  warranty of MERCHANTABILITY          implicite de COMMERCIALISABILITÉ
 *  or FITNESS FOR A PARTICULAR          ni d’ADÉQUATION À UN OBJECTIF
 *  PURPOSE.  See the GNU Affero         PARTICULIER. Consultez la Licence
 *  General Public License for           Générale Publique GNU Affero
 *  more details.                        pour plus de détails.
 *
 *  You should have received             Vous devriez avoir reçu une
 *  a copy of the GNU Affero             copie de la Licence Générale
 *  General Public License along         Publique GNU Affero avec
 *  with OpenCADC.  If not, see          OpenCADC ; si ce n’est
 *  <http://www.gnu.org/licenses/>.      pas le cas, consultez :
 *                                       <http://www.gnu.org/licenses/>.
 *
 *
 ************************************************************************
 */

package org.opencadc.alma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.nrc.cadc.util.StringUtil;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class that can handle a UID in the form of archiveUID://C0/C1/C2 (or uid___C0_C1_C2), or a Project Tarfile ID that is
 * in the form of 2016.1.00161.S_uid___A002_Xc4f3ae_X537a.asdm.sdm.tar.
 */
public class AlmaUID {

    private static final Logger LOGGER = LogManager.getLogger(AlmaUID.class);
    private static final Pattern UID_PATTERN =
            Pattern.compile("uid[_:]+[_/]+[_/]+\\w[0-9a-fA-F]+[_/]+\\w[0-9a-fA-F]+[_/]+\\w[0-9a-fA-F]+");


    // The original UID as provided by the constructor.
    private final String uid;
    private final AlmaUID archiveUID;


    public AlmaUID(final String uid) {
        if (!StringUtil.hasText(uid)) {
            throw new IllegalArgumentException("Passed ID cannot be null or empty.");
        }

        this.uid = uid;
        this.archiveUID = parseArchiveID();
    }

    private AlmaUID parseArchiveID() {
        final Matcher matcher = UID_PATTERN.matcher(this.uid);

        if (matcher.find()) {
            final String uidMatch = matcher.group();

            LOGGER.debug(String.format("Found match (%s)", uidMatch));
            // If the parent matches this current one then don't set it.
            return desanitize(uidMatch).equals(desanitize(this.uid)) ? null : new AlmaUID(uidMatch);
        } else {
            throw new IllegalArgumentException(String.format("No UID found in %s", this.uid));
        }
    }

    public String getUID() {
        return this.uid;
    }

    public AlmaUID getArchiveUID() {
        return this.archiveUID;
    }

    public String getSanitisedUid() {
        return this.sanitize(this.uid);
    }

    public String getSanitizedArchiveUID() {
        return this.sanitize(this.archiveUID.getUID());
    }

    public String getDesanitisedUid() {
        return desanitize(this.uid);
    }

    String desanitize(final String uid) {
        return uid.replace("uid___", "uid://").replaceAll("_", "/");
    }

    String sanitize(final String uid) {
        return uid.replace(':', '_').replaceAll("/", "_");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AlmaUID almaUID = (AlmaUID) o;
        return this.uid.equals(almaUID.uid) &&
               Objects.equals(this.archiveUID, almaUID.archiveUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.uid, this.archiveUID);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return this.uid;
    }
}
