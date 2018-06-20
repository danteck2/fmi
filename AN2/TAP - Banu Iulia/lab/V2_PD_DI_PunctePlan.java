import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Dată o mulţime de puncte în plan (prin coordonatele lor), să de determine cea mai apropiată pereche de puncte (se vor afişa distanţa şi punctele)

public class PunctePlan {

    public static final boolean showDebug = false;
    private Segment selectedSegment = null;

    public String toString() {
        return "Divide et Impera Strategy";
    }

    public void setElements(ArrayList<Point> elements) {
        elements.sort((a, b) -> Point.compareTo(Point::getX, a, b));
        selectedSegment = dei(elements);
    }

    private Segment dei(List<Point> elements) {
        if (elements.size() < 2)
            return Segment.NONE;
        int middle = (elements.size()) / 2;
        double middlePointX = elements.get(middle).getX();
        // divide

        List<Point> leftList = new ArrayList<>(elements.subList(0, middle));
        List<Point> rightList = new ArrayList<>(elements.subList(middle, elements.size()));

        Segment segLeft = dei(leftList);
        Segment segRight = dei(rightList);
        Segment seg = Segment.min(segLeft, segRight);
        if (showDebug) System.out.println("me  : " + elements + " => " + seg);
        if (showDebug) System.out.println("left: " + leftList + "  =>  " + segLeft);
        if (showDebug) System.out.println("righ: " + rightList + "  =>  " + segRight);
        ArrayList<Point> reconstruct = new ArrayList<>();
        while (!leftList.isEmpty() && !rightList.isEmpty()) {
            final Point min = Point.min(Point::getY, leftList.get(0), rightList.get(0));
            reconstruct.add(min);
            if (min == leftList.get(0)) {
                leftList.remove(0);
            } else {
                rightList.remove(0);
            }
        }
        if (leftList.isEmpty())
            reconstruct.addAll(rightList.stream().collect(Collectors.toList()));
        else
            reconstruct.addAll(leftList.stream().collect(Collectors.toList()));
        if (showDebug) System.out.println("reco: " + reconstruct);
        for (int i = 0; i < reconstruct.size(); i++) {
            elements.set(i, reconstruct.get(i));
        }
        reconstruct.clear();
        ArrayList<Point> validPoints = new ArrayList<>();
        for (Point elem : elements) {
            if (Math.abs(elem.getX() - middlePointX) <= seg.getDistanceSquared()) {
                validPoints.add(elem);
            }
        }
        if (showDebug) System.out.println("vald: " + validPoints);

        for (int i = 0; i < validPoints.size(); i++)
            for (int j = i + 1; j - i < 8 && j < validPoints.size(); j++)
                seg = Segment.min(seg, new Segment(validPoints.get(i), validPoints.get(j)));

        if (showDebug) System.out.println("me:D: " + elements + " => " + seg);
        if (showDebug) System.out.println();
        return seg;
    }
    public Segment select() {
        return selectedSegment;
    }
    public boolean canSelect() {
        return true;
    }
}
