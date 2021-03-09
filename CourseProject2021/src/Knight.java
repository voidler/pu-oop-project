import java.awt.*;

public class Knight extends BaseModel {
    public Knight(Color color) {
        super(color);
    }
    public Knight(int team)
    {
        super(team);
        attack = 8;
        armor = 3;
        health = 15;
        attack_square = 1;
        speed = 1;
    }
}
