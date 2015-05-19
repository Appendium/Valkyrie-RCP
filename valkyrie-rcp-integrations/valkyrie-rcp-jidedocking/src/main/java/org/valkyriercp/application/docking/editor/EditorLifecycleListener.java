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
package org.valkyriercp.application.docking.editor;

import com.jidesoft.document.DocumentComponentAdapter;
import com.jidesoft.document.DocumentComponentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.valkyriercp.application.PageComponent;
import org.valkyriercp.application.docking.JideApplicationPage;

/**
 * Listener class that ensures Spring RCP focus events and
 * page component lifestyle events are called from the
 * JIDE document events.
 * 
 * @author Jonny Wray
 *
 */
public class EditorLifecycleListener extends DocumentComponentAdapter{
	private static final Logger logger = LoggerFactory.getLogger(EditorLifecycleListener.class);
	private PageComponent pageComponent;
	private WorkspaceView workspace;
	
	public EditorLifecycleListener(WorkspaceView workspace, PageComponent pageComponent){
		this.pageComponent = pageComponent;
		this.workspace = workspace;
	}
	
	public void documentComponentDeactivated(DocumentComponentEvent event) {
		if(logger.isDebugEnabled()){
			logger.debug("Page component "+pageComponent.getId()+" deactivated");
		}
		((JideApplicationPage)workspace.getContext().getPage()).fireFocusLost(pageComponent);
	}

	/**
	 * Default closed implementation removes the given page component from the
	 * workspace view, and so the document from the document pane.
	 */
	public void documentComponentClosed(DocumentComponentEvent event) {
		if(logger.isDebugEnabled()){
			logger.debug("Page component "+pageComponent.getId()+" closed");
		}
		workspace.remove(pageComponent);
		((JideApplicationPage)workspace.getContext().getPage()).fireClosed(pageComponent);
	}
	
	public void documentComponentOpened(DocumentComponentEvent event) {
		if(logger.isDebugEnabled()){
			logger.debug("Page component "+pageComponent.getId()+" opened");
		}
		((JideApplicationPage)workspace.getContext().getPage()).fireOpened(pageComponent);
	}

	public void documentComponentActivated(DocumentComponentEvent event){
		if(logger.isDebugEnabled()){
			logger.debug("Page component "+pageComponent.getId()+" activated");
		}
		((JideApplicationPage)workspace.getContext().getPage()).fireFocusGained(pageComponent);
	}

	public void documentComponentClosing(DocumentComponentEvent event){
		if(logger.isDebugEnabled()){
			logger.debug("Page component "+pageComponent.getId()+" closing");
		}
		((JideApplicationPage)workspace.getContext().getPage()).fireFocusLost(pageComponent);
	}
}
