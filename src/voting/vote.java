package voting;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;



public class vote extends javax.swing.JFrame {
    koneksi Koneksi = new koneksi();
    Connection koneksi= Koneksi.getConnection();
    /** Creates new form vote */
    public vote() {
        initComponents();
        tampilCandidate();
        
    }

    public void tampilCandidate(){
        String query = "select * from voted";
        
        try {
            Statement st = koneksi.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            comBoxVote.removeAllItems();
            while(rs.next()) {
                String candidate_name = rs.getString("candidate_name");
                comBoxVote.addItem(candidate_name);
            }
        } catch (Exception e) {
            
        }
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        comBoxVote = new javax.swing.JComboBox();
        btn_vote = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setText("Vote for a Candidate");

        jButton1.setText("back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        comBoxVote.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_vote.setText("Vote");
        btn_vote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_vote))
                    .addComponent(comBoxVote, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(93, 93, 93)
                .addComponent(comBoxVote, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btn_vote))
                .addGap(143, 143, 143))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
this.dispose();
new loginform().setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try{
           Class.forName("java.sql.Driver");
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/candidate","root","");
           Statement stmt=conn.createStatement();
           String query="select * from loginfo";
           ResultSet rs=stmt.executeQuery(query);
           rs.next();
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);    // TODO add your handling code here:
        }
    }//GEN-LAST:event_formWindowActivated

    private void btn_voteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voteActionPerformed

        modelUser user = new modelUser();
        String nama_user = user.getUsername();
//        JOptionPane.showMessageDialog(null,nama_user);    
        String nama_candidate = (String) comBoxVote.getSelectedItem();
        String query_id_kandidat = "select id_candidat,suara from voted where candidate_name = '" + nama_candidate +"'";
        int id_candidat = 0;
        try {
//            JOptionPane.showMessageDialog(null,"Tes");    
            Statement st = koneksi.createStatement();
            ResultSet rs = st.executeQuery(query_id_kandidat);
            if (rs.next()) {
                id_candidat = rs.getInt("id_candidat");
                int suara = rs.getInt("suara");
                suara = suara + 1;
                String query_updateSuara = "update voted set suara = '" + suara + "' where id_candidat = '" + id_candidat + "'";
                try {
                    int row = st.executeUpdate(query_updateSuara);
                    if (row > 0) {
                        
                        String query_statusVoting = "update loginfo set voted = 'yes' where username = '" + nama_user + "'";
                        
                        try {
                            int row2= st.executeUpdate(query_statusVoting);
                            if (row2 > 0) {
                                JOptionPane.showMessageDialog(null,"Berhadil Voting");    
                                this.dispose();
                                new loginform().setVisible(true); 
                            } else {
//                                JOptionPane.showMessageDialog(null,"Gagal Voting");    
                                JOptionPane.showMessageDialog(null,"Berhadil Voting"); 
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"Terdapat kesalahan dalam update suara");    
                    }
                } catch (Exception e) {
                e.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(null,"TIdak Menemukan Data Id Candidat");    
            }                        
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_voteActionPerformed
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_vote;
    private javax.swing.JComboBox comBoxVote;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
