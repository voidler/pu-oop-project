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


    private static void addMovePixels(int x, int y, int i, int j, Graphics g) {
        if(schemeArray[i][j].number_of_move != 0 )
        {
            if (schemeArray[i][j].danger == 1)
            {
                g.setColor(Color.red);
                g.fillRect(x, y, 70, 70);
                g.setColor(Color.black);
                g.drawRect(x, y, 70, 70);
            }
            else
            {
                g.setColor(Color.green);
                g.fillRect(x, y, 70, 70);
                g.setColor(Color.black);
                g.drawRect(x, y, 70, 70);
            }
        }
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

    private static void addPlayerTwoInfoPoint(int x , int y , Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString(String.valueOf(pointPlayer2), x + 30, y + 40);
    }

    private static void addPlayerOneInfoPoint(int x , int y , Graphics g) {

        g.setColor(Color.green);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString(String.valueOf(pointPlayer1), x + 30, y + 40);
    }

    private static void checkForWinner() {
        int player1 = 0 ;
        int player2 = 0;
        int totalCount = 0 ;
        for (int i = 0 ; i <  7; i++){
            for (int j = 0 ; j < 9 ; j++ ){
                if (schemeArray[i][j].baseModel.team == 1){
                    player1 ++;
                }
                else{
                    player2++;
                }
                if(schemeArray[i][j].baseModel.getClass().getName() != "BaseModel"){
                    totalCount++;
                }
            }
        }
        if (totalCount == 0 ){
            return;
        }
        generateMessage(player1 , player2);

    }

    private static void generateMessage(int player1, int player2) {
        if (player1 == 0 ){
            JOptionPane.showMessageDialog(null, getStringForPlayer(1,2), "Winner: ", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if (player2 == 0){
                JOptionPane.showMessageDialog(null, getStringForPlayer(2,1), "Winner: ", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static String getStringForPlayer(int play1 , int play2 ) {
        String winner = "player 1 is winner\n";
        String plyer1Damage = getAvatarDamage(1);
        String plyer2Damage = getAvatarDamage(2);
        return winner + plyer1Damage + plyer2Damage;
    }

    private static String getAvatarDamage(int modes) {
        String avatars = "";
        for (int i = 0 ; i < avatarDamage.size(); i++)
        {
            if (avatarDamage.get(i).team == modes) {
                avatars += avatarDamage.get(i).getClass().getName();
            }
        }
        return avatars;
    }
}