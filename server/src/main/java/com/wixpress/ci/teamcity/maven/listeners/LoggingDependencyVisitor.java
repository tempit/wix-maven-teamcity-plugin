/*******************************************************************************
 * Copyright (c) 2010, 2011 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.sonatype.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sonatype, Inc. - initial API and implementation
 *******************************************************************************/
package com.wixpress.ci.teamcity.maven.listeners;

import org.sonatype.aether.graph.DependencyNode;
import org.sonatype.aether.graph.DependencyVisitor;

/**
 * A dependency visitor that dumps the graph to the console.
 */
public class LoggingDependencyVisitor
    implements DependencyVisitor
{

    private ListenerLogger out;

    private String currentIndent = "";

    public LoggingDependencyVisitor(ListenerLogger out)
    {
        this.out = out;
    }

    public boolean visitEnter( DependencyNode node )
    {
        out.info( currentIndent + node );
        if ( currentIndent.length() <= 0 )
        {
            currentIndent = "+- ";
        }
        else
        {
            currentIndent = "|  " + currentIndent;
        }
        return true;
    }

    public boolean visitLeave( DependencyNode node )
    {
        currentIndent = currentIndent.substring( 3, currentIndent.length() );
        return true;
    }

}
