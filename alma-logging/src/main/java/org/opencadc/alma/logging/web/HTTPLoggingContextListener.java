/*
 ************************************************************************
 *******************  CANADIAN ASTRONOMY DATA CENTRE  *******************
 **************  CENTRE CANADIEN DE DONNÉES ASTRONOMIQUES  **************
 *
 *  (c) 2022.                            (c) 2022.
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

package org.opencadc.alma.logging.web;


import ca.nrc.cadc.util.StringUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.AppenderRefComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.opencadc.alma.logging.LoggingClient;
import org.opencadc.alma.logging.log4j.AlmaPatternLayout;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Listener to initialize the remote logger.  This relies on a System Property to define the URL of the remote logger.
 * The name of the System property is a context init parameter.
 */
public class HTTPLoggingContextListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(HTTPLoggingContextListener.class);
    private static final String LOG_CONTROL_URL_PROPERTY = "logControlURLProperty";

    /**
     * Receives notification that the web application initialization
     * process is starting.
     *
     * <p>All ServletContextListeners are notified of context
     * initialization before any filters or servlets in the web
     * application are initialized.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     *            that is being initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();
        final String loggingControlServiceURLPropertyName = servletContext.getInitParameter(LOG_CONTROL_URL_PROPERTY);
        final String loggingControlServiceURLString = System.getProperty(loggingControlServiceURLPropertyName);

        try {
            if (StringUtil.hasText(loggingControlServiceURLString)) {
                initializeAppender(loggingControlServiceURLString);
            } else {
                throw new RuntimeException(
                        "Missing init-param value - 'logControlURL': URL to the logging service.");
            }
        } catch (MalformedURLException malformedURLException) {
            throw new IllegalArgumentException(malformedURLException.getMessage(), malformedURLException);
        }
    }

    /**
     * Receives notification that the ServletContext is about to be
     * shut down.
     *
     * <p>All servlets and filters will have been destroyed before any
     * ServletContextListeners are notified of context
     * destruction.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     *            that is being destroyed
     */
    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        // Do nothing.
    }

    /**
     * Initialize an Asynchronous appender with the remote HTTP Post appender.
     *
     * @param loggingControlServiceURLString The endpoint of the logging service.
     * @throws MalformedURLException If the provided URL string is not a URL.
     */
    void initializeAppender(final String loggingControlServiceURLString) throws MalformedURLException {
        final ConfigurationBuilder<BuiltConfiguration> configurationBuilder =
                ConfigurationBuilderFactory.newConfigurationBuilder();

        final AppenderRefComponentBuilder httpAppenderRefComponentBuilder =
                configurationBuilder.newAppenderRef("alma-remote");
        final AppenderRefComponentBuilder asyncAppenderRefComponentBuilder =
                configurationBuilder.newAppenderRef("alma-async");

        final LayoutComponentBuilder layoutComponentBuilder =
                configurationBuilder.newLayout("AlmaLayout");
        final AppenderComponentBuilder httpAppenderComponentBuilder =
                configurationBuilder
                        .newAppender("alma-remote", "Http")
                        .addAttribute("url", new URL(loggingControlServiceURLString).toExternalForm())
                        .add(layoutComponentBuilder);

        final AppenderComponentBuilder asyncAppenderComponentBuilder =
                configurationBuilder
                        .newAppender("alma-async", "Async")
                        .addComponent(httpAppenderRefComponentBuilder);

        configurationBuilder.setPackages(AlmaPatternLayout.class.getPackage().getName())
                            .add(httpAppenderComponentBuilder)
                            .add(asyncAppenderComponentBuilder)
                            .add(configurationBuilder
                                         .newLogger(LoggingClient.class.getName(), Level.ALL)
                                         .add(asyncAppenderRefComponentBuilder));

        Configurator.initialize(configurationBuilder.build());
        LOGGER.info("initializeAppender() OK");
    }
}
