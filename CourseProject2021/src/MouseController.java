import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MouseController extends JComponent implements MouseListener {
    int x = 0;
    int y = 0;
    boolean addAvatars = false;
    int mode = 0;
    int player_mode = 1;
    int state_action = 0;
    int modePlay = 0;
    BaseModel model = null;
    int action = 0;
    int i_start = 0;
    int j_start = 0;

    int team = 1;
    int avatar_i = 0;
    int avatar_j = 0;
    String avatar = "";

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        private void getFourthCoordinateSystemItems(int min) {
            min = i_start;
            for (int index = model.getAttack_square(); index > 0 ; index--) {
                if(min > 8){
                    break;
                }
                for (int i  = 0 ; i <= index; i++){
                    if(j_start -i < 0){
                        continue;
                    }
                    if (Start.schemeArray[min][j_start - i].baseModel.getClass().getName() != "BaseModel"){
                        continue;
                    }
                    else{
                        Start.schemeArray[min][j_start - i].possibility_to_move = 1;
                    }
                    Start.schemeArray[min][j_start - i].number_of_move =1;
                }
                min++;
                if (min > 6){
                    break;
                }
            }
        }

        private void getThirdCoordinateSystemItems(int min) {
            min = i_start;
            for (int index = model.getAttack_square() +1; index > 0 ; index--) {
                if(min > 8){
                    break;
                }
                for (int i  = 0 ; i < index; i++) {
                    if (j_start+ i  > 8 ){
                        continue;
                    }
                    if (Start.schemeArray[min][j_start + i].baseModel.getClass().getName() != "BaseModel"){
                        continue;
                    }
                    else{
                        Start.schemeArray[min][j_start + i].possibility_to_move = 1;
                    }
                    Start.schemeArray[min][j_start + i].number_of_move =1;
                }
                min++;
                if (min > 6){
                    break;
                }
            }
        }

        private void getSecondCoordinateSystemItems(int min) {
            min = i_start - model.getAttack_square();
            for (int index = 0; index < model.getAttack_square() + 1 ; index ++){
                if (min < 0){
                    min++;
                    continue;
                }
                if(min > 8){
                    break;
                }
                for (int i  = 0 ; i <= index; i++){
                    if(j_start -i < 0){
                        continue;
                    }
                    if (Start.schemeArray[min][j_start - i].baseModel.getClass().getName() != "BaseModel"){
                        continue;
                    }else{
                        Start.schemeArray[min][j_start - i].possibility_to_move = 1;
                    }
                    Start.schemeArray[min][j_start - i].number_of_move =1;
                }
                min++;
            }
        }

        private void getFirstCoordinateSystemItems(int min) {
            for (int index = 0; index < model.getAttack_square() + 1 ; index ++) {
                if (min < 0){
                    min++;
                    continue;
                }
                if(min > 8){
                    break;
                }
                for (int i  = 0 ; i <= index; i++){
                    if (j_start+ i  > 8 ){
                        continue;
                    }
                    if (Start.schemeArray[min][j_start + i].baseModel.getClass().getName() != "BaseModel"){
                        continue;
                    }
                    else{
                        Start.schemeArray[min][j_start + i].possibility_to_move = 1;
                    }
                    Start.schemeArray[min][j_start + i].number_of_move =1;
                }
                min++;
            }
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}