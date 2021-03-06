
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

package org.opencadc.soda.server;

import ca.nrc.cadc.dali.Circle;
import ca.nrc.cadc.dali.Point;

import java.net.URI;
import java.net.URL;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.Assert;
import org.opencadc.alma.AlmaUID;
import org.opencadc.alma.deliverable.HierarchyItem;
import org.opencadc.alma.deliverable.RequestHandlerQuery;


public class AlmaStreamingSodaPluginTest {

    @Test
    public void simpleCutoutURL() throws Throwable {
        final RequestHandlerQuery mockRequestHandlerQuery = mock(RequestHandlerQuery.class);
        final SodaURLBuilder mockSodaURLBuilder = mock(SodaURLBuilder.class);
        final AlmaStreamingSodaPlugin testSubject = new AlmaStreamingSodaPlugin(mockRequestHandlerQuery,
                                                                                mockSodaURLBuilder);
        final String targetParentUid = "uid://C1/C2/C3";
        final URI targetURI = URI.create("1977.11.25_uid___C1_C2_C3.myfile.fits");
        final AlmaUID targetAlmaUID = new AlmaUID(targetURI.toString());
        final Circle circle = new Circle(new Point(18.0D, 78.5D), 0.5D);
        final Cutout testCutout = new Cutout();
        testCutout.pos = circle;

        final HierarchyItem hierarchyItem = new HierarchyItem(new AlmaUID(targetParentUid), targetAlmaUID.toString(), "myfile.fits",
                                                              HierarchyItem.Type.ASDM, 88L, true,
                                                              new HierarchyItem[0], new AlmaUID[0]);

        when(mockRequestHandlerQuery.query(targetAlmaUID)).thenReturn(hierarchyItem);
        when(mockSodaURLBuilder.createCutoutURL(hierarchyItem, testCutout)).thenReturn(
                new URL("https://almaserver.com/sodacutout/downloads/1977.11.25_uid___C1_C2_C3.myfile.fits?CIRCLE=18" +
                        ".0+78.5+0.5"));

        final URL cutoutURL = testSubject.toURL(88, targetURI, testCutout, null);

        Assert.assertEquals("Wrong result URL.",
                            "https://almaserver.com/sodacutout/downloads/1977.11.25_uid___C1_C2_C3.myfile" +
                            ".fits?CIRCLE=18.0+78.5+0.5",
                            cutoutURL.toExternalForm());

        verify(mockRequestHandlerQuery, times(1)).query(targetAlmaUID);
    }
}
