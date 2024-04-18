/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package displaySwitcher.contextmenu;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.persistence.PersistedList;
import burp.api.montoya.persistence.PersistedObject;
import java.awt.GraphicsDevice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author karl
 */
public class DisplaySwitcherPanel extends javax.swing.JPanel {
    public String newFontProfileName = "";
    private final MontoyaApi api;
    public PersistedObject myExtensionData;
    public PersistedList<String> monitorList = PersistedList.persistedStringList(); // = new ArrayList<>();
    final DefaultListModel<String> fontProfileListModel = new DefaultListModel<>();
    final DefaultListModel<String> monitorListModel = new DefaultListModel<>();
    /**
     * Creates new form fontSwitcherPanel
     */
    public DisplaySwitcherPanel(MontoyaApi api) {
        
        this.api = api;
        myExtensionData = api.persistence().extensionData();
        //load saved extension data to local list model for UI update
        for(String fontProfile : myExtensionData.stringKeys()) {
            if(fontProfile.contains(":")){
                monitorListModel.addElement(fontProfile);
            } else {
                fontProfileListModel.addElement(fontProfile);
            }
        }
        //
        
        MouseListener mouseListenerFont = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String targetMonitor = jList2.getSelectedValue();
                    String fontProfile = jList1.getSelectedValue();
                    myExtensionData.setString(targetMonitor, fontProfile);
                }
            }
        };
       
        MouseListener mouseListenerMonitor = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                    String targetMonitor = jList2.getSelectedValue();
                    
                    if(fontProfileListModel.contains(myExtensionData.getString(targetMonitor))){
                        jList1.setSelectedValue(myExtensionData.getString(targetMonitor), true);
                    }
                   
                
            }
        };

        
        initComponents();
        if(!Objects.isNull(myExtensionData.getBoolean("checkbox")) && myExtensionData.getBoolean("checkbox").booleanValue() == true){
            jCheckBox2.setSelected(true);
        }
        jList1.addMouseListener(mouseListenerFont);             
        jList2.addMouseListener(mouseListenerMonitor);
        jList1.setModel(fontProfileListModel);
        jList2.setModel(monitorListModel);
        
        
        
    }
    
    public String getMonitorResolution(JTextField textfield){
        JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, textfield);
        GraphicsDevice gd = frame.getGraphicsConfiguration().getDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        
        String screenDimensions = String.valueOf(screenWidth) + "x" + String.valueOf(screenHeight);
        return screenDimensions;
        
        
    }
    
    public void saveNewFontProfile(String name){

        String userOptions = api.burpSuite().exportUserOptionsAsJson("user_options.display");
        api.logging().logToOutput("Exported Display Options: " + userOptions);
        myExtensionData.setString(name,userOptions);
        fontProfileListModel.addElement(name);
        //jList1.setModel(fontProfileListModel);
       
    }
    
    public void loadFontProfile(String name){
        String fontProfile = myExtensionData.getString(name);
        api.burpSuite().importUserOptionsFromJson(fontProfile);
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTextField1.setText("Name");

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "fontProfileListModel" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Display Settings Profiles");

        jLabel2.setText("Save New Display Profile with Current Display Settings");

        jLabel3.setText("Monitor Configurations (Displayed as Name:Resolution)");

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "monitorListModel" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList2);

        jButton3.setText("DETECT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField2.setText("Name");

        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("ADD");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Autoswitch on Tab Access (first resolution match)");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Select Monitor Configuration, then double click Display Profile to associate.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 29, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addComponent(jButton2)))
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(76, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String newFontProfileName = jTextField1.getText();
        if(newFontProfileName.contains(":")){
            api.logging().logToError("Font Profile Names cannot contain the colon character ':'");
            return;
        }
        saveNewFontProfile(newFontProfileName);
        jTextField1.setText("Name");

        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String targetProfile = jList1.getSelectedValue();
        myExtensionData.deleteString(targetProfile);
        fontProfileListModel.removeElementAt(jList1.getSelectedIndex());
        //jList1.setModel(fontProfileListModel);
        
        
        
                // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
            
        String screenDimensions = getMonitorResolution(jTextField2);
        jTextField2.setText(screenDimensions);
 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        String screenDimensions = getMonitorResolution(jTextField2);
        String addMonitorString = jTextField2.getText() + ":" + screenDimensions;
        if(myExtensionData.getString(addMonitorString) != null){
            api.logging().logToError("Duplicate Monitor Configuration Name Exists.");
            return;
        }
        //add to list, update model, add to persistence
        monitorListModel.addElement(addMonitorString);
        myExtensionData.setString(addMonitorString, addMonitorString);
        //jList2.setModel(monitorListModel);


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String targetMonitor = jList2.getSelectedValue();
        monitorListModel.removeElementAt(jList2.getSelectedIndex());
        myExtensionData.deleteString(targetMonitor);
        
        
        
        //jList2.setModel(monitorListModel);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        
        if (jCheckBox2.isSelected() == true){
            
            String currentResolution = getMonitorResolution(jTextField2);
            
            //find first saved monitor configuration with current resolution.
            
            for(String fontProfile : myExtensionData.stringKeys()) {
            if(fontProfile.contains(":")){ //it's a monitor
                if (fontProfile.endsWith(currentResolution)){
                    String fullName = fontProfile;
                    String associatedFontProfile = myExtensionData.getString(fullName);
                    loadFontProfile(associatedFontProfile);
                }
                //monitorListModel.addElement(fontProfile);
            } else {
                //fontProfileListModel.addElement(fontProfile);
            }
        }
            
            
        }
    }//GEN-LAST:event_formComponentShown

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if(jCheckBox2.isSelected() == true){
            myExtensionData.setBoolean("checkbox", true);
        } else {
            myExtensionData.setBoolean("checkbox", false);
        }
        api.logging().logToOutput("checkbox bool: " + String.valueOf(myExtensionData.getBoolean("checkbox")));
            // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
