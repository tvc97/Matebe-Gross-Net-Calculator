package matebe.Tvc97;

/**
 *
 * @author Tvc97
 */
public class GrossNet {
    
    double thuNhapVND, thuNhapUSD;
    double bhXH, bhYT, bhTN;
    
    double thuNhapTruocThue;
    double gtCN, gtGC;
    
    double thuNhapChiuThue;
    double[] thueTNCN = new double[7];
    double sumThueTNCN;
    
    public GrossNet() {
        
    }
    
    void calcThuNhap(double vnd, double usd, double rate) {
        double t = vnd + usd * rate;
        thuNhapVND = t;
        thuNhapUSD = t / rate;
    }
    
    void calcBH(double thuNhap, double xh, double yt, double tn, int province) {
        bhXH = xh * thuNhap / 100;
        bhYT = yt * thuNhap / 100;
        
        int vung = Province.vung[province];
        int min  = Province.minimum[vung];
        
        bhTN = Math.min(min * 2, tn * thuNhap / 100);
        if(bhTN == 0) bhTN = min * tn / 100;
        
        thuNhapTruocThue = thuNhapVND - bhXH - bhYT - bhTN;
    }
    
    void calcGT(double cn, double pt, int sn) {
        gtCN = cn;
        gtGC = pt * sn;
        
        thuNhapChiuThue = thuNhapTruocThue - gtCN - gtGC;
    }
    
    void calcThueTNCN() {
        double t = 0, tmp = thuNhapChiuThue;
        int moc[] = {80000000, 52000000, 32000000, 18000000, 10000000, 5000000, 0};
        double thue[] = {0.35, 0.3, 0.25, 0.2, 0.15, 0.1, 0.05};
        for(int i = 0; i < 7; i++) {
            if(tmp >= moc[i]) {
                thueTNCN[6-i] = (tmp - moc[i]) * thue[i];
                t += thueTNCN[6-i];
                tmp -= (tmp - moc[i]);
            }
        }
        sumThueTNCN = t;
    }
    
}
