package game;

import java.io.Serializable;
import java.util.Arrays;

public class GameInfo implements Serializable {
    private int isThere[][];
    private double gridX[][];
    private double gridY[][];
    private int lalX[];
    private int lalY[];
    private int nilX[];
    private int nilY[];
    private double sideLineLal[];
    private double sideLineNil[];
    private boolean millFormed;
    public boolean nullMove;
    public boolean win;
    public boolean falseWin;

    public boolean isFalseWin() {
        return falseWin;
    }

    public void setFalseWin(boolean falseWin) {
        this.falseWin = falseWin;
    }

    public boolean isNullMove() {
        return nullMove;
    }

    public void setNullMove(boolean nullMove) {
        this.nullMove = nullMove;
    }

    public boolean isMillFormed() {
        return millFormed;
    }

    public void setMillFormed(boolean millFormed) {
        this.millFormed = millFormed;
    }

    public void setIsThere (int i,int j,int val) {
        this.isThere[i][j]=val;
    }

    public int getIsThere (int i,int j) {
        return this.isThere[i][j];
    }

    public double[][] getGridX() {
        return gridX;
    }

    public double getGridX(int i,int j) {
        return gridX[i][j];
    }

    public void setGridX(int i,int j,double val) {
        this.gridX[i][j] = val;
    }

    public double getGridY(int i,int j) {
        return gridY[i][j];
    }

    public void setGridY(int i,int j,double val) {
        this.gridY[i][j] = val;
    }

    public int getLalX(int i) {
        return lalX[i];
    }

    public void setLalX(int i, int val) {
        this.lalX[i] = val;
    }

    public double getLalY(int i) {
        return lalY[i];
    }

    public void setLalY(int i, int val) {
        this.lalY[i] = val;
    }

    public double getNilX(int i) {
        return nilX[i];
    }

    public void setNilX(int i, int val) {
        this.nilX[i] = val;
    }

    public double getNilY(int i) {
        return nilY[i];
    }

    public void setNilY(int i, int val) {
        this.nilY[i] = val;
    }

    public int[] getLalX() {
        return lalX;
    }

    public void setLalX(int[] lalX) {
        this.lalX = lalX;
    }

    public int[] getLalY() {
        return lalY;
    }

    public void setLalY(int[] lalY) {
        this.lalY = lalY;
    }

    public int[] getNilX() {
        return nilX;
    }

    public void setNilX(int[] nilX) {
        this.nilX = nilX;
    }

    public int[] getNilY() {
        return nilY;
    }

    public void setNilY(int[] nilY) {
        this.nilY = nilY;
    }

    public double getSideLineLal(int i) {
        return sideLineLal[i];
    }

    public double getSideLineNil(int i) {
        return sideLineNil[i];
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    GameInfo () {
        millFormed=false;
        nullMove=false;
        win=false;
        falseWin=false;

        isThere = new int[8][8];
        gridX = new double [8][8];
        gridY = new double [8][8];
        lalX = new int[9];
        lalY = new int[9];
        nilX = new int[9];
        nilY = new int[9];
        sideLineLal = new double[9];
        sideLineNil = new double[9];

        for (int i=0;i<7;i++) for (int j=0;j<7;j++) isThere[i][j]=0;
        isThere[0][1]=isThere[0][2]=isThere[0][4]=isThere[0][5]=-1;
        isThere[1][0]=isThere[1][2]=isThere[1][4]=isThere[1][6]=-1;
        isThere[2][0]=isThere[2][1]=isThere[2][5]=isThere[2][6]=-1;
        isThere[3][3]=-1;
        isThere[6][1]=isThere[6][2]=isThere[6][4]=isThere[6][5]=-1;
        isThere[5][0]=isThere[5][2]=isThere[5][4]=isThere[5][6]=-1;
        isThere[4][0]=isThere[4][1]=isThere[4][5]=isThere[4][6]=-1;

        for (int i=0;i<7;i++) {
            gridY[0][i]=-180;   gridX[i][0]=-70;
            gridY[1][i]=-119;   gridX[i][1]=-7;
            gridY[2][i]=-58;    gridX[i][2]=53;
            gridY[3][i]=-2;     gridX[i][3]=106;
            gridY[4][i]=55;     gridX[i][4]=165;
            gridY[5][i]=113;    gridX[i][5]=225;
            gridY[6][i]=171;    gridX[i][6]=282;
        }

        for (int i=0;i<9;i++) {
            sideLineLal[i]=-220+i*20;
            sideLineNil[i]=55+i*20;
        }
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "isThere=" + Arrays.toString(isThere) +
                ", gridX=" + Arrays.toString(gridX) +
                ", gridY=" + Arrays.toString(gridY) +
                ", lalX=" + Arrays.toString(lalX) +
                ", lalY=" + Arrays.toString(lalY) +
                ", nilX=" + Arrays.toString(nilX) +
                ", nilY=" + Arrays.toString(nilY) +
                ", sideLineLal=" + Arrays.toString(sideLineLal) +
                ", sideLineNil=" + Arrays.toString(sideLineNil) +
                ", millFormed=" + millFormed +
                '}';
    }
}
