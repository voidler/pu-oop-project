import java.awt.*;

public class LittleMan extends  BaseModel{
    public LittleMan(Color color) {
        super(color);
    }
    public LittleMan(int team)
    {
        super(team);
        attack = 6;
        armor = 2;
        health = 12;
        attack_square = 2;
        speed = 2;
    }

}
