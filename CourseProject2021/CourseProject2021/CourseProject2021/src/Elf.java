import java.awt.*;

public class Elf extends  BaseModel {
    public Elf(Color color) {
        super(color);
    }
    public Elf(int team)
    {
        super(team);
        attack = 5;
        armor = 1;
        health = 10;
        attack_square = 3;
        speed = 3;
    }
    public Elf()
    {
    }
}
