package matebe.Tvc97;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Tvc97
 */
public class ReportFormController implements Initializable {

    @FXML
    Label lbGROSS, lbNET;
    @FXML
    Label lbGross, lbBHXH, lbBHYT, lbBHTN;
    @FXML
    Label lbThuNhapTruocThue, lbGTGCCN, lbGTGCPT, lbThuNhapChiuThue, lbThueTNCN, lbNet;
    @FXML
    Label lbTax0, lbTax1, lbTax2, lbTax3, lbTax4,lbTax5, lbTax6;
    @FXML
    Label lbSDLDGross, lbSDLDBHXH, lbSDLDBHYT, lbSDLDBHTN, lbSDLDTotal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Label taxes[] = {lbTax0, lbTax1, lbTax2, lbTax3, lbTax4,lbTax5, lbTax6};
        try {
            NumberFormat nf = NumberFormat.getInstance();
            String gross = nf.format((long)ReportModel.thuNhapVND) + " VND ≈ " + nf.format((long)ReportModel.thuNhapUSD) + " USD";
            lbGROSS.setText(gross);
            String net = nf.format((long)ReportModel.net) + " VND ≈ " + nf.format((long)toUSD(ReportModel.net)) + " USD";
            lbNET.setText(net);

            lbGross.setText(nf.format((long)ReportModel.thuNhapVND));
            lbBHXH.setText("- " + nf.format((long)ReportModel.bhXH));
            lbBHYT.setText("- " + nf.format((long)ReportModel.bhYT));
            lbBHTN.setText("- " + nf.format((long)ReportModel.bhTN));
            lbNet.setText(nf.format((long)ReportModel.net));

            lbThuNhapTruocThue.setText(nf.format((long)ReportModel.thuNhapTruocThue));
            lbThuNhapChiuThue.setText(nf.format((long)ReportModel.thuNhapChiuThue));
            lbGTGCCN.setText("- " + nf.format((long)ReportModel.gtCN));
            lbGTGCPT.setText("- " + nf.format((long)ReportModel.gtGC));
            lbThueTNCN.setText("- " + nf.format((long)ReportModel.sumThueTNCN));
            
            for(int i = 0; i < 7; i++)
                taxes[i].setText(nf.format((long)ReportModel.thueTNCN[i]));
            
            lbSDLDGross.setText(nf.format((long)ReportModel.SDLDGross));
            lbSDLDBHXH.setText(nf.format((long)ReportModel.SDLDBHXH));
            lbSDLDBHYT.setText(nf.format((long)ReportModel.SDLDBHYT));
            lbSDLDBHTN.setText(nf.format((long)ReportModel.SDLDBHTN));
            lbSDLDTotal.setText(nf.format((long)ReportModel.SDLDTotal));
        }catch(Exception exc) {
        }
    }
    
    public double toUSD(double vnd) {
        return vnd / ReportModel.rate;
    }
     
    
}
