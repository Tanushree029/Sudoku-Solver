public class Trial {

    public static void main(String[]  args) {

        long startTime = System.currentTimeMillis();

        Game g = new Game();

        while(true) {
            g.go();
            if(Utils.isOver(g.getSudoku()) || g.term) {
                break;
            }
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (stopTime - startTime));
    }
}