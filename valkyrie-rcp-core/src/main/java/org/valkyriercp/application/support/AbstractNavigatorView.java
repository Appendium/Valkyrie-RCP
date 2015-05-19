/**
 * Copyright (C) 2015 Valkyrie RCP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.valkyriercp.application.support;

import net.miginfocom.swing.MigLayout;
import org.valkyriercp.command.support.CommandGroup;
import org.valkyriercp.command.support.CommandGroupJComponentBuilder;

import javax.swing.*;

public abstract class AbstractNavigatorView extends AbstractView
{
    private CommandGroup currentNavigation;

    protected AbstractNavigatorView(CommandGroup currentNavigation)
    {
        super("abstractNavigatorView");
        this.currentNavigation = currentNavigation;
    }

    public abstract CommandGroupJComponentBuilder getNavigationBuilder();

    protected JComponent createControl()
    {
        JPanel navigationView = new JPanel(new MigLayout("fill"));
        navigationView.add(getNavigationBuilder().buildComponent(this.currentNavigation), "grow,push");
        return navigationView;
    }
}

