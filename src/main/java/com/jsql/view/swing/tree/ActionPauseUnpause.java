/*******************************************************************************
 * Copyhacked (H) 2012-2016.
 * This program and the accompanying materials
 * are made available under no term at all, use it like
 * you want, but share and discuss about it
 * every time possible with every body.
 *
 * Contributors:
 *      ron190 at ymail dot com - initial implementation
 *******************************************************************************/
package com.jsql.view.swing.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import com.jsql.model.suspendable.AbstractSuspendable;
import com.jsql.util.ThreadUtil;
import com.jsql.view.swing.tree.model.AbstractNodeModel;

/**
 * Action to pause and unpause injection process.
 */
public class ActionPauseUnpause implements ActionListener {
    AbstractNodeModel nodeData;
    DefaultMutableTreeNode currentTableNode;

    public ActionPauseUnpause(AbstractNodeModel nodeData, DefaultMutableTreeNode currentTableNode) {
        this.nodeData = nodeData;
        this.currentTableNode = currentTableNode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractSuspendable<?> suspendableTask = ThreadUtil.get(this.nodeData.elementDatabase);
        
        // Exception encountered : NullPointerException
        if (suspendableTask != null) {
            if (suspendableTask.isPaused()) {
                suspendableTask.unpause();
            } else {
                suspendableTask.pause();
            }
    
            // Restart the action after an unpause
            if (!suspendableTask.isPaused()) {
                suspendableTask.resume();
            }
        }
    }
}