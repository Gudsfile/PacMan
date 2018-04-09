package data;

public class EntityFruit extends EntityPacDot {

    public String name;

    public EntityFruit(int level) {
        if (level == 1) {
            this.name = FruitNames.Cerise.toString();
        } else if (level == 2) {
            this.name = FruitNames.Fraise.toString();
        } else if (level == 3 || level == 4) {
            this.name = FruitNames.Orange.toString();
        } else if (level == 5 || level == 6) {
            this.name = FruitNames.Pomme.toString();
        } else if (level == 7 || level == 8) {
            this.name = FruitNames.Melon.toString();
        } else if (level == 9 || level == 10) {
            this.name = FruitNames.Galboss.toString();
        } else if (level == 11 || level == 12) {
            this.name = FruitNames.Cloche.toString();
        } else {
            this.name = FruitNames.Clé.toString();
        }
    }

    public String getName() {
        return this.name;
    }
}