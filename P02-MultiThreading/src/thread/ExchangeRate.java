package thread;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ACER
 */
import com.google.gson.Gson;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class ExchangeRate extends javax.swing.JFrame {

    String API_PROVDIER = "https://api.apilayer.com/exchangerates_data";
    String[] code = {"MXN", "AUD", "HKD", "RON", "HRK", "CHF", "IDR", "CAD", "USD", "JPY", "BRL", "PHP", "CZK", "NOK", "INR", "PLN", "ISK", "MYR", "ZAR", "ILS", "GBP", "SGD", "HUF", "EUR", "CNY", "TRY", "SEK", "RUB", "NZD", "KRW", "THB", "BGN", "DKK"};
    DefaultListModel<String> model = new DefaultListModel<>();
    List<String> rates = new ArrayList<>();

    private void initBase() {
        listRates.setModel(model);
        cmbBase.removeAllItems();
        cmbBase.addItem("--Select Base--");
        for (String str : code) {
            cmbBase.addItem(str);
        }
    }

    private void startClock() {
        Timer t = new Timer("Thread-Jam");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                String waktu = df.format(c.getTime());
                lblClock.setText(waktu);
            }
        }, 0, 1000);
    }

    private void loading(boolean b) {
        loading.setVisible(b);
    }

    private void addResponseToList(String base) {
        CurrencyConversionResponse response
                = getResponse(API_PROVDIER + "" + "/latest?" + "apikey=jtxp71VSIJ6u9AnDtbhPkuwstMvKLSGf" + "&base=" + base + "");
        if (response != null) {
            rates.clear();
            for (String str : code) {
                String rate = response.getRates().get(str);
                String item = str + "\t: " + rate;
                rates.add(item);
            }
        }
    }

    private static CurrencyConversionResponse
            getResponse(String strUrl) {
        CurrencyConversionResponse response = null;
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        if (strUrl == null || strUrl.isEmpty()) {
            System.out.println("Application Error");
            return null;
        }
        URL url;
        try {
            url = new URL(strUrl);
            HttpURLConnection connection
                    = (HttpURLConnection) url.openConnection();
            try ( InputStream stream
                    = connection.getInputStream()) {
                int data = stream.read();
                while (data != -1) {
                    sb.append((char) data);
                    data = stream.read();
                }
            }
            response = gson.fromJson(sb.toString(),
                    CurrencyConversionResponse.class);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public ExchangeRate() throws HeadlessException {
        initComponents();

        initBase();
        loading(Boolean.FALSE);
        startClock();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblClock = new javax.swing.JLabel();
        pembatas = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cmbBase = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        listRates = new javax.swing.JList<>();
        loading = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblClock.setText("<CLOCK>");

        jLabel2.setText("BASE : ");

        cmbBase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBaseActionPerformed(evt);
            }
        });

        listRates.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listRates);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pembatas, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(loading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(lblClock))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBase, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblClock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pembatas, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBaseActionPerformed
        int index = cmbBase.getSelectedIndex();
        if (index > 0) {
            loading(true);
            new Exchange("Thread-Exchange").execute();
        } else {
            model.clear();
        }
    }//GEN-LAST:event_cmbBaseActionPerformed
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
            java.util.logging.Logger.getLogger(ExchangeRate.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExchangeRate.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExchangeRate.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExchangeRate.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExchangeRate().setVisible(true);
            }
        });
    }

    class Exchange extends SwingWorker<Object, Object> {

        public Exchange(String name) {
            setName(name);
            System.out.println(name + " => Dijalankan!");
        }

        @Override
        protected Object doInBackground() throws Exception {
            String base
                    = cmbBase.getSelectedItem().toString();
            addResponseToList(base);
            return 0;
        }

        @Override
        protected void done() {
            model.clear();
            rates.forEach((rate) -> {
                model.addElement(rate);
            });
            loading(false);
            System.out.println(getName() + " => Dihentikan!");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBase;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClock;
    private javax.swing.JList<String> listRates;
    private javax.swing.JProgressBar loading;
    private javax.swing.JSeparator pembatas;
    // End of variables declaration//GEN-END:variables

}
