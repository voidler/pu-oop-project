import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Start extends JPanel {

    public static Scheme[][] schemeArray = new Scheme[7][9];
    public static AvatarPosition[] avatar = new AvatarPosition[3];
    static JFrame frame;
    public static Graphics gs;
    public static int pointPlayer1 = 0;
    public static int pointPlayer2 = 0;
    public static ArrayList<BaseModel> avatarDamage = new ArrayList<BaseModel>();

    public static void main(String[] args) {

    }

    private static void addAvatars(int x, int y, int i, int j, Graphics g) {
        if (schemeArray[i][j].baseModel.getClass().getName() == "Elf"){
            addElf(x , y , g);
        }
        else if(schemeArray[i][j].baseModel.getClass().getName() == "LittleMan"){
            addLittleMan(x , y , g);

        }
        else if(schemeArray[i][j].baseModel.getClass().getName() == "Knight"){
            addKnight(x , y , g);
        }
        else if (schemeArray[i][j].status != 0 )
        {
            addStatus(x , y , g);
        }
        else {
            g.setColor(schemeArray[i][j].baseModel.color);
            g.fillRect(x, y, 70, 70);
            g.setColor(Color.black);
            g.drawRect(x, y, 70, 70);
        }
    }

    private static void addStatus(int x, int y, Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("X", x + 30, y + 40);
    }


    private static void addKnight(int x, int y, Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("R", x + 30, y + 40);
    }

    private static void addLittleMan(int x, int y, Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("D", x + 30, y + 40);

    }

    private static void addElf(int x, int y, Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("E", x + 30, y + 40);
    }

    private static void createJFrame() {
        frame = new JFrame();
        frame.setSize(1000, 600);
        frame.getContentPane().add(new Start());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Component mouseClick = new MouseController();
    }
}
