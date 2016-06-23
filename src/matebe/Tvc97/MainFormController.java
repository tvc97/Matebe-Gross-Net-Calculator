package matebe.Tvc97;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class MainFormController implements Initializable {

    @FXML
    ComboBox comboProvince;

    @FXML
    TextField tfSalaryVND, tfSalaryUSD, tfCurrencyRate, tfBHXH, tfBHYT, tfBHTN, tfGTGCCN, tfGTGCPT, tfSNPT, tfBHK;

    @FXML
    ToggleGroup baoHiemRG;

    @FXML
    RadioButton radioSalary, radioOther;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboProvince.getItems().addAll(Province.list);
        comboProvince.getSelectionModel().select(30);

        addFilter();
    }

    @FXML
    public void netGross(MouseEvent a) {
        GrossNet g = new GrossNet();

        try {
            double vnd = Double.parseDouble(tfSalaryVND.getText());
            double usd = Double.parseDouble(tfSalaryUSD.getText());
            double rate = Double.parseDouble(tfCurrencyRate.getText());
            double bhxh = Double.parseDouble(tfBHXH.getText());
            double bhyt = Double.parseDouble(tfBHYT.getText());
            double bhtn = Double.parseDouble(tfBHTN.getText());
            double gtcn = Double.parseDouble(tfGTGCCN.getText());
            double gtpt = Double.parseDouble(tfGTGCPT.getText());
            double other = Double.parseDouble(tfBHK.getText());
            int pt = Integer.parseInt(tfSNPT.getText());
            int province = comboProvince.getSelectionModel().getSelectedIndex();
            int vung = Province.vung[province];
            boolean insuranceSalary = baoHiemRG.getSelectedToggle().equals(radioSalary);

            double net = vnd + usd * rate;

            while (true) {
                g.calcThuNhap(vnd, usd, rate);
                if (insuranceSalary) {
                    g.calcBH(g.thuNhapVND, bhxh, bhyt, bhtn, province);
                } else {
                    g.calcBH(other, bhxh, bhyt, bhtn, province);
                }
                g.calcGT(gtcn, gtpt, pt);
                g.calcThueTNCN();
                if (g.thuNhapTruocThue - g.sumThueTNCN >= net) {
                    break;
                } else {
                    vnd += 1000;
                }
            }

            while ((long) (g.thuNhapTruocThue - g.sumThueTNCN) > (long) net) {
                vnd--;
                g.calcThuNhap(vnd, usd, rate);
                if (insuranceSalary) {
                    g.calcBH(g.thuNhapVND, bhxh, bhyt, bhtn, province);
                } else {
                    g.calcBH(other, bhxh, bhyt, bhtn, province);
                }
                g.calcGT(gtcn, gtpt, pt);
                g.calcThueTNCN();
            }

            ReportModel.rate = rate;
            ReportModel.thuNhapVND = g.thuNhapVND;
            ReportModel.thuNhapUSD = g.thuNhapUSD;
            ReportModel.bhXH = g.bhXH;
            ReportModel.bhYT = g.bhYT;
            ReportModel.bhTN = g.bhTN;
            ReportModel.thuNhapTruocThue = g.thuNhapTruocThue;
            ReportModel.gtCN = g.gtCN;
            ReportModel.gtGC = g.gtGC;
            ReportModel.thuNhapChiuThue = g.thuNhapChiuThue;
            ReportModel.sumThueTNCN = g.sumThueTNCN;
            ReportModel.net = g.thuNhapTruocThue - g.sumThueTNCN;
            System.arraycopy(g.thueTNCN, 0, ReportModel.thueTNCN, 0, 7);

            ReportModel.SDLDGross = g.thuNhapVND;
            ReportModel.SDLDBHXH = g.thuNhapVND * 0.18;
            ReportModel.SDLDBHYT = g.thuNhapVND * 0.03;
            ReportModel.SDLDBHTN = g.thuNhapVND * 0.01;
            if (ReportModel.SDLDBHTN == 0) {
                ReportModel.SDLDBHTN = Province.minimum[vung] * 0.01;
            }
            ReportModel.SDLDTotal = g.thuNhapVND + ReportModel.SDLDBHXH + ReportModel.SDLDBHYT + ReportModel.SDLDBHTN;

            GrossNetCalculator.instance.openReportForm();
        } catch (Exception exc) {
            this.showAlert("Lỗi", "Đã xảy ra lỗi", "Vui lòng nhập đầy đủ thông tin form yêu cầu", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void grossNet(MouseEvent a) {
        GrossNet g = new GrossNet();

        try {
            double vnd = Double.parseDouble(tfSalaryVND.getText());
            double usd = Double.parseDouble(tfSalaryUSD.getText());
            double rate = Double.parseDouble(tfCurrencyRate.getText());
            double bhxh = Double.parseDouble(tfBHXH.getText());
            double bhyt = Double.parseDouble(tfBHYT.getText());
            double bhtn = Double.parseDouble(tfBHTN.getText());
            double gtcn = Double.parseDouble(tfGTGCCN.getText());
            double gtpt = Double.parseDouble(tfGTGCPT.getText());
            double other = Double.parseDouble(tfBHK.getText());
            int pt = Integer.parseInt(tfSNPT.getText());
            int province = comboProvince.getSelectionModel().getSelectedIndex();
            int vung = Province.vung[province];
            boolean insuranceSalary = baoHiemRG.getSelectedToggle().equals(radioSalary);

            g.calcThuNhap(vnd, usd, rate);
            if (insuranceSalary) {
                g.calcBH(g.thuNhapVND, bhxh, bhyt, bhtn, province);
            } else {
                g.calcBH(other, bhxh, bhyt, bhtn, province);
            }
            g.calcGT(gtcn, gtpt, pt);
            g.calcThueTNCN();

            ReportModel.rate = rate;
            ReportModel.thuNhapVND = g.thuNhapVND;
            ReportModel.thuNhapUSD = g.thuNhapUSD;
            ReportModel.bhXH = g.bhXH;
            ReportModel.bhYT = g.bhYT;
            ReportModel.bhTN = g.bhTN;
            ReportModel.thuNhapTruocThue = g.thuNhapTruocThue;
            ReportModel.gtCN = g.gtCN;
            ReportModel.gtGC = g.gtGC;
            ReportModel.thuNhapChiuThue = g.thuNhapChiuThue;
            ReportModel.sumThueTNCN = g.sumThueTNCN;
            ReportModel.net = g.thuNhapTruocThue - g.sumThueTNCN;
            System.arraycopy(g.thueTNCN, 0, ReportModel.thueTNCN, 0, 7);

            ReportModel.SDLDGross = g.thuNhapVND;
            ReportModel.SDLDBHXH = g.thuNhapVND * 0.18;
            ReportModel.SDLDBHYT = g.thuNhapVND * 0.03;
            ReportModel.SDLDBHTN = g.thuNhapVND * 0.01;
            if (ReportModel.SDLDBHTN == 0) {
                ReportModel.SDLDBHTN = Province.minimum[vung] * 0.01;
            }
            ReportModel.SDLDTotal = g.thuNhapVND + ReportModel.SDLDBHXH + ReportModel.SDLDBHYT + ReportModel.SDLDBHTN;

            GrossNetCalculator.instance.openReportForm();
        } catch (Exception exc) {
            this.showAlert("Lỗi", "Đã xảy ra lỗi", "Vui lòng nhập đầy đủ thông tin form yêu cầu", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void openHelpForm(MouseEvent a) {
        GrossNetCalculator.instance.openHelpForm();
    }

    void addFilter() {
        tfSalaryVND.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(18));
        tfSalaryUSD.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(18));
        tfCurrencyRate.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(18));
        tfBHXH.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(18));
        tfBHYT.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(18));
        tfBHTN.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(18));
        tfGTGCCN.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(18));
        tfGTGCPT.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(18));
        tfSNPT.addEventFilter(KeyEvent.KEY_TYPED, integerValidation(9));
    }

    public EventHandler<KeyEvent> integerValidation(final Integer max_Lengh) {
        return (KeyEvent e) -> {
            TextField txt_TextField = (TextField) e.getSource();
            if (txt_TextField.getText().length() >= max_Lengh) {
                e.consume();
            }
            if (!e.getCharacter().matches("[0-9]")) {
                e.consume();
            }
        };
    }

    public EventHandler<KeyEvent> numericValidation(final Integer max_Lengh) {
        return (KeyEvent e) -> {
            TextField txt_TextField = (TextField) e.getSource();
            if (txt_TextField.getText().length() >= max_Lengh) {
                e.consume();
            }
            if (e.getCharacter().matches("[0-9.]")) {
                if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                    e.consume();
                } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                    e.consume();
                }
            } else {
                e.consume();
            }
        };
    }

    public void showAlert(String title, String header, String msg, Alert.AlertType alt) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alt);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(msg);
            alert.showAndWait();
        });

    }
}