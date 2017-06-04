package GUI;
import io.IOoperator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.helper.StringHelper;
import model.util.ImageUtils;
import model.util.Predictor;
import model.util.neuralcluster.NCSamplePredictor;

public class MainFrame extends javax.swing.JFrame {
    public static final String NO_FILE = "No file";
    private File directory;
    private final ImageUtils imgProcObj;
    private final Predictor predictor;
    public static String a = ""; 
    //private static final int NUM_OF_PROCESS = 8;
    private final IOoperator io;
    

    public MainFrame() {
        initComponents();
        setLocationRelativeTo(null);
        this.io = new IOoperator();
        this.imgProcObj = new ImageUtils();
        this.predictor = new NCSamplePredictor();
    }
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        browseButton = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        ImageNameList = new javax.swing.JScrollPane();
        imageNameList = new javax.swing.JList<String>();
        ImageContainer = new javax.swing.JScrollPane();
        imageContainer = new javax.swing.JLabel();
        predictButton = new javax.swing.JButton();
        PredictAllButton = new javax.swing.JButton();
        parallel = new javax.swing.JRadioButton();
        isFullFolderCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sampling tool");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        imageLabel.setText("Image");

        imageNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                imageNameListValueChanged(evt);
            }
        });
        ImageNameList.setViewportView(imageNameList);

        imageContainer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImageContainer.setViewportView(imageContainer);

        predictButton.setText("Predict");
        predictButton.setEnabled(false);
        predictButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predictButtonActionPerformed(evt);
            }
        });

        PredictAllButton.setText("Predict all");
        PredictAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PredictAllButtonActionPerformed(evt);
            }
        });

        parallel.setText("Parallel Running");

        isFullFolderCheckBox.setText("getAllFiles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(isFullFolderCheckBox)
                        .addGap(63, 63, 63)
                        .addComponent(predictButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(PredictAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 209, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(imageLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(parallel))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ImageNameList, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ImageContainer)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parallel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ImageNameList, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addComponent(ImageContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PredictAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(predictButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isFullFolderCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private File getDirectory(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile(); 
        }  
        
        return null;
    }
    
    
    private void predictButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predictButtonActionPerformed
        // TODO add your handling code here:
        ((NCSamplePredictor)this.predictor).setIsFullFolder(isFullFolderCheckBox.isSelected());
        boolean isSick = this.predictor.isSick(this.directory);
        
        if(isSick){
            JOptionPane.showMessageDialog(null, "Benh nhan duoc chuan doan bi xuat huyet nao");
        }else{
            JOptionPane.showMessageDialog(null, "Benh nhan duoc chuan doan binh thuong");
        }
        
    }//GEN-LAST:event_predictButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:
        File directory = getDirectory(); 
        
        if(directory != null){
            this.directory = directory;
            File[] filesInDirectory = directory.listFiles();

            DefaultListModel<String> nameListData = new DefaultListModel<>();

            for (File file : filesInDirectory) {
                if(ImageUtils.isImage(file.getName())){
                    nameListData.addElement(file.getName());
                }
            }

            if(nameListData.isEmpty()){
                nameListData.addElement(NO_FILE);
            }

            this.imageNameList.setModel(nameListData);
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void imageNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_imageNameListValueChanged
        // TODO add your handling code here:
        String chosenFile = this.imageNameList.getSelectedValue();

        if (chosenFile != null && !chosenFile.equalsIgnoreCase(NO_FILE)) {
            String directoryPath = StringHelper.getDirectoryPath(this.directory);
            this.imgProcObj.setFilePath(StringHelper.getAbsolutePath(directoryPath, chosenFile));

            if (ImageUtils.isImage(chosenFile)) {
                updateImage();
            }
        }
    }//GEN-LAST:event_imageNameListValueChanged

    private void PredictAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PredictAllButtonActionPerformed
        // TODO add your handling code here:
        ((NCSamplePredictor)this.predictor).setIsFullFolder(isFullFolderCheckBox.isSelected());
        File[] directories = this.directory.listFiles((File file) -> file.isDirectory());
         
        /* Set pathfile */
        try {
            this.io.setPATH(StringHelper.getAbsolutePath(StringHelper.getDirectoryPath(this.directory), "result.txt"));
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         // Chay tuan tu
        if(!parallel.isSelected()){
            for (File subDirectory : directories){
                boolean isSick = this.predictor.isSick(subDirectory);
                
                if (isSick){
                    this.io.WriteFile(subDirectory.getName() + ": xuat huyet" + "\n");
                }else{
                    this.io.WriteFile(subDirectory.getName() + ": binh thuong" + "\n");
                }
            } 
        }
        
//        else{ //Chay song song
//            ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_PROCESS);
//            Set<Future<String>> set = new HashSet<Future<String>>();
//            for (File subDirectory : directories){
//                Callable<String> callable = new NCSamplePredictor(subDirectory).setIsFullFolder(true);
//                Future<String> future = executor.submit(callable);
//                set.add(future);
//            }
//          
//            for (Future<String> future : set) {
//                try {
//                    this.io.WriteFile(future.get());
//                } catch (InterruptedException | ExecutionException ex) {
//                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//                }
//           }
//        }
         /*Close file*/
         this.io.close();
            
        
    }//GEN-LAST:event_PredictAllButtonActionPerformed
    
    private void updateImage(){
        ImageIcon imageIcon = new ImageIcon(this.imgProcObj.loadImage());
        this.imageContainer.setIcon(imageIcon);
        this.predictButton.setEnabled(true);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
         
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ImageContainer;
    private javax.swing.JScrollPane ImageNameList;
    private javax.swing.JButton PredictAllButton;
    private javax.swing.JButton browseButton;
    private javax.swing.JLabel imageContainer;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JList<String> imageNameList;
    private javax.swing.JCheckBox isFullFolderCheckBox;
    private javax.swing.JRadioButton parallel;
    private javax.swing.JButton predictButton;
    // End of variables declaration//GEN-END:variables
}
