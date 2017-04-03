package com.jsql.view.swing.action;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.jsql.i18n.I18n;
import com.jsql.view.swing.MediatorGui;

@SuppressWarnings("serial")
public class ActionCloseTabResult extends AbstractAction {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ActionCloseTabResult.perform(MediatorGui.tabResults().getSelectedIndex());
    }
    
    public static void perform(int closeTabNumber) {
        if (MediatorGui.tabResults().getTabCount() > 0) {
            MediatorGui.tabResults().removeTabAt(closeTabNumber);
            
            ActionCloseTabResult.perform();
        }
    }
    
    public static void perform() {
        if (MediatorGui.tabResults().getTabCount() == 0) {
            int i = MediatorGui.frame().splitHorizontalTopBottom.splitVerticalLeftRight.getDividerLocation();
            if (ComponentOrientation.getOrientation(I18n.getLocaleDefault()) == ComponentOrientation.LEFT_TO_RIGHT) {
                MediatorGui.frame().splitHorizontalTopBottom.splitVerticalLeftRight.setRightComponent(
                    MediatorGui.frame().splitHorizontalTopBottom.labelPlaceholderResult
                );
            } else {
                MediatorGui.frame().splitHorizontalTopBottom.splitVerticalLeftRight.setLeftComponent(
                    MediatorGui.frame().splitHorizontalTopBottom.labelPlaceholderResult
                );
            }
            MediatorGui.frame().splitHorizontalTopBottom.splitVerticalLeftRight.setDividerLocation(i);
        }
    }
    
}