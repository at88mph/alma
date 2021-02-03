
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

import ca.nrc.cadc.dali.Interval;
import ca.nrc.cadc.dali.Shape;
import ca.nrc.cadc.dali.util.IntervalFormat;
import ca.nrc.cadc.dali.util.ShapeFormat;
import ca.nrc.cadc.dali.util.StringListFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencadc.alma.AlmaProperties;
import org.opencadc.alma.deliverable.DeliverableURLBuilder;
import org.opencadc.alma.deliverable.HierarchyItem;

import ca.nrc.cadc.net.NetUtil;
import ca.nrc.cadc.util.StringUtil;
import org.opencadc.soda.Cutout;
import org.opencadc.soda.ExtensionSlice;
import org.opencadc.soda.ExtensionSliceFormat;
import org.opencadc.soda.SodaParamValidator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class SodaURLBuilder extends DeliverableURLBuilder {

    private static final Logger LOGGER = LogManager.getLogger(SodaURLBuilder.class);

    public SodaURLBuilder(final AlmaProperties almaProperties) {
        super(almaProperties);
    }

    public URL createCutoutURL(final HierarchyItem hierarchyItem, final Cutout cutout) throws MalformedURLException {
        final URL downloadURL = createDownloadURL(hierarchyItem);
        return toCutoutURL(downloadURL, cutout);
    }

    private URL toCutoutURL(final URL downloadURL, final Cutout cutout) throws MalformedURLException {
        final StringBuilder queryStringBuilder = new StringBuilder();
        final StringBuilder urlStringBuilder = new StringBuilder(downloadURL.toExternalForm());

        if (cutout.pos != null) {
            queryStringBuilder.append(String.format("%s=%s", cutout.pos.getClass().getSimpleName().toLowerCase(),
                                                    NetUtil.encode(format(cutout.pos))));
        }

        if (cutout.band != null) {
            queryStringBuilder.append(String.format("%s=%s", SodaParamValidator.BAND.toLowerCase(),
                                                    NetUtil.encode(format(cutout.band))));
        }

        if (cutout.time != null) {
            queryStringBuilder.append(String.format("%s=%s", SodaParamValidator.TIME.toLowerCase(),
                                                    NetUtil.encode(format(cutout.time))));
        }

        if (cutout.pol != null && !cutout.pol.isEmpty()) {
            queryStringBuilder.append(String.format("%s=%s", SodaParamValidator.POL.toLowerCase(),
                                                    NetUtil.encode(format(cutout.pol))));
        }

        final ExtensionSliceFormat extensionSliceFormat = new ExtensionSliceFormat();
        for (final ExtensionSlice extensionSlice : cutout.extensionSlices) {
            queryStringBuilder.append(String.format("%s=%s", SodaParamValidator.SUB.toLowerCase(),
                                                    NetUtil.encode(extensionSliceFormat.format(extensionSlice))));
        }

        if (queryStringBuilder.length() > 0) {
            if (StringUtil.hasText(downloadURL.getQuery())) {
                urlStringBuilder.append("&");
            } else {
                urlStringBuilder.append("?");
            }

            urlStringBuilder.append(queryStringBuilder.toString());
        }

        LOGGER.debug(String.format("CutoutURL from %s is %s.", cutout, urlStringBuilder.toString()));

        return new URL(urlStringBuilder.toString());
    }

    /**
     * Format the Shape's value, but remove the class name that is prepended.
     * @param shape The Shape to format.
     * @return  String value
     */
    private String format(final Shape shape) {
        return new ShapeFormat(true).format(shape).substring(
                shape.getClass().getSimpleName().length()).trim();
    }

    private String format(final Interval<?> interval) {
        return new IntervalFormat().format(interval);
    }

    private String format(final List<String> stringList) {
        return new StringListFormat().format(stringList);
    }
}
