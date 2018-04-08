package data;

public class EntityGhost extends Entity {

    public static int compteurGhost = 0;
    private String name;

    public EntityGhost() {
        switch(compteurGhost) {
            case 0:
                this.name = GhostNames.Otoboke.toString();
                break;
            case 1:
                this.name = GhostNames.Oikake.toString();
                break;
            case 2:
                this.name = GhostNames.Kimagure.toString();
                break;
            case 3:
                this.name = GhostNames.Machibuse.toString();
                break;
        }
        compteurGhost++;
    }

    public String getName() {
        return this.name;
    }
}
