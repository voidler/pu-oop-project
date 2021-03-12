import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 *This is class for control all game by clicking over one of the graphics buckets
 * in front end
 * we use mouse press to handle the event from clicking over jframe
 */

public class MouseController extends JComponent implements MouseListener {
    int x = 0;
    int y = 0;
    boolean addAvatars = false;
    int mode = 0 ;
    int player_mode = 1;
    int state_action = 0;
    int modePlay = 0 ;
    BaseModel model = null;
    int action = 0;
    int i_start = 0 ;
    int j_start = 0 ;

    int team = 1;
    int avatar_i = 0 ;
    int avatar_j = 0 ;
    String avatar = "";

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX()  -20;
        y = e.getY()  - 20;
        if(checkAvatarCount()) {
            if (x > 650) {
                if (checkClickAvatar()) {
                    addAvatars = true;
                    changeScheme(player_mode);
                }
            }
            if (addAvatars) {
                mode++;
            }
            if (mode > 0) {
                if (addElements()) {
                    mode = 0;
                    addAvatars = false;
                }
            }
        }
        else{
            actionInGame();
        }

    }

    private void actionInGame() {
        if (x > 650) {
            int isNotElement = 0;
            checkClickAction();
            switch (state_action) {
                case 1:
                    action = 1;
                    break;
                case 2:
                    action = 2;
                    break;
                case 3:
                    action = 3;
                    break;
                default:isNotElement = 1 ; break;
            }
            if (isNotElement == 0 ) {
                modePlay = 1;
            }
            return;
        }
        if (modePlay > 0){
            if (modePlay == 1) {
                CheckSelectedElement();
                switch(action){
                    case  1 :
                        attackVisual();modePlay++; break;
                    case  2 : moveVisual();modePlay++; break;
                    case  3 : heal();modePlay = 0 ; break;
                }

            }
            else if (modePlay > 1) {
                switch(action){
                    case  1 :
                        attack(); break;
                    case  2 : move(); break;
                }
                refreshNumberOfMove();
                modePlay = 0;
            }
        }
    }

    private void changeSchemeForMove() {
        int min = i_start - model.getAttack_square();
        avatar_i = i_start;
        avatar_j = j_start;
        getFirstCoordinateSystemItems(min);
        getSecondCoordinateSystemItems(min);
        getThirdCoordinateSystemItems(min);
        getFourthCoordinateSystemItems(min);
    }

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

    private void CheckSelectedElement() {
        for (int i = 0 ; i < 7 ; i++)
        {
            for(int j = 0 ; j < 9 ; j++)
            {
                if (Start.schemeArray[i][j].startX<= x &&Start.schemeArray[i][j].startY<= y
                        && Start.schemeArray[i][j].endX >=  x &&Start.schemeArray[i][j].endY >= y ) {
                    if (Start.schemeArray[i][j].baseModel.getClass().getName()!= "BaseModel")
                    {
                        i_start = i ;
                        j_start = j;
                        model = Start.schemeArray[i][j].baseModel;
                        return;
                    }
                }
            }
        }
        model = null;
    }

    private void heal() {
        Random rand = new Random();

        for (int i = 0 ; i < 7 ; i++){
            for (int j = 0 ; j < 9 ; j++) {
                if (Start.schemeArray[i][j].startX <= x && Start.schemeArray[i][j].startY <= y
                        && Start.schemeArray[i][j].endX >= x && Start.schemeArray[i][j].endY >= y) {
                    if (Start.schemeArray[i][j].baseModel.getClass().getName() != "BaseModel"){
                        Start.schemeArray[i][j].baseModel.health += rand.nextInt(7);


                    }
                }
            }
        }
        if (rand.nextInt(7) % 2 != 0)
        {
            return;
        }
        refreshNumberOfMove();
        Start.addElements(Start.gs, true);
    }

    private void moveVisual() {
        if (Start.schemeArray[i_start][j_start].baseModel.team == team)
        {
            return;
        }
        changeSchemeForMove();
        Start.addElements(Start.gs, true);
        changeTeam();
        modePlay++;
        return;
    }

    private void changeTeam() {
        if (team == 1)
        {
            team = 2;
        }
        else
        {
            team = 1;
        }
    }

    private void attack() {
        if (checkClickedPixel(2)) {
            if (Start.schemeArray[i_start][j_start].attachScheme == 1) {
                if (Start.schemeArray[i_start][j_start].baseModel.health + Start.schemeArray[i_start][j_start].baseModel.armor > Start.schemeArray[avatar_i][avatar_j].baseModel.attack) {
                    if (Start.schemeArray[i_start][j_start].baseModel.armor < Start.schemeArray[avatar_i][avatar_j].baseModel.attack) {
                        int attacPoint = Start.schemeArray[avatar_i][avatar_j].baseModel.attack - Start.schemeArray[i_start][j_start].baseModel.armor;
                        Start.schemeArray[i_start][j_start].baseModel.health -= attacPoint;
                        Start.schemeArray[i_start][j_start].baseModel.armor = 0;
                        addPoint();
                    }
                } else {
                    Start.avatarDamage.add(Start.schemeArray[i_start][j_start].baseModel );
                    Start.schemeArray[i_start][j_start].baseModel = new BaseModel();
                    addPoint();
                }
            } else {
                refreshNumberOfMove();
            }
        }
    }

    private void addPoint() {
        switch (team)
        {
            case 1: Start.pointPlayer1++; break;
            case 2 : Start.pointPlayer2++ ; break;
        }
        refreshNumberOfMove();

    }

    private void move() {
        if(checkClickedPixel(1))
        {
            if (Start.schemeArray[i_start][j_start].danger != 1) {
                BaseModel model_avatar = Start.schemeArray[i_start][j_start].baseModel;
                Start.schemeArray[i_start][j_start].baseModel = Start.schemeArray[avatar_i][avatar_j].baseModel;
                Start.schemeArray[avatar_i][avatar_j].baseModel = model_avatar;
            }
            else
            {
                return;
            }
        }
        refreshNumberOfMove();
        Start.addElements(Start.gs, true);
    }

    private boolean checkClickedPixel(int modes) {
        for (int i = 0 ; i < 7 ; i++){
            for (int j = 0 ; j < 9 ; j++){
                if (Start.schemeArray[i][j].startX <= x && Start.schemeArray[i][j].startY <= y
                        && Start.schemeArray[i][j].endX >= x && Start.schemeArray[i][j].endY >= y) {
                    if (modes == 1){
                        if (i == avatar_i || j == j_start) {
                            return false;
                        }
                        if (Start.schemeArray[i][j].number_of_move != 0) {
                            i_start = i;
                            j_start = j;
                            return true;
                        }
                    }
                    else{
                        if (Start.schemeArray[i][j].attachScheme != 0) {
                            i_start = i;
                            j_start = j;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void attackVisual() {
        int min_i = i_start - model.getAttack_square();
        int max_i = i_start + model.getAttack_square();
        int min_j = j_start - model.getAttack_square();
        int max_j = j_start + model.getAttack_square();
        if (Start.schemeArray[i_start][j_start].baseModel.team == team){
            return;
        }
        if (min_i > 0 ){
            addBattlePlace(min_i,j_start);
        }
        if (max_i < 7 ){
            addBattlePlace(max_i,j_start);
        }
        if (min_j > 0 ) {
            addBattlePlace(i_start,min_j);
        }
        if (max_j < 9){
            addBattlePlace(i_start,max_j);
        }
        avatar_i = i_start;
        avatar_j = j_start;
        Start.addElements(Start.gs, true);
        changeTeam();
    }

    private void addBattlePlace(int index_i , int index_j) {
        if(Start.schemeArray[index_i][index_j].baseModel.getClass().getName() != "BaseModel" &&
                Start.schemeArray[index_i][index_j].baseModel.team == team){
            Start.schemeArray[index_i ][index_j].attachScheme = 1;
        }
    }

    private void refreshNumberOfMove() {
        for (int i = 0 ; i < 7 ; i++){
            for (int j = 0 ; j < 9 ; j++)
            {
                Start.schemeArray[i][j].number_of_move = 0 ;
                Start.schemeArray[i][j].possibility_to_move = 0 ;
                Start.schemeArray[i][j].attachScheme = 0 ;
            }
        }
        changeTeam();
        Start.addElements(Start.gs , true);
    }

    private void checkClickAction() {
        for (int index = 0 ; index < 3 ; index++) {
            if (Start.avatar[index].startX <= x && Start.avatar[index].startY <= y
                    && Start.avatar[index].endX >= x && Start.avatar[index].endY >= y) {
                state_action = index + 1;
            }
        }
    }

    private boolean checkAvatarCount() {
        return Start.checkAvatarCount();
    }

    private Boolean addElements()
    {
        boolean isAdded = false;
        for (int i = 0 ; i < 7 ; i++) {
            for (int j = 0; j < 9; j++) {
                if (Start.schemeArray[i][j].startX<= x &&Start.schemeArray[i][j].startY<= y
                        && Start.schemeArray[i][j].endX >=  x &&Start.schemeArray[i][j].endY >= y ) {
                    if (player_mode == 1){
                        if (i > 1){
                            return  false;
                        }
                    }
                    else{
                        if (i< 5){
                            return false ;
                        }
                    }
                    if (Start.schemeArray[i][j].baseModel.getClass().getName() == "BaseModel") {
                        addAvatar(i , j);
                        isAdded = true;
                        break;
                    }
                }
            }
            if (isAdded)
            {
                refreshScheme();
                break;
            }
        }
        return isAdded;
    }
    private void addAvatar(int i , int j) {
        switch (avatar) {
            case "D":
                Start.schemeArray[i][j].baseModel = new LittleMan(team);
                break;
            case "R":
                Start.schemeArray[i][j].baseModel = new Knight(team);
                break;
            case "E":
                Start.schemeArray[i][j].baseModel = new Elf(team);
                break;
        }
        if(team ==  1){
            team = 2;
        }
        else
        {
            team = 1;
        }
    }

    private void refreshScheme() {
        for (int i = 0 ; i < 7 ; i++) {
            for (int j = 0; j < 9; j++) {
                Start.schemeArray[i][j].status = 0 ;
            }
        }
        if(player_mode == 1)
        {
            player_mode = 2;
        }
        else
        {
            player_mode = 1;
        }
        Start.addElements(Start.gs , true);
    }

    private boolean checkClickAvatar() {
        boolean isClicked = false;
        for (int index = 0 ; index < 3 ; index++)
        {
            if (Start.avatar[index].startX<= x &&Start.avatar[index].startY<= y
                    && Start.avatar[index].endX >=  x &&Start.avatar[index].endY >= y )
            {
                if(!checkCount(Start.avatar[index].type))
                {
                    isClicked = false;
                    break;
                }
                avatar = Start.avatar[index].type;
                isClicked = true;
                break;
            }
        }
        return  isClicked ;
    }

    private boolean checkCount(String type) {
        String className = "";
        switch(type){
            case "R" :className = "Knight" ;
                break;
            case "D" :className = "LittleMan";
                break;
            case "E" : className = "Elf";
                break;
        }
        int count = 0 ;
        for(int i = 0 ; i < 7; i++){
            for(int j = 0 ; j < 9 ; j++){
                if (Start.schemeArray[i][j].player == player_mode
                        && Start.schemeArray[i][j].baseModel.getClass().getName() == className){
                    count++;
                }
                if (count == 2){
                    return false;
                }
            }
        }
        return  true;
    }

    private void changeScheme(int player_mode) {
        for (int i = 0 ; i < 7 ; i++){
            for (int j = 0 ; j < 9 ; j++){
                if (Start.schemeArray[i][j].player != player_mode){
                    Start.schemeArray[i][j].status = 1;
                }
            }
        }
        Start.addElements(Start.gs, true);
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
