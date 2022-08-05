package PackSCN;

public class Sc_Main{
    static  MainWindow mainWindow;

    public void ScreenLook(){
        mainWindow = new MainWindow();
        mainWindow.preparePanels();
        mainWindow.prepareComponents();
        mainWindow.ScreenFocus(ScreenMode.STEETING);
        mainWindow.setVisible(true);
    }
}