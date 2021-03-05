import java.awt.*;

public class BaseModel {
    Color color ;
    int finalStatus = 0;

    public int attack;
    public int armor;
    public int health;
    public int attack_square;
    public int speed;
    public int team;

    public  BaseModel()
    {}
    public BaseModel(Color color)
    {
        this.color = color;
    }
    public BaseModel(Color color, int state )
    {
        this.color = color;
    }
    public BaseModel(int attack, int armor, int health, int attack_square, int speed )
    {
        this.attack = attack;
        this.armor=armor;
        this.health = health;
        this.attack_square = attack_square;
        this.speed =speed;
    }
    public BaseModel (int team)
    {
        this.team = team;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack_square() {
        return attack_square;
    }
}
