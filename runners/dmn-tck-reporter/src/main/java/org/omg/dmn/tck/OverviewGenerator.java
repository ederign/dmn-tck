/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.omg.dmn.tck;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * generates the index.html page
 */
public class OverviewGenerator {
    private static final Logger logger = LoggerFactory.getLogger( OverviewGenerator.class);

    public static void generatePage(Reporter.Parameters params, Configuration cfg, Vendor vendor, ReportHeader header, ReportTable tableByLabels, ReportChart chartByLabels) {
        logger.info( "Generating overview_{}.html", vendor.getFileNameId() );
        try {
            Template temp = cfg.getTemplate( "/templates/overview.ftl" );

            Map<String, Object> data = new HashMap<>(  );
            data.put( "vendor", vendor );
            data.put( "header", header );
            data.put( "tByLabels", tableByLabels );
            data.put( "cbl", chartByLabels );

            Writer out = new FileWriter( params.output.getAbsolutePath() + "/overview_"+vendor.getFileNameId()+".html" );
            temp.process( data, out );
            out.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( TemplateException e ) {
            e.printStackTrace();
        }
    }

}
