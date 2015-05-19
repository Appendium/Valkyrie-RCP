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
package org.valkyriercp.widget;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.valkyriercp.util.MessageConstants;
import org.valkyriercp.util.PopupMenuMouseListener;

import javax.swing.*;

/**
 * A widget that enables to mimic a screen. This is useful for application
 * still under development, but that want to show how a certain screen will look
 * like.
 *
 * The view consists of a image tab showing the screen look-a-like and/or a HTML tab
 * consisting of an explanation on what this screen will do.
 */
public class ScreenSimulationWidget extends AbstractWidget
{
    JTabbedPane mainComponent;

    private static Log log = LogFactory.getLog(ScreenSimulationWidget.class);

    public ScreenSimulationWidget(Resource explanationPath)
    {
        this(explanationPath, null);
    }

    public ScreenSimulationWidget(Resource explanationPath, Resource imagePath)
    {
        this(explanationPath, imagePath, null);
    }

    public ScreenSimulationWidget(Resource explanationPath, Resource imagePath, JPopupMenu popup)
    {
        this.mainComponent = new JTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

        JComponent imageArea = createImagePanel(imagePath);
        if (imageArea != null)
        {
            String screenLabel = getApplicationConfig().messageResolver().getMessage("simulation", "screen", MessageConstants.LABEL);
            this.mainComponent.addTab(screenLabel, imageArea);
        }
        else
            log.warn("Image not found at " + imagePath);

        JComponent explanationArea = createTextPanel(explanationPath);
        if (explanationArea != null)
        {

            String explanationLabel = getApplicationConfig().messageResolver().getMessage("simulation", "explanation", MessageConstants.LABEL);
            this.mainComponent.addTab(explanationLabel, explanationArea);
        }
        else
            log.warn("Explanation html not found at " + explanationPath);

        if (popup != null)
        {
            this.mainComponent.addMouseListener(new PopupMenuMouseListener(popup));
        }
    }

    private JComponent createTextPanel(Resource textResource)
    {
        HTMLViewWidget hw = new HTMLViewWidget(textResource);
        return hw.getComponent();
    }

    private JComponent createImagePanel(Resource imageResource)
    {
        ImageViewWidget hw = new ImageViewWidget(imageResource);
        return hw.getComponent();
    }

    public JComponent getComponent()
    {
        return mainComponent;
    }

    @Override
    public String getId() {
        return "screenSimulationWidget";
    }
}

