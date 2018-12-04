package org.intermine.webservice.server.fair;

/*
 * Copyright (C) 2002-2018 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import org.apache.commons.lang.StringUtils;
import org.intermine.api.InterMineAPI;
import org.intermine.web.logic.PermanentURIHelper;
import org.intermine.webservice.server.core.JSONService;

/**
 * Generate a permanent URI given a type and internal InterMine ID
 * Examples of permanent URIs
 * 1-If the mine is registered in identifiers.org and you've configured the identifier.uri.base
 * property in the mine properties file:
 * identifiers.org/{mine}/protein:P31946 (if the mine is registered in identifiers.org)
 * 2-If a redirection system is not used the uri generated will be based on
 * {webapp.baseurl}/{webapp.path}/protein:P31946
 *
 * The uri returned will be empty in the following cases:
 * type is not defined in the model,
 * type is defined in the model but we are not bale to generate a permanet URI
 * id is wrong
 *
 * @author Daniela Butano
 */
public class PermanentURIService extends JSONService
{

    /**
     * The constructor
     * @param im the intermine api
     */
    public PermanentURIService(InterMineAPI im) {
        super(im);
    }

    @Override
    protected void execute() throws Exception {
        String type = getRequiredParameter("type");
        String id = getRequiredParameter("id");
        String uri = (new PermanentURIHelper(request)).getPermanentURI(type,
                Integer.parseInt(id));
        if (uri == null) {
            addOutputInfo("uri", StringUtils.EMPTY);
        } else {
            addOutputInfo("uri", uri);
        }
    }
}
