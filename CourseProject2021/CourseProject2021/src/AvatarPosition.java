public class AvatarPosition extends Scheme {
    String type ;
    public AvatarPosition(int startX, int start_y, int endX, int endY, BaseModel baseModel, String typeIn) {
        super(startX, start_y, endX, endY, baseModel);
        type =typeIn;
    }
}
