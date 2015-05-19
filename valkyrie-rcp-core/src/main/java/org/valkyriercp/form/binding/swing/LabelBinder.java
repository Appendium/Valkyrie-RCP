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
package org.valkyriercp.form.binding.swing;

import org.springframework.util.Assert;
import org.valkyriercp.binding.form.FormModel;
import org.valkyriercp.form.binding.Binding;
import org.valkyriercp.form.binding.support.AbstractBinder;

import javax.swing.*;
import java.util.Map;

/**
 * @author Mauro Ransolin
 */
public class LabelBinder extends AbstractBinder {

    public LabelBinder() {
        super(String.class);
    }

    protected Binding doBind(JComponent control, FormModel formModel, String formPropertyPath, Map context) {
        Assert.isTrue(control instanceof JLabel, "Control must be an instance of JLabel.");
        return new LabelBinding((JLabel)control, formModel, formPropertyPath);
    }

    protected JComponent createControl(Map context) {
        return getComponentFactory().createLabel("");
    }
}